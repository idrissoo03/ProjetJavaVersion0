package IA;
import UtilisateurApplication.*;
import java.util.ArrayList;
import java.util.List;

public class MoteurSuggestion {
    private String moteur;
    
    public MoteurSuggestion(String moteur) {
        this.setMoteur(moteur);
    }
    
    public List<String> getSuggestionsClient(Client client, List<String> historique) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("Produits fréquemment achetés");
        suggestions.add("Offres personnalisées");
        return suggestions;
    }
    
    public List<String> getSuggestionsAdmin(List<String> historique) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("Réapprovisionner articles populaires");
        suggestions.add("Promotions recommandées");
        return suggestions;
    }

	public String getMoteur() {
		return moteur;
	}

	public void setMoteur(String moteur) {
		this.moteur = moteur;
	}
}
