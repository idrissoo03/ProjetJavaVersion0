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
        System.out.println("✓ Administrateur " + getNom() + " connecté (accès complet)");
    }
    
    @Override
    public void deconnecter() {
        System.out.println("✓ Administrateur " + getNom() + " déconnecté");
    }
    
    public List<String> gererInventaire(Inventaire inventaire) {
        System.out.println("\n📦 Gestion de l'inventaire...");
        List<String> actions = new ArrayList<>();
        actions.add("→ Vérification des stocks");
        actions.add("→ " + inventaire.getArticles().size() + " articles en inventaire");
        actions.add("→ Valeur totale: " + String.format("%.2f€", inventaire.calculerValeurStock()));
        
        List<ArticleEpicerie> perimes = inventaire.iterArticlesPerimes();
        if (!perimes.isEmpty()) {
            actions.add("⚠️ " + perimes.size() + " article(s) périmé(s) détecté(s)");
        }
        
        for (String action : actions) {
            System.out.println(action);
        }
        return actions;
    }
    
    public String genererRapport(Caisse caisse, Inventaire inventaire) {
        System.out.println("\n📊 Génération du rapport...");
        StringBuilder rapport = new StringBuilder();
        rapport.append("\n╔════════════════════════════════════╗\n");
        rapport.append("║     RAPPORT DE GESTION QUOTIDIEN   ║\n");
        rapport.append("╚════════════════════════════════════╝\n");
        rapport.append("Date: ").append(LocalDate.now()).append("\n\n");
        
        rapport.append("--- VENTES ---\n");
        rapport.append("Nombre de transactions: ").append(caisse.getVentesJournalieres().size()).append("\n");
        rapport.append("Chiffre d'affaires: ").append(String.format("%.2f€", caisse.getTotalVentes())).append("\n");
        rapport.append("Fond de caisse: ").append(String.format("%.2f€", caisse.getFondDeCaisse())).append("\n\n");
        
        rapport.append("--- INVENTAIRE ---\n");
        rapport.append("Articles en stock: ").append(inventaire.getArticles().size()).append("\n");
        rapport.append("Valeur du stock: ").append(String.format("%.2f€", inventaire.calculerValeurStock())).append("\n");
        
        List<ArticleEpicerie> perimes = inventaire.iterArticlesPerimes();
        rapport.append("Articles périmés: ").append(perimes.size()).append("\n");
        
        rapport.append("\n════════════════════════════════════\n");
        
        String rapportStr = rapport.toString();
        System.out.println(rapportStr);
        return rapportStr;
    }
    
    public void validerVente(Vente vente) {
        if (vente != null) {
            System.out.println("✓ Vente " + vente.getIdVente() + " validée par l'administrateur");
        } else {
            System.out.println("✗ Vente invalide");
        }
    }
}