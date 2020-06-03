package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;

public class TourVue {
	private Jeu jeu;
	private Pane panneauJeu;
	private Image tourVue;

	public TourVue(Pane panneau, Jeu jeu) {
		this.jeu = jeu;
		this.panneauJeu = panneau;
		this.tourVue = null;
		try {
			tourVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/tour_brique.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void afficherTourVue(Tour tour) {
		ImageView imageview = (ImageView) this.panneauJeu.lookup("#" + tour.getId());
		if (imageview == null) {
			creerTourVue(tour);
		} else {
			imageview.setTranslateX(tour.getX() * 12);
			imageview.setTranslateY(tour.getY() * 12);
		}
	}

	public void creerTourVue(Tour tour) {
		ImageView imageview = new ImageView(this.tourVue);
		imageview.setFitWidth(12*tour.getTaille());
		imageview.setFitHeight(12*tour.getTaille());
		imageview.setTranslateX(tour.getX() * 12);
		imageview.setTranslateY(tour.getY() * 12);
		imageview.setId(tour.getId());
		this.panneauJeu.getChildren().add(imageview);
	}
}
