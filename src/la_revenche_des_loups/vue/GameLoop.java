package la_revenche_des_loups.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import la_revenche_des_loups.modele.Loup;

public class GameLoop {

	private Timeline gameLoop;
	private LoupVue loupVue;
	private Loup loup;

	private int nbFrame;

	public GameLoop(Loup loup, LoupVue loupVue) {
		this.loup = loup;
		this.loupVue = loupVue;
	}

	public void initAnimation() {
		gameLoop = new Timeline();
		nbFrame = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.10), (ev -> {
			if (nbFrame == 30) {
				gameLoop.stop();
			} else {
				loupVue.afficherLoupVue(this.loup);
				this.loup.seDeplace();

			}
			nbFrame++;
		}));
		gameLoop.getKeyFrames().add(kf);
	}

	public void lancerAnimation() {
		initAnimation();
		gameLoop.play();
	}

}
