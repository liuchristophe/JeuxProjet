package la_revenche_des_loups.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Maison;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.vue.BFSVue;
import la_revenche_des_loups.vue.HistoriqueActionVue;
import la_revenche_des_loups.modele.Tour;
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
	
	//test affichage
	private HistoriqueActionVue historiqueVue;
	@FXML
    private Label labelVague;

    @FXML
    private Label labelAction1;
    @FXML
    private Label labelAction2;
    @FXML
    private Label labelAction3;
	@FXML
    private Label labelAction4;
	@FXML
	private Label labelAction5;

	@FXML
	private Label infoActeur;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain = new Terrain();
		this.jeu = new Jeu(terrain);
		this.terrainVue = new TerrainVue(this.tilePane, jeu.getTerrain());
		this.terrainVue.afficherTerrainVue(21, 21, 12);
		
		// ajout de la maison et du loup dans le jeu
		this.maison = new Maison(jeu);
		
		// ajout des sprites
		this.maisonVue = new MaisonVue(this.tableDeJeu, infoActeur);
		this.maisonVue.creerMaisonVue(maison);
		this.tourVue = new TourVue(this.tableDeJeu);
		
		//Initialisation de l'historique d'action
		this.historiqueVue = new HistoriqueActionVue(jeu, labelAction1, labelAction2, labelAction3, labelAction4, labelAction5);
		
		// initialisation de la gameloop
		this.gameloop = new GameLoop(this.jeu, this.tableDeJeu, this.historiqueVue);
		
		
		//initialisation BFS
		//this.bfsVue = new BFSVue(testBFS, this.jeu);
		//this.bfsVue.afficherBFSVue(2, 2, 12);
	}

	@FXML
	void vagueSuivante(ActionEvent event) {
		//this.historiqueVue.reinitialiserHistorique();
		this.gameloop.lancerVague();
		this.labelVague.setText("Vague numéro "+this.gameloop.getNumVague());
	}
	
	@FXML
	void lancerPartie(ActionEvent event) {
		this.labelVague.setText("Vague numéro "+this.gameloop.getNumVague());
		this.gameloop.lancerAnimation();
	}

	@FXML
	void quitterJeu(ActionEvent event) {
		this.gameloop.pause();
		//this.jeu.reintialiser();
		//System.out.println(this.jeu.bfs(99, 25, 2555));
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
	
	
	//test
	@FXML
	void changeLabel() {
		
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
