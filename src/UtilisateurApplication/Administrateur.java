package UtilisateurApplication;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import InventaireArticle.*;


public class Administrateur extends Utilisateur {
    
    public Administrateur(String id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }
    
    @Override
    public void connecter() {
        System.out.println("\n   ✓ Administrateur " + getNom() + " connecté (accès complet au système)");
    }
    
    @Override
    public void deconnecter() {
        System.out.println("\n   ✓ Administrateur " + getNom() + " déconnecté");
    }
    
    public void ajouterArticle(Inventaire inventaire, ArticleEpicerie article) {
        inventaire.ajouterArticle(article);
        System.out.println("   ✓ Article " + article.getNom() + " ajouté à l'inventaire");
    }
    
    public void supprimerArticle(Inventaire inventaire, String id) {
        ArticleEpicerie article = inventaire.getArticle(id);
        if (article != null) {
            inventaire.supprimerArticle(id);
            System.out.println("   ✓ Article " + article.getNom() + " supprimé de l'inventaire");
        } else {
            System.out.println("   ✗ Article non trouvé");
        }
    }
    
    public void modifierArticle(Inventaire inventaire, String id, String nom, double prix, int stock, String categorie) {
        ArticleEpicerie article = inventaire.getArticle(id);
        if (article != null) {
            article.setNom(nom);
            article.setPrix(prix);
            article.setQuantiteStock(stock);
            article.setCategorie(categorie);
            System.out.println("   ✓ Article modifié avec succès");
        } else {
            System.out.println("   ✗ Article non trouvé");
        }
    }
    
    public String genererRapport(Caisse caisse, Inventaire inventaire) {
        StringBuilder rapport = new StringBuilder();
        rapport.append("\n ╔════════════════════════════════════════════════════════════╗\n");
        rapport.append("   ║              RAPPORT DE GESTION QUOTIDIEN                  ║\n");
        rapport.append("   ╠════════════════════════════════════════════════════════════╣\n");
        rapport.append(String.format("   ║ Date: %-52s ║%n", LocalDate.now()));
        rapport.append("   ╠════════════════════════════════════════════════════════════╣\n");
        rapport.append("   ║                        VENTES                              ║\n");
        rapport.append("   ╠════════════════════════════════════════════════════════════╣\n");
        rapport.append(String.format("   ║ Nombre de transactions: %-34d ║%n", caisse.getVentesJournalieres().size()));
        rapport.append(String.format("   ║ Chiffre d'affaires:                         %10.2f€ ║%n", caisse.getTotalVentes()));
        rapport.append(String.format("   ║ Fond de caisse initial:                     %10.2f€ ║%n", caisse.getFondDeCaisse()));
        rapport.append(String.format("   ║ Fond de caisse actuel:                      %10.2f€ ║%n", caisse.getFondDeCaisse() + caisse.getTotalVentes()));
        rapport.append("   ╠════════════════════════════════════════════════════════════╣\n");
        rapport.append("   ║                      INVENTAIRE                            ║\n");
        rapport.append("   ╠════════════════════════════════════════════════════════════╣\n");
        rapport.append(String.format("   ║ Articles en stock: %-39d ║%n", inventaire.getArticles().size()));
        rapport.append(String.format("   ║ Valeur du stock:                            %10.2f€ ║%n", inventaire.calculerValeurStock()));
        
        List<ArticleEpicerie> perimes = inventaire.iterArticlesPerimes();
        rapport.append(String.format("   ║ Articles périmés: %-40d ║%n", perimes.size()));
        
        rapport.append("   ╚════════════════════════════════════════════════════════════╝\n");
        
        String rapportStr = rapport.toString();
        System.out.println(rapportStr);
        return rapportStr;
    }
    
    public void validerVente(Vente vente) {
        if (vente != null) {
            System.out.println("   ✓ Vente " + vente.getIdVente() + " validée par l'administrateur");
        } else {
            System.out.println("   ✗ Vente invalide");
        }
    }
}
