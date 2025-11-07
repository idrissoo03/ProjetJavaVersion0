package IA;
import InventaireArticle.*;
import Recette.*;
import java.util.ArrayList;
import java.util.List;

class MoteurSuggestionRegles extends MoteurSuggestion {
    public MoteurSuggestionRegles(String moteur) {
        super(moteur);
    }
    
    public List<Recette> suggererRecettes(Inventaire inventaire) {
        return new ArrayList<>();
    }
    
    public List<ArticleEpicerie> suggererParPeremption(List<ArticleEpicerie> peremptions) {
        return new ArrayList<>();
    }
}