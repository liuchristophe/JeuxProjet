package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;

public class LoupVue {
	private TilePane panneauJeu;
	private Terrain terrain;
	
	public LoupVue(TilePane panneau, Terrain terrain) {
		this.panneauJeu = panneau;
		this.terrain = terrain;
	}
	
	public void afficherLoupVue(Loup loup) {
		Image loupVue = null;
        try {
        	loupVue = new Image(new FileInputStream("src/la_revenche_des_loups/modele/Loup_Gris.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		ImageView imageview = new ImageView(loupVue);
		imageview.setFitWidth(12);
		imageview.setFitHeight(12);
		imageview.setTranslateX(loup.getX()*12);
		imageview.setTranslateY(loup.getY()*12);
		System.out.println("");
		this.panneauJeu.getChildren().add(imageview);
	}
}
