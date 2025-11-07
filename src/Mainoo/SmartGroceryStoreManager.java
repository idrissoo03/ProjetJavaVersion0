package Mainoo;

import InventaireArticle.*;

import java.time.LocalDate;
import java.util.List;

import UtilisateurApplication.*;

public class SmartGroceryStoreManager {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║  SMART GROCERY STORE MANAGER - DEMO       ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        
        // Initialisation du système
        Inventaire inventaire = new Inventaire();
        Caisse caisse = new Caisse(100.0);
        
        // Création des articles
        System.out.println("📦 Initialisation de l'inventaire...\n");
        
        ArticlePerissable lait = new ArticlePerissable(
            "A001", "Lait", 1.50, 50, "Produits laitiers", 
            LocalDate.now().plusDays(5)
        );
        
        ArticlePerissable pain = new ArticlePerissable(
            "A002", "Pain", 1.20, 30, "Boulangerie", 
            LocalDate.now().plusDays(2)
        );
        
        ArticleNonPerissable pates = new ArticleNonPerissable(
            "A003", "Pâtes", 2.50, 100, "Épicerie salée", 365
        );
        
        ArticleNonPerissable riz = new ArticleNonPerissable(
            "A004", "Riz", 3.00, 80, "Épicerie salée", 730
        );
        
        ArticlePerissable fromage = new ArticlePerissable(
            "A005", "Fromage", 4.50, 25, "Produits laitiers", 
            LocalDate.now().plusDays(10)
        );
        
        inventaire.ajouterArticle(lait);
        inventaire.ajouterArticle(pain);
        inventaire.ajouterArticle(pates);
        inventaire.ajouterArticle(riz);
        inventaire.ajouterArticle(fromage);
        
        System.out.println("✓ " + inventaire.getArticles().size() + " articles ajoutés à l'inventaire\n");
        
        // Vérification des articles périssables
        pain.activerAlerte();
        
        // Création des utilisateurs
        Client client1 = new Client("C001", "Alice Dupont", "alice@email.com", "pass123");
        Client client2 = new Client("C002", "Bob Martin", "bob@email.com", "pass456");
        Administrateur admin = new Administrateur("A001", "Sophie Admin", "admin@store.com", "admin123");
        
        System.out.println("\n👥 Utilisateurs créés:");
        System.out.println("   • Client: " + client1.getNom());
        System.out.println("   • Client: " + client2.getNom());
        System.out.println("   • Admin: " + admin.getNom());
        
        // Simulation 1: Client Alice fait ses courses
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SIMULATION 1: ACHAT CLIENT ALICE");
        System.out.println("=".repeat(50));
        
        client1.connecter();
        
        System.out.println("\n🛒 Alice fait ses courses:");
        client1.ajouterAuPanier(lait, 2);
        client1.ajouterAuPanier(pain, 1);
        client1.ajouterAuPanier(fromage, 1);
        
        System.out.println("\n💰 Panier d'Alice:");
        for (LignePanier ligne : client1.getPanier().getLignes()) {
            System.out.println("   • " + ligne.getArticle().getNom() + 
                             " x" + ligne.getQuantite() + 
                             " = " + String.format("%.2f€", ligne.getTotal()));
        }
        System.out.println("   TOTAL: " + String.format("%.2f€", client1.getPanier().getTotal()));
        
        Vente vente1 = client1.payer(caisse);
        admin.validerVente(vente1);
        
        client1.deconnecter();
        
        // Simulation 2: Client Bob fait ses courses
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SIMULATION 2: ACHAT CLIENT BOB");
        System.out.println("=".repeat(50));
        
        client2.connecter();
        
        System.out.println("\n🛒 Bob fait ses courses:");
        client2.ajouterAuPanier(pates, 3);
        client2.ajouterAuPanier(riz, 2);
        client2.ajouterAuPanier(lait, 1);
        
        System.out.println("\n💡 Suggestions pour Bob:");
        List<String> suggestions = client2.consulterSuggestions();
        for (String suggestion : suggestions) {
            System.out.println("   " + suggestion);
        }
        
        Vente vente2 = client2.payer(caisse);
        
        client2.deconnecter();
        
        // Administration
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SIMULATION 3: GESTION ADMINISTRATIVE");
        System.out.println("=".repeat(50));
        
        admin.connecter();
        
        admin.gererInventaire(inventaire);
        admin.genererRapport(caisse, inventaire);
        
        // Rapport de caisse
        RapportCaisse rapportCaisse = new RapportCaisse(
            LocalDate.now(), 
            caisse.getVentesJournalieres(), 
            caisse.getFondDeCaisse()
        );
        
        System.out.println("\n💵 Rapport de caisse détaillé:");
        System.out.println("   • Fond initial: " + String.format("%.2s€", rapportCaisse.getDate()));
        System.out.println("   • Ventes du jour: " + rapportCaisse.getVentes().size());
        System.out.println("   • Total des ventes: " + String.format("%.2f€", rapportCaisse.getTotal()));
        System.out.println("   • Fond final: " + String.format("%.2f€", rapportCaisse.getFondFinal()));
        
        admin.deconnecter();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("✓ DÉMONSTRATION TERMINÉE");
        System.out.println("=".repeat(50));
    }
}