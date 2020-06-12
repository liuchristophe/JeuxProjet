package la_revenche_des_loups.controleur;

import la_revenche_des_loups.modele.Bfs;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.vue.TourVue;

public class ActionControleur {
	private Jeu jeu;
	private Bfs bfs;
	
	public ActionControleur(Jeu jeu) {
		this.jeu = jeu;
		this.bfs = this.jeu.getBfs();
	}
	
	public void ajouteTourDansJeu(int x, int y, TourVue tourVue) {
		if(!this.jeu.getPartiEstLance()) {
			if(!this.bfs.verifieObstacle(x, y)) {
				if(this.jeu.limiterTours()) {
					if(!this.jeu.verifieTourAlentour(x, y, this.jeu.getTerrain().getDistanciationTour())) {	
						Tour tour = new Tour(this.jeu, x, y);
						this.jeu.ajouterTour(tour);
						tourVue.afficherTourVue(tour);
						this.bfs.ajoutObstacleTour(x, y);
						System.out.println("ActionControleur.ajouteTourDansJeu [ ajout d un tour ]");
						System.out.println("ActionControleur.ajouteTourDansJeu [ tour " + this.jeu.getNombreTours() + "/" + this.jeu.getLimiteTours() + " ]");
					}
					else {
						System.out.println("ActionControleur.ajouteTourDansJeu [ tour dans le rayon de 5 tuile ]");
					}
				}
				else {
					System.out.println("ActionControleur.ajouteTourDansJeu [ fin tour " + this.jeu.getNombreTours() + "/" + this.jeu.getLimiteTours() + " ]");
				}
			}
		}
	}
	
	
	
	
}
