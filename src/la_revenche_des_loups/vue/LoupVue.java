package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.exception.MonException;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Blanc;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Gris;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Fantome;

public class LoupVue {
	private Pane panneauJeu;
	private Image loupBlancVue;
	private Image loupGrisVue;
	private Image loupFantomeVue;
	
	public LoupVue(Pane panneau) throws MonException {
		this.panneauJeu = panneau;
		this.loupBlancVue = null;
		this.loupGrisVue = null;
		this.loupFantomeVue = null;
        try {
           	loupBlancVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/Loup_Blanc.png"));
           	loupGrisVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/Loup_Gris.png"));
           	loupFantomeVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/Loup_Fantome.png"));
        } catch (FileNotFoundException e) {
            throw new MonException("Fichier non trouvé");
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
			if(loup instanceof Loup_Blanc) {
				imageview.setTranslateX(loup.getX()*12);		
				imageview.setTranslateY((loup.getY()*12)-24);	
			}
			else {
				imageview.setTranslateX(loup.getX()*12);		
				imageview.setTranslateY((loup.getY()*12)-12);	
			}
		}
	}
	
	private void creerLoupVue(Loup loup) {
		Image loupVue = null;
		ImageView imageview = null;

		if(loup instanceof Loup_Blanc) {
			loupVue = this.loupBlancVue;
			imageview = new ImageView(loupVue);
			imageview.setFitWidth(36);
			imageview.setFitHeight(36);
			imageview.setTranslateX(loup.getX()*12);		
			imageview.setTranslateY((loup.getY()*12)-30);
		}
		else if(loup instanceof Loup_Gris) {
			loupVue = this.loupGrisVue;
			imageview = new ImageView(loupVue);
			imageview.setFitWidth(24);
			imageview.setFitHeight(24);
			imageview.setTranslateX(loup.getX()*12);		
			imageview.setTranslateY((loup.getY()*12)-15);
		}
		else if(loup instanceof Loup_Fantome) {
			loupVue = this.loupFantomeVue;
			imageview = new ImageView(loupVue);
			imageview.setFitWidth(24);
			imageview.setFitHeight(24);
			imageview.setTranslateX(loup.getX()*12);		
			imageview.setTranslateY((loup.getY()*12)-15);
		}	
		imageview.setId(loup.getId());
		this.panneauJeu.getChildren().add(imageview);
	}
	
	public void supprimerLoup(Loup loup) {
        this.panneauJeu.getChildren().remove(this.panneauJeu.lookup("#" + loup.getId()));
    }
}
