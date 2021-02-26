import java.sql.*;

/**
 * Iteration 3 Assignment ICS 370 2/26/21
 * 
 * MovieDriver class uses the JBconnector to acesss the omdb database and
 * display some information from it into eclipse output
 * 
 * @author Jason Yang
 *
 */

public class MovieDriver {

	public static void selectAllMovies() {

		Connection con = null;

		try {
			String dbURL = "jdbc:mysql://localhost:3306/omdb";
			String username = "root";
			String password = "1234";

			con = DriverManager.getConnection(dbURL, username, password);
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * From Movies");
			while (myRs.next()) {
				int id = myRs.getInt(1);
				String native_name = myRs.getString(2);
				String english_name = myRs.getString(3);
				int year = myRs.getInt(4);
				System.out.println("ID: " + id + "\t" + "Native name: " + native_name + "\t" + "English name: "
						+ english_name + "\t" + "Year: " + year);
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void createMovie(String native_name, String english_name, int year_made) {

		Connection con = null;

		try {
			String dbURL = "jdbc:mysql://localhost:3306/omdb";
			String username = "root";
			String password = "1234";
			con = DriverManager.getConnection(dbURL, username, password);

			String sql = "Insert INTO Movies (native_name, english_name, year_made) VALUES (?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, native_name);
			statement.setString(2, english_name);
			statement.setInt(3, year_made);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new movie was inserted successfully.");
			}

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void readMovie() {
		Connection con = null;

		try {
			String dbURL = "jdbc:mysql://localhost:3306/omdb";
			String username = "root";
			String password = "1234";
			con = DriverManager.getConnection(dbURL, username, password);

			String sql = "SELECT movies.movie_id, movies.native_name, movies.english_name, movies.year_made, movie_data.tag_line, movie_data.language,"
					+ "movie_data.country, movie_data.genre, movie_data.plot FROM movies, movie_data WHERE movies.movie_id = movie_data.movie_id";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet myRs = statement.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt(1);
				String native_name = myRs.getString(2);
				String english_name = myRs.getString(3);
				int year = myRs.getInt(4);
				String tag_line = myRs.getString(5);
				String language = myRs.getString(6);
				String country = myRs.getString(7);
				String genre = myRs.getString(8);
				String plot = myRs.getString(9);

				System.out.println("ID: " + id + "\t" + "Native name: " + native_name + "\t" + "English name: "
						+ english_name + "\t" + "Year: " + year + "\t" + "Tag Line: " + tag_line + "\t" + "Language: "
						+ language + "\t" + "Country: " + country + "\t" + "Genre: " + genre + "\t" + "Plot: " + plot);
			}

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void updateMovie(int id, String new_Native_Name, String new_English_Name, int new_Year_Made) {

		Connection con = null;

		try {
			String dbURL = "jdbc:mysql://localhost:3306/omdb";
			String username = "root";
			String password = "1234";
			con = DriverManager.getConnection(dbURL, username, password);

			String sql = "UPDATE Movies SET native_name =?, english_name=?, year_made=? WHERE movie_id=?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, new_Native_Name);
			statement.setString(2, new_English_Name);
			statement.setInt(3, new_Year_Made);
			statement.setInt(4, id);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("A new movie was updated successfully.");
			}

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void deleteMovie(int id) {

		Connection con = null;

		try {
			String dbURL = "jdbc:mysql://localhost:3306/omdb";
			String username = "root";
			String password = "1234";
			con = DriverManager.getConnection(dbURL, username, password);

			String sql = "Delete FROM Movies WHERE movie_id =?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A new movie was deleted successfully.");
			}

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		// createMovie("Transformers", "Transformers", 2008);
		// updateMovie(20118, "Wall-E", "Wall-E", 2009);
		// deleteMovie(20118);
		// readMovie();
	}
}

