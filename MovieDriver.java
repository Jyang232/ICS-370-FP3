import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MovieDriver {

    public Movie createMovie(Movie m) {

    }

    public Movie readMovie() {
        
    }

    public boolean updateMovie(Movie m) {

    }

    public Movie deleteMovie() {

    }
	
	public static void main(String[] args) {
		
		String varaible = "movie_id";
		String table = "movies";
		
		String username = "root"; 
		String password = " "; //*********enter your password here

		try {
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb", username , password);
			
			java.sql.Statement myStat = myCon.createStatement();
			
			ResultSet myRs = myStat.executeQuery("select " + varaible + " from " + table);
			
			while(myRs.next())
			{
				System.out.println(myRs.getString(varaible));
				}
			}
		catch (Exception ex)
		{
			ex.printStackTrace();
			}
		}
	}
