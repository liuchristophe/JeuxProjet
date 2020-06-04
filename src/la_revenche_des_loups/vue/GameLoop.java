package la_revenche_des_loups.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import la_revenche_des_loups.modele.Jeu;

public class GameLoop {

	private Timeline gameLoop;
	private Jeu jeu;
	private int nbFrame;
	private int nbLoups;
	private LoupVue loupVue;

	public GameLoop(Jeu jeu, Pane panneau) {
		this.jeu = jeu;
		this.loupVue = new LoupVue(panneau);
		this.nbLoups = 10;
	}

	public void initAnimation() {
		gameLoop = new Timeline();
		nbFrame = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.10), (ev -> {
			// Le loup agit à chaque frame jusqu'a que la gameloop se stop
			if (this.jeu.finPartie() || this.jeu.finVague(nbLoups)) {
				gameLoop.stop();
			} else {
				vague();
			}
			nbFrame++;
		}));
		gameLoop.getKeyFrames().add(kf);
	}
	
	public void nouveauLoup() {
		if (nbFrame % 10 == 0 && this.jeu.getListeLoups().size()<nbLoups) {
			this.jeu.ajouterLoup();
		}
	}
	
	public void vague() {
		nouveauLoup();
		for (int i = 0; i < this.jeu.getListeLoups().size(); i++) {
			System.out.println("je place le loup n "+this.jeu.getListeLoups().get(i).getId());
			this.loupVue.afficherLoupVue(this.jeu.getListeLoups().get(i));
		}
		this.jeu.agir();
	}
	

	public void lancerAnimation() {
		System.out.println("je suis dans l'animation");
		initAnimation();
		gameLoop.play();
	}

}
