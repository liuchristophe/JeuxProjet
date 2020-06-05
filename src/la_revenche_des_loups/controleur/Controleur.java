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
//	private BFSVue bfsVue;
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
<<<<<<< HEAD
//		this.bfsVue = new BFSVue(testBFS, this.jeu);
//		this.bfsVue.afficherBFSVue(2, 2, 12);
//		this.gameloop = new GameLoop(this.loup, this.loupVue, this.jeu);
=======
		//this.bfsVue = new BFSVue(testBFS, this.jeu);
		//this.bfsVue.afficherBFSVue(2, 2, 12);
		
>>>>>>> f3b4f49a28ad27cc925cf365c8d87a9dbad97be9
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
		
<<<<<<< HEAD
		//this.jeu.reintialiser();
=======
		this.jeu.reintialiser();
<<<<<<< HEAD
		System.out.println(this.jeu.bfs(99, 25, 1063));
=======
>>>>>>> 10b9cc83889ea6a8f5afcd0a3e4c5cc4d52c5bd3
		
		//System.out.println(this.jeu.bfs(99, 25, 2555));
>>>>>>> f3b4f49a28ad27cc925cf365c8d87a9dbad97be9
	}

	@FXML
	public void cliqueTableDeJeu(MouseEvent click) {
		int x = ((int) click.getX()) / 12 - 1;
		int y = ((int) click.getY()) / 12 - 1;
		System.out.println("Controleur.cliqueTableDeJeu [ x:" + x + " y:" + y + " ]");
		if(!this.jeu.verifieObstacle(x, y)) {
			if(this.jeu.limiterTours()) {
				if(!this.jeu.verifieTourAlentour(x, y, 5)) {	
					Tour tour = new Tour(this.jeu, x, y);
					this.jeu.ajouterTour(tour);
					this.tourVue.afficherTourVue(tour);
					this.jeu.ajoutObstacleTour(x, y);
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
	
//	//Test de bfs
//	@FXML
//	public void cliqueTableDeJeu(MouseEvent click) {
//		int x = ((int) click.getX()) / 12;
//		int y = ((int) click.getY()) / 12;
//		System.out.println("Controleur.cliqueTableDeJeu [ x:" + x + " y:" + y + " ]");
//		boolean erreur=false;
//		try {
//			this.jeu.bfs(99, 25, x+y*100);
//		}
//		catch (Exception e) {
//			erreur=true;
//		}
//		if(!erreur) {
//			System.out.println(this.jeu.bfs(99, 25, x+y*100));
//		}
//	}
}
