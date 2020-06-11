package la_revenche_des_loups.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.vue.HistoriqueActionVue;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.TirVue;

public class GameLoop {

	private Timeline gameLoop;
	private Jeu jeu;
	private int nbFrame;
	private int nbLoups;
	private LoupVue loupVue;
	private Pane panneau;
	private int numVague;
	
	//test bouton pause
	private boolean gameloopEnCour;

	// test tir
	private TirVue tirVue;

	private HistoriqueActionVue historiqueVue;

	public GameLoop(Jeu jeu, Pane panneau, HistoriqueActionVue historiqueVue) {
		this.jeu = jeu;
		this.loupVue = new LoupVue(panneau);
		this.nbLoups = 10;
		this.panneau = panneau;
		this.numVague = 1;
		this.historiqueVue = historiqueVue;

		// test tir
		this.tirVue = new TirVue(this.jeu, this.panneau);
		
		//test bouton pause
		this.gameloopEnCour = false;
	}

	public void initAnimation() {
		gameLoop = new Timeline();
		nbFrame = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.2), (ev -> {
			// Le loup agit à chaque frame jusqu'a que la gameloop se stop
			if (this.jeu.finPartie() || this.jeu.finVague(nbLoups)) {
				this.historiqueVue.affichageFinParti();
				this.jeu.changeStatutParti();
				this.gameloopEnCour = false;
				gameLoop.stop();
				this.numVague++;
			} else {
				this.jeu.vague(nbFrame, loupVue, nbLoups);
				this.tirVue.affichageTir();
				supprimerLoup();
				supprimerTour();
				this.historiqueVue.affichageHistorique();
			}
			nbFrame++;
		}));
		gameLoop.getKeyFrames().add(kf);
		
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
		if (this.numVague <= 3) {
			this.nbLoups += this.numVague * 10;
			this.jeu.changeStatutParti();
			this.gameloopEnCour = true;
			gameLoop.play();
		} else {
			System.out.println("GameLoop.lancerVague [ Aucune vague restante ]");
		}
	}

	public int getNumVague() {
		return this.numVague;
	}

	public void lancerAnimation() {
		System.out.println("GameLoop.lancerAnimation [ je suis dans l'animation ]");
		initAnimation();
		this.jeu.changeStatutParti();
		this.gameloopEnCour = true;
		gameLoop.play();
	}
	
	//test bouton pause
	public void pause() {
		if (this.gameloopEnCour) {
			this.gameLoop.stop();
		}
		else{
			this.gameLoop.play();
		}
	}

}
