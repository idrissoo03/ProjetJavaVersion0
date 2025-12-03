package InventaireArticle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vente {
    private String idVente;
    private LocalDate date;
    private List<LigneVente> articles;
    private double total;
    private double taxe;
    
    public Vente(String idVente) {
        this.idVente = idVente;
        this.date = LocalDate.now();
        this.articles = new ArrayList<>();
        this.total = 0;
        this.taxe = 0;
    }
    
    public String getIdVente() { return idVente; }
    public LocalDate getDate() { return date; }
    
    public List<LigneVente> getArticles() { return articles; }
    public double getTotal() { return total; }
    public double getTaxe() { return taxe; }
    public int getNombreArticles() {
        return (articles != null) ? articles.size() : 0;
    }    
    public void enregistrerVente(Panier panier) {
        for (LignePanier lignePanier : panier.getLignes()) {
            LigneVente ligneVente = new LigneVente(
                lignePanier.getArticle(), 
                lignePanier.getQuantite()
            );
            articles.add(ligneVente);
        }
        calculerTotal();
    }
    
    public double calculerTotal() {
        total = 0;
        for (LigneVente ligne : articles) {
            total += ligne.getSousTotal();
        }
        taxe = total * 0.20; // TVA 20%
        total += taxe;
        return total;
    }
    
    public String genererFacture() {
        StringBuilder facture = new StringBuilder();
        facture.append("                            FACTURE                            \n");
        facture.append(String.format("    ID Vente: %-47s %n", idVente));
        facture.append(String.format("    Date: %-51s %n", date));
        
        for (LigneVente ligne : articles) {
            facture.append(String.format("    %-30s x%-3d = %8.2f€ ║%n", 
                ligne.getArticle().getNom(), 
                ligne.getQuantite(), 
                ligne.getSousTotal()));
        }
        facture.append(String.format("   Sous-total:                                 %10.2f€ %n", total - taxe));
        facture.append(String.format("    TVA (20%%):                                  %10.2f€ %n", taxe));
        facture.append(String.format("    TOTAL:                                      %10.2f€ %n", total));
        
        return facture.toString();
    }
}