package UtilisateurApplication;
import java.util.ArrayList;
import java.util.List;

import InventaireArticle.*;


public class Client extends Utilisateur {
    private Panier panier;
    private List<Vente> historique;
    
    public Client(String id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
        this.panier = new Panier();
        this.historique = new ArrayList<>();
    }
    
    public Panier getPanier() { return panier; }
    public void setPanier(Panier panier) { this.panier = panier; }
    public List<Vente> getHistorique() { return historique; }
    
    @Override
    public void connecter() {
        System.out.println("\n   ✓ Client " + getNom() + " connecté avec succès!");
    }
    
    @Override
    public void deconnecter() {
        System.out.println("\n   ✓ Au revoir " + getNom() + "!");
    }
    
    public void ajouterAuPanier(ArticleEpicerie article, int quantite) {
        if (article.estDisponible(quantite)) {
            panier.ajouterArticle(article, quantite);
            System.out.println("   ✓ " + quantite + "x " + article.getNom() + " ajouté(s) au panier");
        } else {
            System.out.println("   ✗ Stock insuffisant pour " + article.getNom() + " (disponible: " + article.getQuantiteStock() + ")");
        }
    }
    
    public Vente payer(Caisse caisse) {
        if (panier.estVide()) {
            System.out.println("   ✗ Impossible de payer : panier vide!");
            return null;
        }
        
        System.out.println("\n   💳 Traitement du paiement...");
        Vente vente = caisse.enregistrerVente(panier);
        
        // Mettre à jour les stocks
        for (LignePanier ligne : panier.getLignes()) {
            ArticleEpicerie article = ligne.getArticle();
            article.setQuantiteStock(article.getQuantiteStock() - ligne.getQuantite());
        }
        
        historique.add(vente);
        System.out.println(vente.genererFacture());
        System.out.println("   ✓ Paiement effectué avec succès!");
        panier.vider();
        return vente;
    }
}