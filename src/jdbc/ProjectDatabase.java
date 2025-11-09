package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class ProjectDatabase {
    public static void main(String[] args) throws  SQLException {
        
        String url = "jdbc:mysql://localhost:3306/projet";
        String user = "root"; 
        String password = "root";
        
        String createArticleTable = """
        		SELECT * FROM Article
        	""";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()) {

               stmt.executeUpdate(createArticleTable);
               System.out.println("Tables created successfully!");

           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
}
