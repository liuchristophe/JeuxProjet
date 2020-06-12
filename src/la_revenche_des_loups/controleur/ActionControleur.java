package la_revenche_des_loups.controleur;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Bfs;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.modele.ClasseTour.Tour_Bois;
import la_revenche_des_loups.modele.ClasseTour.Tour_Brique;
import la_revenche_des_loups.modele.ClasseTour.Tour_Paille;
import la_revenche_des_loups.vue.TourVue;

public class ActionControleur {
	private Jeu jeu;
	private Bfs bfs;
	private TourVue tourVue;

	public ActionControleur(Jeu jeu) {
		this.jeu = jeu;
		this.bfs = this.jeu.getBfs();
		this.bfs = new Bfs(jeu);
	}

	public void ajouteTourDansJeu(int x, int y, int typeTour, Pane tableDeJeu) {
		boolean peuxPayer = false;
		if (!this.jeu.getPartiEstLance()) {
			if (!this.bfs.verifieObstacle(x, y)) {
				if (this.jeu.limiterTours()) {
					switch (typeTour) {
					case 1:
						if (this.jeu.getMonnaie() >= Tour_Paille.getPrix()) {
							peuxPayer = true;
						}
						break;

					case 2:
						if (this.jeu.getMonnaie() >= Tour_Bois.getPrix()) {
							peuxPayer = true;
						}
						break;

					case 3:
						if (this.jeu.getMonnaie() >= Tour_Brique.getPrix()) {
							peuxPayer = true;
						}
						break;
					}

					if (peuxPayer) {
						if (!this.jeu.verifieTourAlentour(x, y, 5)) {
							Tour tour;
							if (typeTour == 1) {
								tour = new Tour_Paille(this.jeu, x, y);
							} else if (typeTour == 2) {
								tour = new Tour_Bois(this.jeu, x, y);
							} else {
								tour = new Tour_Brique(this.jeu, x, y);
							}
							this.jeu.ajouterTour(tour);
							tourVue = new TourVue(tableDeJeu, tour);
							tourVue.afficherTourVue(tour);
						}
					}
				}
			}
		}
	}

	public void tourSelection(int typeTour, Label tourSelection) {
		if (typeTour == 1) {
			tourSelection.setText("PAILLE");
		} else if (typeTour == 2) {
			tourSelection.setText("BOIS");
		} else {
			tourSelection.setText("BRIQUE");
		}
	}
}
