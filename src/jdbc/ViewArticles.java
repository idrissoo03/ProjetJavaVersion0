package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ViewArticles {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/projet"; 
        String user = "root"; 
        String password = "root";

        String query = "SELECT * FROM Article;";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Articles found in database:\n");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                int stock = rs.getInt("stock");
                String categorie = rs.getString("categorie");
                String createdAt = rs.getString("created_at");

                System.out.printf("ID: %d | Nom: %s | Prix: %.2f | Quantité: %d | Stock: %d | Catégorie: %s | Créé le: %s%n",
                        id, nom, prix, quantite, stock, categorie, createdAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
