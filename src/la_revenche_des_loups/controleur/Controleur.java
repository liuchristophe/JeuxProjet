package la_revenche_des_loups.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Maison;
import la_revenche_des_loups.modele.Terrain;
<<<<<<< HEAD
import la_revenche_des_loups.vue.GameLoop;
=======
import la_revenche_des_loups.modele.Tour;
>>>>>>> c1a4df185e0fc4296f5b48d3a3383acd606d6c94
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.MaisonVue;
import la_revenche_des_loups.vue.TerrainVue;
import la_revenche_des_loups.vue.TourVue;

public class Controleur implements Initializable{

	private Terrain terrain;
	private Jeu jeu;
<<<<<<< HEAD
	private Loup loup;
	private Maison maison;
	private TerrainVue terrainVue;
	private LoupVue loupVue;
	private MaisonVue maisonVue;
	private GameLoop gameloop;
=======
	private TerrainVue terrainVue;
	private LoupVue loupVue;
	private Loup loup;
	private TourVue tourVue;
>>>>>>> c1a4df185e0fc4296f5b48d3a3383acd606d6c94
    @FXML private TilePane tilePane;
    @FXML private Pane tableDeJeu;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain=new Terrain();
<<<<<<< HEAD
		this.jeu = new Jeu(terrain);
		this.maison = new Maison(jeu); //testMaisonVue
		this.loup = new Loup(jeu);
		this.jeu.ajouterLoup(loup);
		this.terrainVue = new TerrainVue(this.tilePane, jeu.getTerrain());
		this.terrainVue.afficherTerrainVue(21,21,12);
		this.maisonVue = new MaisonVue(this.tableDeJeu, jeu.getTerrain()); //testMaisonVue
		this.maisonVue.creerMaisonVue(maison); //testMaisonVue
		this.loupVue = new LoupVue(this.tableDeJeu, jeu.getTerrain());
		this.gameloop = new GameLoop(this.jeu.getLoup(),this.loupVue);
=======
		this.loup = new Loup(null);
		this.jeu = new Jeu(this.terrain);
		this.terrainVue = new TerrainVue(this.tilePane, terrain);
		this.terrainVue.afficherTerrainVue(21,21,12);
		this.loupVue = new LoupVue(this.tableDeJeu, terrain);
		this.tourVue = new TourVue(this.tableDeJeu, this.jeu);
>>>>>>> c1a4df185e0fc4296f5b48d3a3383acd606d6c94
	}

	@FXML
    void lancerPartie(ActionEvent event) {
<<<<<<< HEAD
//		System.out.println("X:" + loup.getX() + " Y:" + loup.getY());
		//loupVue.afficherLoupVue(this.loup);
		//this.loup.seDeplace();
		this.gameloop.lancerAnimation();
=======
		this.loupVue.afficherLoupVue(this.loup);
		this.loup.seDeplace();
>>>>>>> c1a4df185e0fc4296f5b48d3a3383acd606d6c94
    }
	
	@FXML
    void quitterJeu(ActionEvent event) {
		this.jeu.reintialiser();
    }
	
	@FXML
	public void cliqueTableDeJeu(MouseEvent click) {
		int x = ((int) click.getX())/12-1;
		int y = ((int) click.getY())/12-1;
		Tour tour = new Tour(this.jeu, x, y);
		this.tourVue.afficherTourVue(tour);
		
    }
}
