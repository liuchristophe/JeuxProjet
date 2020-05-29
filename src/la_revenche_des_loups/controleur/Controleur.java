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
import la_revenche_des_loups.vue.GameLoop;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.MaisonVue;
import la_revenche_des_loups.vue.TerrainVue;
import la_revenche_des_loups.vue.TourVue;

public class Controleur implements Initializable {

	private Terrain terrain;
	private Jeu jeu;
	private Loup loup;
	private Maison maison;
	private TerrainVue terrainVue;	
	private LoupVue loupVue;
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
//		this.terrainVue.afficherTerrainVue(21, 21, 12);
		// ajout de la maison et du loup dans le jeu
		this.maison = new Maison(jeu);
		this.loup = new Loup(jeu);
		// ajout des sprites
		this.maisonVue = new MaisonVue(this.tableDeJeu, jeu.getTerrain());
		this.maisonVue.creerMaisonVue(maison);
		this.loupVue = new LoupVue(this.tableDeJeu, jeu.getTerrain());
		this.tourVue = new TourVue(this.tableDeJeu, this.jeu);
		// initialisation de la gameloop
//		this.gameloop = new GameLoop(this.jeu.getPremierLoup(), this.loupVue, this.jeu);
		//initialisation BFS
		this.bfsVue = new BFSVue(testBFS, this.jeu);
		this.bfsVue.afficherBFSVue2(2, 1, 12);
		this.gameloop = new GameLoop(this.loup, this.loupVue, this.jeu);
	}

	@FXML
	void lancerPartie(ActionEvent event) {
		this.gameloop.lancerAnimation();
		this.loupVue.afficherLoupVue(this.loup);
		this.loup.seDeplace();
	}

	@FXML
	void quitterJeu(ActionEvent event) {
		this.jeu.reintialiser();
		System.out.println(this.jeu.bfs(99, 25, 2555));
	}

	@FXML
	public void cliqueTableDeJeu(MouseEvent click) {
		int x = ((int) click.getX()) / 12 - 1;
		int y = ((int) click.getY()) / 12 - 1;
		System.out.println("Controleur.cliqueTable[ x: " + x + " y" + y +" ]");
		if(!this.jeu.verifieObstacle(x, y)) {
//			if(!this.jeu.existeTour()) {
				Tour tour = new Tour(this.jeu, x, y);
				this.tourVue.afficherTourVue(tour);
				this.jeu.ajoutObstacleTour(x, y);
				this.testBFS.getChildren().clear();
				this.bfsVue.afficherBFSVue(2, 1, 12);
				System.out.println("Controleur.cliqueTableDeJeu [ ajout d un tour ]");
//			}
		}
		else {
			System.out.println("Controleur.cliqueTableDeJeu [ pas de tour poser ]");
			//utilise un variable pour idCible ou sinon ca va faire n'importe quoi
			int idCible = x+y*this.jeu.getTerrain().getLargeur()+3+200;
			this.testBFS.getChildren().clear();
			this.bfsVue.afficherCheminBFS(this.jeu.bfs(99, 25, idCible));
		}
	}
}
