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
    
    public Vente enregistrerVente(Panier panier) {
        Vente vente = new Vente("V" + (ventesJournalieres.size() + 1));
        vente.enregistrerVente(panier);
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