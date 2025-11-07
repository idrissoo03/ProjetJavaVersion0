package InventaireArticle;

public class ArticleNonPerissable extends ArticleEpicerie {
    private int dureeConservation;
    
    public ArticleNonPerissable(String id, String nom, double prix, int quantiteStock, 
                                String categorie, int dureeConservation) {
        super(id, nom, prix, quantiteStock, categorie);
        this.dureeConservation = dureeConservation;
    }
    
    public int getDureeConservation() { return dureeConservation; }
    public void setDureeConservation(int dureeConservation) { this.dureeConservation = dureeConservation; }
}