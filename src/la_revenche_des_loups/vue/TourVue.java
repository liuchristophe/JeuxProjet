package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.modele.Tour_Bois;
import la_revenche_des_loups.modele.Tour_Paille;

public class TourVue {
	
	private Pane panneauJeu;
	private Image tourVue;

	public TourVue(Pane panneau, Tour t) {
        this.panneauJeu = panneau;
        this.tourVue = null;
        try {
            if(t instanceof Tour_Paille) {
            tourVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/tour_paille.png"));
            }
            else if(t instanceof Tour_Bois) {
                tourVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/tour_bois.png"));
            }
            else {
                tourVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/tour_brique.png"));
            }

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
