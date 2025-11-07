package InventaireArticle;

public class ArticleEpicerie {
    private String id;
    private String nom;
    private double prix;
    private int quantiteStock;
    private String categorie;
    
    public ArticleEpicerie(String id, String nom, double prix, int quantiteStock, String categorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.categorie = categorie;
    }
    
    public String getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public int getQuantiteStock() { return quantiteStock; }
    public String getCategorie() { return categorie; }
    
    public void setId(String id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setQuantiteStock(int quantiteStock) { this.quantiteStock = quantiteStock; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    
    public double getPrixTotal(int qte) {
        return prix * qte;
    }
    
    public boolean estDisponible(int qte) {
        return quantiteStock >= qte;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | %-20s | Prix: %6.2f€ | Stock: %4d | Catégorie: %s", 
            id, nom, prix, quantiteStock, categorie);
    }
}