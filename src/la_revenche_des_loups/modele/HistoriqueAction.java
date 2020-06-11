package la_revenche_des_loups.modele;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HistoriqueAction {

	// Test Affichage
	private ArrayList<String> listeAction;
	private ArrayList<String> listeDefile;
	private Jeu jeu;

	public HistoriqueAction(Jeu jeu) {
		// Liste fixe
		this.listeAction = new ArrayList<String>();
		this.listeAction.add("Un Loup attaque la Maison");
		this.listeAction.add("Un Loup attaque une Tour");
		this.listeAction.add("Une Tour se défend");
		this.listeAction.add("La Maison se défend");
		this.listeAction.add("Un Loup est mort");
		this.listeAction.add("Une Tour a été détruite");

		// Liste défilante
		this.listeDefile = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			this.listeDefile.add("");
		}

		this.jeu = jeu;
	}

	public ArrayList<String> getListeAction() {
		return this.listeAction;
	}

	public ArrayList<String> getListeDefile() {
		return this.listeDefile;
	}

	public void miseAJourListeDefile(int nouvelleAction) {
		for (int i = this.listeDefile.size() - 1; i > 0; i--) {
			this.listeDefile.set(i, this.listeDefile.get(i - 1));
		}
		this.listeDefile.set(0, this.listeAction.get(nouvelleAction));
	}
	
	public void reinitialiserListeDefile() {
		for (int i = 0; i < 5; i++) {
			this.listeDefile.add("");
		}
	}

}
