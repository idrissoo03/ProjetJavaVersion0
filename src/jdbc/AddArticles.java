package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class AddArticles {
	public static void main (String[] args ) throws SQLException {
		 String url = "jdbc:mysql://localhost:3306/projet";
		 String user = "root";
		 String password = "root";
		 
		 String InsertArticles = """
		 		INSERT INTO ARTICLE (id,nom,prix,quantite,stock,categorie) VALUES (
		 		1001,
		 		'Yaourt',
		 		3.5,
		 		50,
		 		50,
		 		'Produit Latiere'
		 		);
		 		""";
		 
		 try (Connection con = DriverManager.getConnection(url,user,password);
				 Statement stmt = con.createStatement();) {
			 stmt.executeUpdate(InsertArticles);
			 System.out.println("Values Added!");
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
	}

}
