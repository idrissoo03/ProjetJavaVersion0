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
    
    public ArticleEpicerie getArticle(String id) {
        return articles.get(id);
    }
    
    public void supprimerArticle(String id) {
        articles.remove(id);
    }
    
    public void mettreAJourStock(String articleId, int qte) {
        ArticleEpicerie article = articles.get(articleId);
        if (article != null) {
            article.setQuantiteStock(qte);
        }
    }
    
    public List<ArticleEpicerie> rechercherParNom(String nom) {
        List<ArticleEpicerie> resultats = new ArrayList<>();
        for (ArticleEpicerie article : articles.values()) {
            if (article.getNom().toLowerCase().contains(nom.toLowerCase())) {
                resultats.add(article);
            }
        }
        return resultats;
    }
    
    public List<ArticleEpicerie> rechercherParCategorie(String categorie) {
        List<ArticleEpicerie> resultats = new ArrayList<>();
        for (ArticleEpicerie article : articles.values()) {
            if (article.getCategorie().equalsIgnoreCase(categorie)) {
                resultats.add(article);
            }
        }
        return resultats;
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
    
    public void afficherTous() {
        if (articles.isEmpty()) {
            System.out.println("   ℹ️ Aucun article en inventaire");
            return;
        }
        
        System.out.println("\n   ╔═════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("   ║                                    INVENTAIRE COMPLET                                       ║");
        System.out.println("   ╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
        
        for (ArticleEpicerie article : articles.values()) {
            System.out.println("   " + article);
        }
        
        System.out.println("\n   Total articles: " + articles.size());
        System.out.println("   Valeur totale du stock: " + String.format("%.2f€", calculerValeurStock()));
    }
}