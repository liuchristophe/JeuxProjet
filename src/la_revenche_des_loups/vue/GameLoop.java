package la_revenche_des_loups.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;

public class GameLoop {

	private Timeline gameLoop;
	private Jeu jeu;
	private LoupVue loupVue;
	private Loup loup;
	private int nbFrame;

	public GameLoop(Loup loup, LoupVue loupVue, Jeu jeu) {
		this.jeu = jeu;
		this.loup = loup;
		this.loupVue = loupVue;
	}

	public void initAnimation() {
		gameLoop = new Timeline();
		nbFrame = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.10), (ev -> {
			// Le loup agit à chaque frame jusqu'a que la gameloop se stop
			if (nbFrame == 60 || this.jeu.finPartie()) {
				gameLoop.stop();
			} else {
				loupVue.afficherLoupVue(this.loup);
				this.jeu.agir();
			}
			nbFrame++;
		}));
		gameLoop.getKeyFrames().add(kf);
	}

	// La maison et les tours agissent toutes les 2 frames

	/*
	public void défenseTourAnimation() {
		if (nbFrame % 2 == 0) {
			this.jeu.agitTour();
		}
	}
*/
	/*
	public void défenseMaisonAnimation() {
		if (nbFrame % 2 == 0) {
			this.jeu.agitMaison();
		}
	}*/

	public void lancerAnimation() {
		initAnimation();
		gameLoop.play();
	}

}
