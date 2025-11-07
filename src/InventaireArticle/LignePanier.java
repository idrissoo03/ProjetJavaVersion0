package InventaireArticle;

public class LignePanier {
    private ArticleEpicerie article;
    private int quantite;
    
    public LignePanier(ArticleEpicerie article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }
    
    public ArticleEpicerie getArticle() { return article; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    
    public double getTotal() {
        return article.getPrix() * quantite;
    }
}