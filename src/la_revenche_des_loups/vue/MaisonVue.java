package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Maison;

public class MaisonVue {

	private Pane panneauJeu;
	private Image maisonVue;

	
	public MaisonVue(Pane panneau) {
		this.panneauJeu = panneau;
		this.maisonVue = null;
		try {
			this.maisonVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/chateauFinal2.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void creerMaisonVue(Maison maison) {
		ImageView imageview = new ImageView(this.maisonVue);
		imageview.setFitWidth(250);
		imageview.setFitHeight(460);
		imageview.setTranslateX(maison.getX()-50);
		imageview.setTranslateY(maison.getYInf() +42);
		this.panneauJeu.getChildren().add(imageview);
	}
}
