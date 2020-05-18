package la_revenche_des_loups.vue;

import javafx.animation.Timeline;

public class GameLoop {

	private Timeline gameLoop;
	
	private int temps;
	
	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
	}
	
	private void lancerAnimation() {
		initAnimation();
		gameLoop.play();
	}
	
}
