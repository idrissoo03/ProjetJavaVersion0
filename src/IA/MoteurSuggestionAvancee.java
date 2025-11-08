package IA;
import InventaireArticle.*;
import Recette.*;

import java.util.ArrayList;
import java.util.List;

public class MoteurSuggestionAvancee extends MoteurSuggestion implements SuggestionIA {
    public MoteurSuggestionAvancee(String moteur) {
        super(moteur);
    }
    
    @Override
    public List<Recette> suggererRecettes(Inventaire inventaire) {
        List<Recette> recettes = new ArrayList<>();
        Recette r = new Recette("Salade fraîche", 15);
        recettes.add(r);
        return recettes;
    }
    
    @Override
    public List<ArticleEpicerie> suggererParPeremption(List<ArticleEpicerie> peremptions) {
        return new ArrayList<>(peremptions);
    }
    
    @Override
    public List<ArticleEpicerie> suggererPromotions(List<ArticleEpicerie> promotions) {
        return new ArrayList<>(promotions);
    }
}