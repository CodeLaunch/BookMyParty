import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionClass {
	 private Connection connect = null;
	  private java.sql.Statement statement = null;
	  private ResultSet resultSet = null;

	  public static void main(String a[]) throws Exception
	  {
		  ConnectionClass b = new ConnectionClass();
		  ResultSet x= b.readDataBase();
		  while(x.next())
		  {
			  System.out.println(x.getString("name"));
		  }
	  }
	  public ResultSet readDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/cater_db?"
	              + "user=root&password=r00t");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("SELECT name, id, ( 3959 * acos( cos( radians(37) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(-122) ) + sin( radians(37) ) * sin( radians( lat ) ) ) ) AS distance, address, lat, lng FROM cater_db HAVING distance < 25 ORDER BY distance LIMIT 0 , 20;");
	      return resultSet;
	    } catch (Exception e) {
	      throw e;
	    } finally {
	    }

	  }

	  // You need to close the resultSet
	  private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }
}
