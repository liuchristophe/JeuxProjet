package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Maison;

public class MaisonVue {

	private Pane panneauJeu;
	private Image maisonVue;

	private ColorAdjust colorAdjust;
	private Label info;

	
	public MaisonVue(Pane panneau, Label infoActeur) {
		this.panneauJeu = panneau;
		this.maisonVue = null;
		try {
			this.maisonVue = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/chateau3.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.info = infoActeur;
		//test changement couleur
		/*
		 //Instantiating the ColorAdjust class 
        this.colorAdjust = new ColorAdjust(); 
        //Setting the contrast value 
        //colorAdjust.setContrast(0.4);
        //Setting the hue value 
        //colorAdjust.setHue(-0.05);
        //Setting the brightness value 
        colorAdjust.setBrightness(0.7); 
        //Setting the saturation value 
        colorAdjust.setSaturation(0.8); 
        imageview.setEffect(this.colorAdjust);
        */
		
	}

	public void creerMaisonVue(Maison maison) {
		ImageView imageview = new ImageView(this.maisonVue);
		imageview.setFitWidth(240);
		imageview.setFitHeight(390);
		imageview.setTranslateX(maison.getX()-15);
		imageview.setTranslateY(maison.getYInf() * 9);
		this.panneauJeu.getChildren().add(imageview);
		imageview.setOnMouseClicked(e-> this.info.setText(maison.toString()));
	}
}
