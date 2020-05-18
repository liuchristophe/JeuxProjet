package la_revenche_des_loups.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.vue.GameLoop;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.TerrainVue;
import javafx.scene.layout.Pane;

public class Controleur implements Initializable{

	private Terrain terrain;
	private Jeu jeu;
	private Loup loup;
	private TerrainVue terrainVue;
	private LoupVue loupVue;
	private GameLoop gameloop;
    @FXML private TilePane tilePane;
    @FXML private Pane tableDeJeu;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain=new Terrain();
		this.jeu = new Jeu(terrain);
		this.loup = new Loup(jeu);
		this.jeu.ajouterLoup(loup);
		this.terrainVue = new TerrainVue(this.tilePane, jeu.getTerrain());
		this.terrainVue.afficherTerrainVue(21,21,12);
		this.loupVue = new LoupVue(this.tableDeJeu, jeu.getTerrain());
		this.gameloop = new GameLoop(this.jeu.getLoup(),this.loupVue);
	}

	@FXML
    void lancerPartie(ActionEvent event) {
//		System.out.println("X:" + loup.getX() + " Y:" + loup.getY());
		//loupVue.afficherLoupVue(this.loup);
		//this.loup.seDeplace();
		this.gameloop.lancerAnimation();
    }
	
	@FXML
    void quitterJeu(ActionEvent event) {
		this.jeu.reintialiser();
    }
	
}
