package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Loup;

public class LoupVue {
	private Pane panneauJeu;
	private Image loupVue;
	
	public LoupVue(Pane panneau) {
		this.panneauJeu = panneau;
		this.loupVue = null;
        try {
        	loupVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/Loup_Gris.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public LoupVue getLoupVue() {
		return this;
	}
	
	public void afficherLoupVue(Loup loup) {
		ImageView imageview = (ImageView) this.panneauJeu.lookup("#"+loup.getId());
		if(imageview == null) {
			creerLoupVue(loup);
		}
		else {
			imageview.setTranslateX(loup.getX()*12);		
			imageview.setTranslateY(loup.getY()*12);		
		}
	}
	
	private void creerLoupVue(Loup loup) {
		ImageView imageview = new ImageView(this.loupVue);
		imageview.setFitWidth(24);
		imageview.setFitHeight(24);
		imageview.setTranslateX(loup.getX()*12);		
		imageview.setTranslateY(loup.getY()*12);		
		imageview.setId(loup.getId());
		this.panneauJeu.getChildren().add(imageview);
	}
	
	
}
