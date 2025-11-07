package InventaireArticle;
import java.util.ArrayList;
import java.util.List;


public class Panier {
    private List<LignePanier> lignes;
    
    public Panier() {
        this.lignes = new ArrayList<>();
    }
    
    public List<LignePanier> getLignes() { return lignes; }
    
    public void ajouterArticle(ArticleEpicerie article, int qte) {
        for (LignePanier ligne : lignes) {
            if (ligne.getArticle().getId().equals(article.getId())) {
                ligne.setQuantite(ligne.getQuantite() + qte);
                return;
            }
        }
        lignes.add(new LignePanier(article, qte));
    }
    
    public void modifierQuantite(String articleId, int nouvelleQuantite) {
        for (LignePanier ligne : lignes) {
            if (ligne.getArticle().getId().equals(articleId)) {
                ligne.setQuantite(nouvelleQuantite);
                return;
            }
        }
    }
    
    public void supprimerArticle(String articleId) {
        lignes.removeIf(ligne -> ligne.getArticle().getId().equals(articleId));
    }
    
    public double getTotal() {
        double total = 0;
        for (LignePanier ligne : lignes) {
            total += ligne.getTotal();
        }
        return total;
    }
    
    public void vider() {
        lignes.clear();
    }
    
    public boolean estVide() {
        return lignes.isEmpty();
    }
    
    public void afficher() {
        if (lignes.isEmpty()) {
            System.out.println("   🛒 Panier vide");
            return;
        }
        
        System.out.println("\n   ╔════════════════════════════════════════════════════════════╗");
        System.out.println("   ║                    CONTENU DU PANIER                       ║");
        System.out.println("   ╠════════════════════════════════════════════════════════════╣");
        
        for (LignePanier ligne : lignes) {
            System.out.printf("   ║ %-30s x%-3d = %8.2f€ ║%n", 
                ligne.getArticle().getNom(), 
                ligne.getQuantite(), 
                ligne.getTotal());
        }
        
        System.out.println("   ╠════════════════════════════════════════════════════════════╣");
        System.out.printf("   ║ TOTAL:                                      %10.2f€ ║%n", getTotal());
        System.out.println("   ╚════════════════════════════════════════════════════════════╝");
    }
}