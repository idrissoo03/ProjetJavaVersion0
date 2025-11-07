package InventaireArticle;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



public class Inventaire {
    private Map<String, ArticleEpicerie> articles;
    
    public Inventaire() {
        this.articles = new HashMap<>();
    }
    
    public Map<String, ArticleEpicerie> getArticles() { return articles; }
    
    public void ajouterArticle(ArticleEpicerie article) {
        articles.put(article.getId(), article);
    }
    
    public void mettreAJourStock(String articleId, int qte) {
        ArticleEpicerie article = articles.get(articleId);
        if (article != null) {
            article.setQuantiteStock(article.getQuantiteStock() + qte);
        }
    }
    
    public boolean estRealisable(Inventaire inv) {
        // Vérifier si l'inventaire actuel correspond à un inventaire donné
        return articles.size() == inv.getArticles().size();
    }
    
    public List<ArticleEpicerie> iterArticlesPerimes() {
        List<ArticleEpicerie> perimes = new ArrayList<>();
        for (ArticleEpicerie article : articles.values()) {
            if (article instanceof ArticlePerissable) {
                ArticlePerissable perissable = (ArticlePerissable) article;
                if (perissable.estPerime()) {
                    perimes.add(article);
                }
            }
        }
        return perimes;
    }
    
    public double calculerValeurStock() {
        double valeur = 0;
        for (ArticleEpicerie article : articles.values()) {
            valeur += article.getPrix() * article.getQuantiteStock();
        }
        return valeur;
    }
}