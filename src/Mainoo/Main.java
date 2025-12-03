package Mainoo;

import InventaireArticle.*;
import UtilisateurApplication.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class SmartGroceryStoreManager {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Inventaire inventaire = new Inventaire();
    private static Caisse caisse = new Caisse(500.0);
    private static Map<String, Administrateur> admins = new HashMap<>();

    
    public static void main(String[] args) {
        initialiserDonnees();
        
        afficherBanniere();
        
        boolean continuer = true;
        while (continuer) {
            afficherMenuPrincipal();
            int choix = lireEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    menuAdministrateur();
                    break;
                case 2:
                    System.out.println("\n   Merci d'avoir utilisé Notre System!");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n   ✗ Choix invalide!");
            }
        }
        
        scanner.close();
    }
    



    private static void rechercherProduit() {
        System.out.println("\n ╔═══════════════════════════════════════════════════════════╗");
        System.out.println("   ║                  RECHERCHE DE PRODUIT                     ║");
        System.out.println("   ╠═══════════════════════════════════════════════════════════╣");
        System.out.println("   ║  1. Par nom                                               ║");
        System.out.println("   ║  2. Par catégorie                                         ║");
        System.out.println("   ╚═══════════════════════════════════════════════════════════╝");
        
        int choix = lireEntier("Votre choix: ");
        
        List<ArticleEpicerie> resultats = new ArrayList<>();
        
        if (choix == 1) {
            System.out.print("\n   Nom du produit: ");
            String nom = scanner.nextLine();
            resultats = inventaire.rechercherParNom(nom);
        } else if (choix == 2) {
            System.out.print("\n   Catégorie: ");
            String categorie = scanner.nextLine();
            resultats = inventaire.rechercherParCategorie(categorie);
        }
        
        if (resultats.isEmpty()) {
            System.out.println("\n   ℹ️ Aucun produit trouvé");
        } else {
            System.out.println("\n ╔═══════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("   ║                              RÉSULTATS DE LA RECHERCHE                                    ║");
            System.out.println("   ╚═══════════════════════════════════════════════════════════════════════════════════════════╝");
            for (ArticleEpicerie article : resultats) {
                System.out.println("   " + article);
            }
        }
        pauseEtContinuer();
    }
   
   
    // ==================== MENU ADMINISTRATEUR ====================
    private static void menuAdministrateur() {
        boolean continuer = true;
        while (continuer) {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                 ESPACE ADMINISTRATEUR                        ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. 📝 Créer un compte administrateur                        ║");
            System.out.println("║  2. 🔐 Se connecter                                          ║");
            System.out.println("║  3. 🔙 Retour                                                ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            
            int choix = lireEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    creerCompteAdmin();
                    break;
                case 2:
                    connexionAdmin();
                    break;
                case 3:
                    continuer = false;
                    break;
                default:
                    System.out.println("\n   ✗ Choix invalide!");
            }
        }
    }
    
    private static void creerCompteAdmin() {
        System.out.println("\n ╔═══════════════════════════════════════════════════════════╗");
        System.out.println("   ║          CRÉATION DE COMPTE ADMINISTRATEUR                ║");
        System.out.println("   ╚═══════════════════════════════════════════════════════════╝");
        
        String id = "A" + String.format("%04d", admins.size() + 1);
        System.out.print("   Nom complet: ");
        String nom = scanner.nextLine();
        System.out.print("   Email: ");
        String email = scanner.nextLine();
        System.out.print("   Mot de passe: ");
        String motDePasse = scanner.nextLine();
        
        Administrateur admin = new Administrateur(id, nom, email, motDePasse);
        admins.put(id, admin);
        
        System.out.println("\n   ✓ Compte administrateur créé avec succès!");
        System.out.println("   Votre ID admin: " + id);
        pauseEtContinuer();
    }
    
    private static void connexionAdmin() {
        System.out.print("\n   ID Admin: ");
        String id = scanner.nextLine();
        System.out.print("   Mot de passe: ");
        String motDePasse = scanner.nextLine();
        
        Administrateur admin = admins.get(id);
        
        if (admin != null && admin.getMotDePasse().equals(motDePasse)) {
            admin.connecter();
            menuAdminConnecte(admin);
        } else {
            System.out.println("\n   ✗ Identifiants incorrects!");
            pauseEtContinuer();
        }
    }
    
    private static void menuAdminConnecte(Administrateur admin) {
        boolean continuer = true;
        
        while (continuer) {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║             MENU ADMINISTRATEUR - " + String.format("%-23s", admin.getNom()) + " ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. 📦 Voir l'inventaire complet                             ║");
            System.out.println("║  2. 🛒 Gérer une vente                                       ║");
            System.out.println("║  3. ➕ Ajouter un article                                    ║");
            System.out.println("║  4. ✏️  Modifier un article                                  ║");
            System.out.println("║  5. 🗑️  Supprimer un article                                 ║");
            System.out.println("║  6. 🔍 Rechercher un article                                 ║");
            System.out.println("║  7. 📊 Générer un rapport                                    ║");
            System.out.println("║  8. 💰 Voir les ventes du jour                               ║");
            System.out.println("║  9. ⚠️  Vérifier les articles périmés                        ║");
            System.out.println("║ 10. 🚪 Déconnexion                                           ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            
            int choix = lireEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    inventaire.afficherTous();
                    pauseEtContinuer();
                    break;
                case 2:
                    gererVente(admin);
                    break;
                case 3:
                    ajouterArticleAdmin(admin);
                    break;
                case 4:
                    modifierArticleAdmin(admin);
                    break;
                case 5:
                    supprimerArticleAdmin(admin);
                    break;
                case 6:
                    rechercherProduit();
                    break;

                case 7:
                    admin.genererRapport(caisse, inventaire);
                    pauseEtContinuer();
                    break;
                case 8:
                    afficherVentesJour();
                    break;
                case 9:
                    verifierArticlesPerimes();
                    break;
                case 10:
                    admin.deconnecter();
                    continuer = false;
                    break;
                default:
                    System.out.println("\n   ✗ Choix invalide!");
            }
        }
    }
    
    private static void ajouterArticleAdmin(Administrateur admin) {
        System.out.println("\n ╔═══════════════════════════════════════════════════════════╗");
        System.out.println("   ║                   AJOUTER UN ARTICLE                      ║");
        System.out.println("   ╠═══════════════════════════════════════════════════════════╣");
        System.out.println("   ║  1. Article périssable                                    ║");
        System.out.println("   ║  2. Article non périssable                                ║");
        System.out.println("   ╚═══════════════════════════════════════════════════════════╝");
        
        int typeChoix = lireEntier("Type d'article: ");
        
        System.out.print("\n   ID de l'article: ");
        String id = scanner.nextLine();
        System.out.print("   Nom: ");
        String nom = scanner.nextLine();
        double prix = lireDouble("   Prix: ");
        int stock = lireEntier("   Quantité en stock: ");
        System.out.print("   Catégorie: ");
        String categorie = scanner.nextLine();
        
        ArticleEpicerie article = null;
        
        if (typeChoix == 1) {
            System.out.print("   Date d'expiration (AAAA-MM-JJ): ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate dateExpiration = LocalDate.parse(dateStr);
                article = new ArticlePerissable(id, nom, prix, stock, categorie, dateExpiration);
            } catch (Exception e) {
                System.out.println("   ✗ Format de date invalide!");
                pauseEtContinuer();
                return;
            }
        } else if (typeChoix == 2) {
            int dureeConservation = lireEntier("   Durée de conservation (jours): ");
            article = new ArticleNonPerissable(id, nom, prix, stock, categorie, dureeConservation);
        }
        
        if (article != null) {
            admin.ajouterArticle(inventaire, article);
        }
        pauseEtContinuer();
    }
    
    private static void modifierArticleAdmin(Administrateur admin) {
        System.out.print("\n   ID de l'article à modifier: ");
        String id = scanner.nextLine();
        
        ArticleEpicerie article = inventaire.getArticle(id);
        
        if (article == null) {
            System.out.println("   ✗ Article non trouvé!");
            pauseEtContinuer();
            return;
        }
        
        System.out.println("\n   Article actuel: " + article);
        System.out.println("\n   Entrez les nouvelles valeurs (Entrée pour garder l'ancienne):");
        
        System.out.print("   Nouveau nom [" + article.getNom() + "]: ");
        String nom = scanner.nextLine();
        if (nom.isEmpty()) nom = article.getNom();
        
        System.out.print("   Nouveau prix [" + article.getPrix() + "]: ");
        String prixStr = scanner.nextLine();
        double prix = prixStr.isEmpty() ? article.getPrix() : Double.parseDouble(prixStr);
        
        System.out.print("   Nouveau stock [" + article.getQuantiteStock() + "]: ");
        String stockStr = scanner.nextLine();
        int stock = stockStr.isEmpty() ? article.getQuantiteStock() : Integer.parseInt(stockStr);
        
        System.out.print("   Nouvelle catégorie [" + article.getCategorie() + "]: ");
        String categorie = scanner.nextLine();
        if (categorie.isEmpty()) categorie = article.getCategorie();
        
        admin.modifierArticle(inventaire, id, nom, prix, stock, categorie);
        pauseEtContinuer();
    }
    
    private static void supprimerArticleAdmin(Administrateur admin) {
        System.out.print("\n   ID de l'article à supprimer: ");
        String id = scanner.nextLine();
        
        ArticleEpicerie article = inventaire.getArticle(id);
        
        if (article != null) {
            System.out.println("\n   Article: " + article);
            System.out.print("   Confirmer la suppression? (O/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("O")) {
                admin.supprimerArticle(inventaire, id);
            } else {
                System.out.println("   Suppression annulée");
            }
        } else {
            System.out.println("   ✗ Article non trouvé!");
        }
        pauseEtContinuer();
    }
    
    private static void afficherVentesJour() {
        List<Vente> ventes = caisse.getVentesJournalieres();
        
        if (ventes.isEmpty()) {
            System.out.println("\n   ℹ️ Aucune vente aujourd'hui");
        } else {
            System.out.println("\n ╔═══════════════════════════════════════════════════════════╗");
            System.out.println("   ║                  VENTES DU JOUR                           ║");
            System.out.println("   ╚═══════════════════════════════════════════════════════════╝");
            
            for (Vente vente : ventes) {
                System.out.println("\n   ID: " + vente.getIdVente() + " | Date: " + vente.getDate() + 
                                 " | Total: " + String.format("%.2f€", vente.getTotal()));
                System.out.println("   Articles vendus: " + vente.getArticles().size());
            }
            
            System.out.println("\n   " + "=".repeat(60));
            System.out.println("   Total des ventes: " + String.format("%.2f€", caisse.getTotalVentes()));
            System.out.println("   Nombre de transactions: " + ventes.size());
        }
        pauseEtContinuer();
    }
    
    private static void verifierArticlesPerimes() {
        List<ArticleEpicerie> perimes = inventaire.iterArticlesPerimes();
        
        if (perimes.isEmpty()) {
            System.out.println("\n   ✓ Aucun article périmé");
        } else {
            System.out.println("\n ╔═══════════════════════════════════════════════════════════╗");
            System.out.println("   ║              ⚠️  ARTICLES PÉRIMÉS  ⚠️                      ║");
            System.out.println("   ╚═══════════════════════════════════════════════════════════╝");
            
            for (ArticleEpicerie article : perimes) {
                System.out.println("   " + article);
            }
            
            System.out.println("\n   Total: " + perimes.size() + " article(s) périmé(s)");
        }
        
        System.out.println("\n ╔═══════════════════════════════════════════════════════════╗");
        System.out.println("   ║           ARTICLES PROCHES DE L'EXPIRATION                ║");
        System.out.println("   ╚═══════════════════════════════════════════════════════════╝");
        
        boolean aucunProche = true;
        for (ArticleEpicerie article : inventaire.getArticles().values()) {
            if (article instanceof ArticlePerissable) {
                ArticlePerissable perissable = (ArticlePerissable) article;
                if (!perissable.estPerime() && perissable.getJoursRestants() <= 7) {
                    System.out.println("   " + article);
                    aucunProche = false;
                }
            }
        }
        
        if (aucunProche) {
            System.out.println("   ✓ Aucun article proche de l'expiration");
        }
        
        pauseEtContinuer();
    }
    

    
    // methode pour mieux gerer le menu
    private static void initialiserDonnees() {
        ArticlePerissable lait = new ArticlePerissable(
            "A001", "Lait demi-écrémé 1L", 1.50, 50, "Produits laitiers", 
            LocalDate.now().plusDays(5)
        );
        
        ArticlePerissable pain = new ArticlePerissable(
            "A002", "Pain complet", 1.20, 30, "Boulangerie", 
            LocalDate.now().plusDays(2)
        );
        
        ArticleNonPerissable pates = new ArticleNonPerissable(
            "A003", "Pâtes Spaghetti 500g", 2.50, 100, "Épicerie salée", 365
        );
        
        ArticleNonPerissable riz = new ArticleNonPerissable(
            "A004", "Riz Basmati 1kg", 3.00, 80, "Épicerie salée", 730
        );
        
        ArticlePerissable fromage = new ArticlePerissable(
            "A005", "Fromage Camembert", 4.50, 25, "Produits laitiers", 
            LocalDate.now().plusDays(10)
        );
        
        ArticlePerissable yaourt = new ArticlePerissable(
            "A006", "Yaourt nature x4", 2.80, 40, "Produits laitiers", 
            LocalDate.now().plusDays(15)
        );
        
        ArticleNonPerissable huile = new ArticleNonPerissable(
            "A007", "Huile d'olive 1L", 8.50, 30, "Épicerie salée", 545
        );
        
        ArticlePerissable tomates = new ArticlePerissable(
            "A008", "Tomates fraîches 1kg", 3.20, 50, "Fruits et légumes", 
            LocalDate.now().plusDays(4)
        );
        
        inventaire.ajouterArticle(lait);
        inventaire.ajouterArticle(pain);
        inventaire.ajouterArticle(pates);
        inventaire.ajouterArticle(riz);
        inventaire.ajouterArticle(fromage);
        inventaire.ajouterArticle(yaourt);
        inventaire.ajouterArticle(huile);
        inventaire.ajouterArticle(tomates);
        
        Administrateur adminDefault = new Administrateur("idriss", "Admin Principal", "admin@store.com", "idriss123");
        admins.put("idriss", adminDefault);
    }
        
    private static int lireEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("   ✗ Veuillez entrer un nombre valide: ");
        }
        int valeur = scanner.nextInt();
        scanner.nextLine(); 
        return valeur;
    }
    
    private static double lireDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("   ✗ Veuillez entrer un nombre valide: ");
        }
        double valeur = scanner.nextDouble();
        scanner.nextLine(); 
        return valeur;
    }
    
    private static void pauseEtContinuer() {
        System.out.print("\n   Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    private static void afficherBanniere() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                              ║");
        System.out.println("║                Système de Gestion d'Épicerie                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
    
    // ==================== GESTION DES VENTES (ADMINISTRATEUR) ====================
    private static void gererVente(Administrateur admin) {
        Panier panierVente = new Panier();
        boolean continuer = true;
        
        while (continuer) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║              GESTION D'UNE VENTE                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. 📋 Voir les produits disponibles                         ║");
            System.out.println("║  2. 🔍 Rechercher un produit                                 ║");
            System.out.println("║  3. ➕ Ajouter un article au panier                          ║");
            System.out.println("║  4. 📦 Voir le panier actuel                                 ║");
            System.out.println("║  5. ✏️  Modifier le panier                                   ║");
            System.out.println("║  6. 💳 Finaliser la vente et payer                           ║");
            System.out.println("║  7. ❌ Annuler la vente                                      ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            
            int choix = lireEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    afficherProduitsDisponiblesVente();
                    break;
                case 2:
                    rechercherProduitVente();
                    break;
                case 3:
                    ajouterAuPanierVente(panierVente);
                    break;
                case 4:
                    panierVente.afficher();
                    pauseEtContinuer();
                    break;
                case 5:
                    modifierPanierVente(panierVente);
                    break;
                case 6:
                    finaliserVente(panierVente, admin);
                    continuer = false;
                    break;
                case 7:
                    System.out.println("\n   ℹ️ Vente annulée");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n   ✗ Choix invalide!");
            }
        }
    }
    
    private static void afficherProduitsDisponiblesVente() {
        System.out.println("\n ╔═════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("   ║                          PRODUITS DISPONIBLES                                                ║");
        System.out.println("   ╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
        
        boolean aucunProduit = true;
        for (ArticleEpicerie article : inventaire.getArticles().values()) {
            if (article.getQuantiteStock() > 0) {
                System.out.println("   " + article);
                aucunProduit = false;
            }
        }
        
        if (aucunProduit) {
            System.out.println("   ℹ️ Aucun produit disponible pour le moment");
        }
        
        pauseEtContinuer();
    }
    
    private static void rechercherProduitVente() {
        System.out.println("\n ╔═════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("   ║                        RECHERCHE DE PRODUIT                                                  ║");
        System.out.println("   ╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("   ║  1. Par nom                                                       ║");
        System.out.println("   ║  2. Par catégorie                                                 ║");
        System.out.println("   ╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
        
        int choix = lireEntier("Votre choix: ");
        
        List<ArticleEpicerie> resultats = new ArrayList<>();
        
        if (choix == 1) {
            System.out.print("\n   Nom du produit: ");
            String nom = scanner.nextLine();
            resultats = inventaire.rechercherParNom(nom);
        } else if (choix == 2) {
            System.out.print("\n   Catégorie: ");
            String categorie = scanner.nextLine();
            resultats = inventaire.rechercherParCategorie(categorie);
        }
        
        // Filtrer pour ne montrer que les produits en stock
        resultats.removeIf(article -> article.getQuantiteStock() <= 0);
        
        if (resultats.isEmpty()) {
            System.out.println("\n   ℹ️ Aucun produit disponible trouvé");
        } else {
            System.out.println("\n ╔═════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("   ║                            RÉSULTATS DE LA RECHERCHE                                        ║");
            System.out.println("   ╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
            for (ArticleEpicerie article : resultats) {
                System.out.println("   " + article);
            }
        }
        pauseEtContinuer();
    }
    
    private static void ajouterAuPanierVente(Panier panier) {
        System.out.print("\n   ID du produit à ajouter: ");
        String id = scanner.nextLine();
        
        ArticleEpicerie article = inventaire.getArticle(id);
        
        if (article == null) {
            System.out.println("\n   ✗ Produit non trouvé!");
            pauseEtContinuer();
            return;
        }
        
        if (article.getQuantiteStock() <= 0) {
            System.out.println("\n   ✗ Ce produit n'est plus en stock!");
            pauseEtContinuer();
            return;
        }
        
        System.out.println("\n   Produit: " + article.getNom());
        System.out.println("   Prix unitaire: " + String.format("%.2f€", article.getPrix()));
        System.out.println("   Stock disponible: " + article.getQuantiteStock());
        
        int quantite = lireEntier("\n   Quantité à ajouter: ");
        
        if (quantite <= 0) {
            System.out.println("\n   ✗ Quantité invalide!");
            pauseEtContinuer();
            return;
        }
        
        if (quantite > article.getQuantiteStock()) {
            System.out.println("\n   ✗ Stock insuffisant! Stock disponible: " + article.getQuantiteStock());
            pauseEtContinuer();
            return;
        }
        
        panier.ajouterArticle(article, quantite);
        System.out.println("\n   ✓ " + quantite + " x " + article.getNom() + " ajouté(s) au panier!");
        pauseEtContinuer();
    }
    
    private static void modifierPanierVente(Panier panier) {
        if (panier.estVide()) {
            System.out.println("\n   ℹ️ Le panier est vide");
            pauseEtContinuer();
            return;
        }
        
        panier.afficher();
        
        System.out.println("\n ╔══════════════════════════════════════════════════════════════╗");
        System.out.println("   ║              MODIFICATION DU PANIER                          ║");
        System.out.println("   ╠══════════════════════════════════════════════════════════════╣");
        System.out.println("   ║  1. Modifier la quantité d'un article                       ║");
        System.out.println("   ║  2. Supprimer un article                                    ║");
        System.out.println("   ║  3. Vider le panier                                         ║");
        System.out.println("   ╚══════════════════════════════════════════════════════════════╝");
        
        int choix = lireEntier("Votre choix: ");
        
        switch (choix) {
            case 1:
                System.out.print("\n   ID de l'article à modifier: ");
                String idModif = scanner.nextLine();
                
                ArticleEpicerie articleModif = inventaire.getArticle(idModif);
                if (articleModif == null) {
                    System.out.println("\n   ✗ Article non trouvé!");
                    pauseEtContinuer();
                    return;
                }
                
                // Trouver la quantité actuelle dans le panier
                int quantiteActuelle = 0;
                for (LignePanier ligne : panier.getLignes()) {
                    if (ligne.getArticle().getId().equals(idModif)) {
                        quantiteActuelle = ligne.getQuantite();
                        break;
                    }
                }
                
                if (quantiteActuelle == 0) {
                    System.out.println("\n   ✗ Cet article n'est pas dans le panier!");
                    pauseEtContinuer();
                    return;
                }
                
                System.out.println("   Quantité actuelle: " + quantiteActuelle);
                System.out.println("   Stock disponible: " + articleModif.getQuantiteStock());
                
                int nouvelleQuantite = lireEntier("   Nouvelle quantité: ");
                
                if (nouvelleQuantite <= 0) {
                    panier.supprimerArticle(idModif);
                    System.out.println("\n   ✓ Article supprimé du panier");
                } else if (nouvelleQuantite > articleModif.getQuantiteStock()) {
                    System.out.println("\n   ✗ Stock insuffisant! Stock disponible: " + articleModif.getQuantiteStock());
                } else {
                    panier.modifierQuantite(idModif, nouvelleQuantite);
                    System.out.println("\n   ✓ Quantité modifiée avec succès!");
                }
                break;
                
            case 2:
                System.out.print("\n   ID de l'article à supprimer: ");
                String idSuppr = scanner.nextLine();
                panier.supprimerArticle(idSuppr);
                System.out.println("\n   ✓ Article supprimé du panier");
                break;
                
            case 3:
                System.out.print("\n   Confirmer la suppression de tous les articles? (O/N): ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("O")) {
                    panier.vider();
                    System.out.println("\n   ✓ Panier vidé");
                } else {
                    System.out.println("\n   Opération annulée");
                }
                break;
                
            default:
                System.out.println("\n   ✗ Choix invalide!");
        }
        
        pauseEtContinuer();
    }
    
    private static void finaliserVente(Panier panier, Administrateur admin) {
        if (panier.estVide()) {
            System.out.println("\n   ℹ️ Le panier est vide. Ajoutez des produits avant de finaliser la vente.");
            pauseEtContinuer();
            return;
        }
        
        // Afficher le récapitulatif du panier
        panier.afficher();
        
        // Calculer le total avec TVA
        double sousTotal = panier.getTotal();
        double tva = sousTotal * 0.20;
        double totalAvecTVA = sousTotal + tva;
        
        System.out.println("\n   ╔════════════════════════════════════════════════════════════╗");
        System.out.println("   ║                    RÉCAPITULATIF                            ║");
        System.out.println("   ╠════════════════════════════════════════════════════════════╣");
        System.out.printf("   ║ Sous-total:                                 %10.2f€ ║%n", sousTotal);
        System.out.printf("   ║ TVA (20%%):                                  %10.2f€ ║%n", tva);
        System.out.printf("   ║ TOTAL À PAYER:                              %10.2f€ ║%n", totalAvecTVA);
        System.out.println("   ╚════════════════════════════════════════════════════════════╝");
        
        System.out.print("\n   Confirmer et finaliser la vente? (O/N): ");
        String confirmation = scanner.nextLine();
        
        if (!confirmation.equalsIgnoreCase("O")) {
            System.out.println("\n   Vente annulée");
            pauseEtContinuer();
            return;
        }
        
        // Vérifier à nouveau le stock avant le paiement
        boolean stockInsuffisant = false;
        StringBuilder messageErreur = new StringBuilder();
        
        for (LignePanier ligne : panier.getLignes()) {
            ArticleEpicerie article = inventaire.getArticle(ligne.getArticle().getId());
            if (article == null || !article.estDisponible(ligne.getQuantite())) {
                stockInsuffisant = true;
                if (article != null) {
                    messageErreur.append("\n   ✗ ").append(article.getNom())
                                 .append(": Stock insuffisant (disponible: ")
                                 .append(article.getQuantiteStock()).append(")");
                } else {
                    messageErreur.append("\n   ✗ Article introuvable dans l'inventaire");
                }
            }
        }
        
        if (stockInsuffisant) {
            System.out.println("\n   ⚠️  Impossible de finaliser la vente:");
            System.out.println(messageErreur.toString());
            pauseEtContinuer();
            return;
        }
        
        // Enregistrer la vente (cela mettra à jour le stock automatiquement)
        Vente vente = caisse.enregistrerVente(panier, inventaire);
        
        if (vente == null) {
            System.out.println("\n   ✗ Erreur lors de l'enregistrement de la vente!");
            pauseEtContinuer();
            return;
        }
        
        // Afficher la facture
        System.out.println("\n   ✓ Vente finalisée avec succès!");
        System.out.println("   Le stock a été mis à jour automatiquement.");
        System.out.println(vente.genererFacture());
        
        System.out.println("\n   ✓ Transaction enregistrée dans le système");
        pauseEtContinuer();
    }
    
    private static void afficherMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      MENU PRINCIPAL                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. 👨‍💼 Espace Administrateur                                 ║");
        System.out.println("║  2. 🚪 Quitter                                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}