package InventaireArticle;
import java.util.ArrayList;
import java.util.List;

public class Caisse {
    private List<Vente> ventesJournalieres;
    private double fondDeCaisse;
    
    public Caisse(double fondDeCaisse) {
        this.ventesJournalieres = new ArrayList<>();
        this.fondDeCaisse = fondDeCaisse;
    }
    
    public List<Vente> getVentesJournalieres() { return ventesJournalieres; }
    public double getFondDeCaisse() { return fondDeCaisse; }
    
    public Vente enregistrerVente(Panier panier, Inventaire inventaire) {
        // Vérifier que tous les articles sont disponibles en stock
        for (LignePanier ligne : panier.getLignes()) {
            ArticleEpicerie article = inventaire.getArticle(ligne.getArticle().getId());
            if (article == null || !article.estDisponible(ligne.getQuantite())) {
                return null; // Retourne null si stock insuffisant
            }
        }
        
        // Créer la vente
        Vente vente = new Vente("V" + String.format("%04d", ventesJournalieres.size() + 1));
        vente.enregistrerVente(panier);
        
        // Mettre à jour le stock pour chaque article vendu
        for (LignePanier ligne : panier.getLignes()) {
            ArticleEpicerie article = inventaire.getArticle(ligne.getArticle().getId());
            if (article != null) {
                article.diminuerStock(ligne.getQuantite());
            }
        }
        
        // Mettre à jour le fond de caisse
        fondDeCaisse += vente.getTotal();
        
        ventesJournalieres.add(vente);
        return vente;
    }
    
    public double getTotalVentes() {
        double total = 0;
        for (Vente vente : ventesJournalieres) {
            total += vente.getTotal();
        }
        return total;
    }
}
