package InventaireArticle;

class LigneVente {
    private ArticleEpicerie article;
    private int quantite;
    private double sousTotal;
    
    public LigneVente(ArticleEpicerie article, int quantite) {
        this.article = article;
        this.quantite = quantite;
        this.sousTotal = article.getPrix() * quantite;
    }
    
    public ArticleEpicerie getArticle() { return article; }
    public int getQuantite() { return quantite; }
    public double getSousTotal() { return sousTotal; }
}
