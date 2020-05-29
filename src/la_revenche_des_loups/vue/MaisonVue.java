package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Maison;
import la_revenche_des_loups.modele.Terrain;

public class MaisonVue {

	private Pane panneauJeu;
	private Image maisonVue;

	public MaisonVue(Pane panneau, Terrain terrain) {
		this.panneauJeu = panneau;
		this.maisonVue = null;
		try {
			this.maisonVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/chateau3.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void creerMaisonVue(Maison maison) {
		ImageView imageview = new ImageView(this.maisonVue);
		imageview.setFitWidth(240);
		imageview.setFitHeight(390);
		imageview.setTranslateX(maison.getX()-15);
		imageview.setTranslateY(maison.getYInf() * 9);
		this.panneauJeu.getChildren().add(imageview);
	}
}
