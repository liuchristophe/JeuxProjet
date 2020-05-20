package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;

public class LoupVue {
	private Pane panneauJeu;
	private Terrain terrain;
	private Image loupVue;
	
	public LoupVue(Pane panneau, Terrain terrain) {
		this.panneauJeu = panneau;
		this.terrain = terrain;
		this.loupVue = null;
        try {
        	loupVue = new Image(new FileInputStream("src/la_revenche_des_loups/modele/Loup_Gris.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void afficherLoupVue(Loup loup) {
		ImageView imageview = (ImageView) this.panneauJeu.lookup("#"+loup.getId());
		if(imageview == null) {
			creerLoupVue(loup);
		}
		else {
			imageview.setTranslateX(loup.getX()*12);		//A modif
			imageview.setTranslateY(loup.getY()*12);		//A modif
		}
	}
	
	private void creerLoupVue(Loup loup) {
		ImageView imageview = new ImageView(this.loupVue);
		imageview.setFitWidth(24);		//A modif
		imageview.setFitHeight(24);		//A modif
		imageview.setTranslateX(loup.getX()*12);		//A modif
		imageview.setTranslateY(loup.getY()*12);		//A modif
		imageview.setId(loup.getId());
		this.panneauJeu.getChildren().add(imageview);
	}
	
	
}
