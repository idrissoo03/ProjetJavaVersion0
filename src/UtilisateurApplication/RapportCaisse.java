package UtilisateurApplication;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import InventaireArticle.*;

class RapportCaisse {
    private LocalDate date;
    private List<Vente> ventes;
    private double total;
    private double fondInitial;
    private double fondFinal;
    
    public RapportCaisse(LocalDate date, List<Vente> ventes, double fondInitial) {
        this.date = date;
        this.ventes = new ArrayList<>(ventes);
        this.fondInitial = fondInitial;
        calculerTotaux();
    }
    
    public LocalDate getDate() { return date; }
    public List<Vente> getVentes() { return ventes; }
    public double getTotal() { return total; }
    public double getFondFinal() { return fondFinal; }
    
    private void calculerTotaux() {
        total = 0;
        for (Vente v : ventes) {
            total += v.getTotal();
        }
        fondFinal = fondInitial + total;
    }
}