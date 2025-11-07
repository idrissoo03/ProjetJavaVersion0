package IA;
import java.util.List;

import InventaireArticle.*;
import Recette.*;

interface SuggestionIA {
    List<Recette> suggererRecettes(Inventaire inventaire);
    List<ArticleEpicerie> suggererParPeremption(List<ArticleEpicerie> peremptions);
    List<ArticleEpicerie> suggererPromotions(List<ArticleEpicerie> promotions);
}
