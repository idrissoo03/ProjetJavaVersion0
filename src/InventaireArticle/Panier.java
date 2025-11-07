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
}