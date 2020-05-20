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
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.TerrainVue;
import la_revenche_des_loups.vue.TourVue;

public class Controleur implements Initializable{

	private Terrain terrain;
	private Jeu jeu;
	private TerrainVue terrainVue;
	private LoupVue loupVue;
	private Loup loup;
	private TourVue tourVue;
    @FXML private TilePane tilePane;
    @FXML private Pane tableDeJeu;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain=new Terrain();
		this.loup = new Loup(null);
		this.jeu = new Jeu(this.terrain);
		this.terrainVue = new TerrainVue(this.tilePane, terrain);
		this.terrainVue.afficherTerrainVue(21,21,12);
		this.loupVue = new LoupVue(this.tableDeJeu, terrain);
		this.tourVue = new TourVue(this.tableDeJeu, this.jeu);
	}

	@FXML
    void lancerPartie(ActionEvent event) {
		this.loupVue.afficherLoupVue(this.loup);
		this.loup.seDeplace();
    }

	@FXML
    void quitterJeu(ActionEvent event) {
	}
	
	@FXML
	public void cliqueTableDeJeu(MouseEvent click) {
		int x = ((int) click.getX())/12-1;
		int y = ((int) click.getY())/12-1;
		Tour tour = new Tour(this.jeu, x, y);
		this.tourVue.afficherTourVue(tour);
		
    }
}
