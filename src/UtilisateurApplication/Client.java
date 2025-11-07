package UtilisateurApplication;
import java.util.ArrayList;
import java.util.List;

import InventaireArticle.*;


public class Client extends Utilisateur {
    private Panier panier;
    
    public Client(String id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
        this.panier = new Panier();
    }
    
    public Panier getPanier() { return panier; }
    public void setPanier(Panier panier) { this.panier = panier; }
    
    @Override
    public void connecter() {
        System.out.println("✓ Client " + getNom() + " connecté.");
    }
    
    @Override
    public void deconnecter() {
        System.out.println("✓ Client " + getNom() + " déconnecté.");
    }
    
    public void ajouterAuPanier(ArticleEpicerie article, int quantite) {
        if (article.estDisponible(quantite)) {
            panier.ajouterArticle(article, quantite);
            System.out.println("✓ " + quantite + "x " + article.getNom() + " ajouté(s) au panier");
        } else {
            System.out.println("✗ Stock insuffisant pour " + article.getNom());
        }
    }
    
    public List<String> consulterSuggestions() {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("→ Promotions du jour");
        suggestions.add("→ Nouveautés");
        suggestions.add("→ Articles similaires");
        return suggestions;
    }
    
    public Vente payer(Caisse caisse) {
        System.out.println("\n💳 Traitement du paiement...");
        Vente vente = caisse.enregistrerVente(panier);
        
        // Mettre à jour les stocks
        for (LignePanier ligne : panier.getLignes()) {
            ArticleEpicerie article = ligne.getArticle();
            article.setQuantiteStock(article.getQuantiteStock() - ligne.getQuantite());
        }
        
        System.out.println(vente.genererFacture());
        panier.vider();
        return vente;
    }
}