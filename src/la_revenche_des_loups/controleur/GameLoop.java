package la_revenche_des_loups.controleur;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import la_revenche_des_loups.modele.Bfs;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.vue.HistoriqueActionVue;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.TirVue;

public class GameLoop {

	private Timeline gameLoop;
	private Jeu jeu;
	private int nbFrame;
	private int nbLoups;
	private Bfs bfs;
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
		this.nbLoups = 1;//////////10 par default
		this.panneau = panneau;
		this.numVague = 1;
		this.bfs = new Bfs(this.jeu);
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

		KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ev -> {
			// Le loup agit � chaque frame jusqu'a que la gameloop se stop
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
				this.loupVue.supprimerLoup(this.jeu.getListeLoups().get(i));
			}
		}
	}

	public void supprimerTour() {
		ArrayList<Tour> toursMorts = new ArrayList<Tour>();
		for (int i = 0; i < this.jeu.getListeTours().size(); i++) {
			Tour tour = this.jeu.getListeTours().get(i);
			if (!tour.estVivant()) {
				this.panneau.getChildren().remove(this.panneau.lookup("#" + this.jeu.getListeTours().get(i).getId()));
				toursMorts.add(this.jeu.getListeTours().get(i));
				this.jeu.miseAJourToursLimite();
				this.jeu.getBfs().enleveObstacleTour(tour.getX(), tour.getY());
			}
		}
		for(Tour t : toursMorts) {
			this.jeu.retirerTour(t);
			this.bfs.enleveObstacleTour(t.getX(), t.getY());
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
