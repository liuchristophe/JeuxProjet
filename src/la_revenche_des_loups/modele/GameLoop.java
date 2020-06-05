package la_revenche_des_loups.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import la_revenche_des_loups.vue.LoupVue;

public class GameLoop {

	private Timeline gameLoop;
	private Jeu jeu;
	private int nbFrame;
	private int nbLoups;
	private LoupVue loupVue;
	private Pane panneau;
	private int numVague;

	public GameLoop(Jeu jeu, Pane panneau) {
		this.jeu = jeu;
		this.loupVue = new LoupVue(panneau);
		this.nbLoups = 10;
		this.panneau = panneau;
		this.numVague = 1;
	}

	public void initAnimation() {
		gameLoop = new Timeline();
		nbFrame = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.10), (ev -> {
			// Le loup agit à chaque frame jusqu'a que la gameloop se stop
			if (this.jeu.finPartie() || this.jeu.finVague(nbLoups)) {
				gameLoop.stop();
				this.numVague++;
			} else {
				vague();
			}
			nbFrame++;
		}));
		gameLoop.getKeyFrames().add(kf);
	}

	public void nouveauLoup() {
		if (nbFrame % 10 == 0 && this.jeu.getListeLoups().size() < nbLoups) {
			this.jeu.ajouterLoup();
		}
	}

	public void vague() {
		nouveauLoup();
		for (int i = 0; i < this.jeu.getListeLoups().size(); i++) {
			this.loupVue.afficherLoupVue(this.jeu.getListeLoups().get(i));
		}
		this.jeu.agir();
		supprimerLoup();
		supprimerTour();
	}

	public void supprimerLoup() {
		for (int i = 0; i < this.jeu.getListeLoups().size(); i++) {
			if (!this.jeu.getListeLoups().get(i).estVivant()) {
				this.panneau.getChildren().remove(this.panneau.lookup("#" + this.jeu.getListeLoups().get(i).getId()));
			}
		}
	}

	public void supprimerTour() {
		for (int i = 0; i < this.jeu.getListeTours().size(); i++) {
			if (!this.jeu.getListeTours().get(i).estVivant()) {
				this.panneau.getChildren().remove(this.panneau.lookup("#" + this.jeu.getListeTours().get(i).getId()));
			}
		}
	}

	public void lancerVague() {
		if (this.numVague <=3) {
			this.nbLoups += this.numVague*10;
			gameLoop.play();
		}
		else {
			System.out.println("GameLoop.lancerVague [ Aucune vague restante ]");
		}
	}
	
	public void lancerAnimation() {
		System.out.println("GameLoop.lancerAnimation [ je suis dans l'animation ]");
		initAnimation();
		gameLoop.play();
	}

}
