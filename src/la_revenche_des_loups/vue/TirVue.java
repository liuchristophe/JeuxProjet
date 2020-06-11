package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import la_revenche_des_loups.modele.Jeu;

public class TirVue {

	private Jeu jeu;
	private Pane panneauJeu;
	private Image imageImpact;

	public TirVue(Jeu jeu, Pane panneau) {
		this.jeu = jeu;
		this.panneauJeu = panneau;

		this.imageImpact = null;
		try {
			this.imageImpact = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/explosion.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void affichageTir() {

		System.out.println("CONTENU PANNEAU");
		for (int i = 0; i < this.panneauJeu.getChildren().size(); i++) {
			System.out.println(this.panneauJeu.getChildren().get(i).getId());
		}
		System.out.println(".");
		System.out.println("CONTENU LISTE TIR");
		for (int i = 0; i < this.jeu.getListeTirs().size(); i++) {
			System.out.println(this.jeu.getListeTirs().get(i).getIdTir());
		}
		System.out.println(".");
		for (int i = 0; i < this.jeu.getListeTirs().size(); i++) {
			System.out.println("Gestion du tir " + this.jeu.getListeTirs().get(i).getIdTir());
			if (this.panneauJeu.lookup("#" + this.jeu.getListeTirs().get(i).getIdTir()) == null) {
				System.out.println("#" + this.jeu.getListeTirs().get(i).getIdTir() + "est nul");
				/*
				 * Circle tir = new Circle(4); tir.setFill(Color.RED);
				 * tir.translateXProperty().bind(this.jeu.getListeTirs().get(i).getPositionX());
				 * tir.translateYProperty().bind(this.jeu.getListeTirs().get(i).getPositionY());
				 * tir.setId(this.jeu.getListeTirs().get(i).getIdTir());
				 * System.out.println("Lookup : "+this.panneauJeu.lookup("#"+this.jeu.
				 * getListeTirs().get(i).getIdTir())); this.panneauJeu.getChildren().add(tir);
				 * System.out.println("creation du tir "+tir.getId());
				 */
				ImageView impact = new ImageView(this.imageImpact);
				impact.setFitWidth(20);
				impact.setFitHeight(15);
				impact.translateXProperty().bind(this.jeu.getListeTirs().get(i).getPositionX());
				impact.translateYProperty().bind(this.jeu.getListeTirs().get(i).getPositionY());
				impact.setId(this.jeu.getListeTirs().get(i).getIdTir());
				System.out
						.println("Lookup : " + this.panneauJeu.lookup("#" + this.jeu.getListeTirs().get(i).getIdTir()));
				this.panneauJeu.getChildren().add(impact);
				System.out.println("creation de l'impact " + impact.getId());
			} else {
				System.out.println("Suppression du tir " + this.jeu.getListeTirs().get(i).getIdTir());
				this.panneauJeu.getChildren()
						.remove(this.panneauJeu.lookup("#" + this.jeu.getListeTirs().get(i).getIdTir()));
				this.jeu.getListeTirs().remove(i);
				i--;
			}
		}
	}

	/*
	 * public void supprimerTir() { ImageView impact = new ImageView();
	 * impact.setFitWidth(12); impact.setFitHeight(12);
	 * impact.translateXProperty().bind(this.jeu.getListeTirs().get(i).getPositionX(
	 * ));
	 * impact.translateYProperty().bind(this.jeu.getListeTirs().get(i).getPositionY(
	 * )); impact.setId(this.jeu.getListeTirs().get(i).getIdTir());
	 * System.out.println("Lookup : "+this.panneauJeu.lookup("#"+this.jeu.
	 * getListeTirs().get(i).getIdTir()));
	 * this.panneauJeu.getChildren().add(impact);
	 * System.out.println("creation de l'impact "+impact.getId()); }
	 */
}
