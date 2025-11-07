package Recette;

import java.util.ArrayList;
import java.util.List;

import InventaireArticle.ArticleEpicerie;

public class Recette {
    private String nom;
    private List<ArticleEpicerie> ingredients;
    private int tempsPreparation;
    
    public Recette(String nom, int tempsPreparation) {
        this.nom = nom;
        this.tempsPreparation = tempsPreparation;
        this.ingredients = new ArrayList<>();
    }
    
    public String getNom() { return nom; }
    public List<ArticleEpicerie> getIngredients() { return ingredients; }
    public int getTempsPreparation() { return tempsPreparation; }
    
    public void ajouterIngredient(ArticleEpicerie ingredient) {
        ingredients.add(ingredient);
    }
}