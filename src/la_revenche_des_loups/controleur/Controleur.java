package la_revenche_des_loups.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Maison;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.vue.BFSVue;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.MaisonVue;
import la_revenche_des_loups.vue.TerrainVue;
import la_revenche_des_loups.vue.TourVue;

public class Controleur implements Initializable {

	private Terrain terrain;
	private Jeu jeu;
	private Maison maison;
	private TerrainVue terrainVue;
	private MaisonVue maisonVue;
	private GameLoop gameloop;
	private TourVue tourVue;
	private BFSVue bfsVue;
	@FXML
	private TilePane tilePane;
	@FXML
    private TilePane testBFS; //juste pour essaie
	@FXML
	private Pane tableDeJeu;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain = new Terrain();
		this.jeu = new Jeu(terrain);
		this.terrainVue = new TerrainVue(this.tilePane, jeu.getTerrain());
		this.terrainVue.afficherTerrainVue(21, 21, 12);
		
		// ajout de la maison et du loup dans le jeu
		this.maison = new Maison(jeu);
		
		// ajout des sprites
		this.maisonVue = new MaisonVue(this.tableDeJeu, jeu.getTerrain());
		this.maisonVue.creerMaisonVue(maison);
		this.tourVue = new TourVue(this.tableDeJeu, this.jeu);
		
		// initialisation de la gameloop
		this.gameloop = new GameLoop(this.jeu, this.tableDeJeu);
		
		//initialisation BFS
		//this.bfsVue = new BFSVue(testBFS, this.jeu);
		//this.bfsVue.afficherBFSVue(2, 2, 12);
		
	}

	@FXML
	void vagueSuivante(ActionEvent event) {
		this.gameloop.lancerVague();
	}
	
	@FXML
	void lancerPartie(ActionEvent event) {
		this.gameloop.lancerAnimation();
	}

	@FXML
	void quitterJeu(ActionEvent event) {
		
		this.jeu.reintialiser();
		
		//System.out.println(this.jeu.bfs(99, 25, 2555));
	}

	@FXML
	public void cliqueTableDeJeu(MouseEvent click) {
		int x = ((int) click.getX()) / 12 - 1;
		int y = ((int) click.getY()) / 12 - 1;
		if(!this.jeu.verifieObstacle(x, y)) {
			if(this.jeu.limiterTours()) {
				if(!this.jeu.verifieTourAlentour(x, y, 5)) {	
					Tour tour = new Tour(this.jeu, x, y);
					this.jeu.ajouterTour(tour);
					this.tourVue.afficherTourVue(tour);
					this.jeu.ajoutObstacleTour(x, y);
					//this.testBFS.getChildren().clear();
					//this.bfsVue.afficherBFSVue(2, 1, 12);
					System.out.println("Controleur.cliqueTableDeJeu [ ajout d un tour ]");
					System.out.println("Controleur.cliqueTableDeJeu [ tour " + this.jeu.getNombreTours() + "/" + this.jeu.getLimiteTours() + " ]");
				}
				else {
					System.out.println("Controleur.cliqueTableDeJeu [ tour dans le rayon de 5 tuile ]");
				}
			}
			else {
				System.out.println("Controleur.cliqueTableDeJeu [ fin tour " + this.jeu.getNombreTours() + "/" + this.jeu.getLimiteTours() + " ]");
			}
		}
	}
}
