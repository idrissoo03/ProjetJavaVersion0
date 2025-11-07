package InventaireArticle;
import java.time.LocalDate;


public class ArticlePerissable extends ArticleEpicerie {
    private LocalDate dateExpiration;
    private boolean alerteExpiration;
    private int joursRestants;
    
    public ArticlePerissable(String id, String nom, double prix, int quantiteStock, 
                            String categorie, LocalDate dateExpiration) {
        super(id, nom, prix, quantiteStock, categorie);
        this.dateExpiration = dateExpiration;
        this.joursRestants = calculerJoursRestants();
        this.alerteExpiration = joursRestants <= 3;
    }
    
    public LocalDate getDateExpiration() { return dateExpiration; }
    public boolean isAlerteExpiration() { return alerteExpiration; }
    public int getJoursRestants() { return joursRestants; }
    
    public void setDateExpiration(LocalDate dateExpiration) { 
        this.dateExpiration = dateExpiration;
        this.joursRestants = calculerJoursRestants();
        this.alerteExpiration = joursRestants <= 3;
    }
    
    private int calculerJoursRestants() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dateExpiration);
    }
    
    public boolean estPerime() {
        return LocalDate.now().isAfter(dateExpiration);
    }
    
    public void activerAlerte() {
        if (joursRestants <= 3 && joursRestants > 0) {
            System.out.println("⚠️ ALERTE: Article " + getNom() + " expire dans " + joursRestants + " jours!");
        } else if (joursRestants <= 0) {
            System.out.println("❌ PÉRIMÉ: Article " + getNom() + " est périmé!");
        }
    }
    
    @Override
    public String toString() {
        String statut = estPerime() ? "❌ PÉRIMÉ" : (joursRestants <= 3 ? "⚠️ Expire bientôt" : "✓ OK");
        return super.toString() + String.format(" | Expiration: %s (%s)", dateExpiration, statut);
    }
}