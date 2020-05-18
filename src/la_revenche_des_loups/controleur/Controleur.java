package la_revenche_des_loups.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.vue.LoupVue;
import la_revenche_des_loups.vue.TerrainVue;

public class Controleur implements Initializable{

	private Terrain terrain;
	private Loup loup;
	private TerrainVue terrainVue;
	private LoupVue loupVue;
    @FXML private TilePane tilePane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain=new Terrain();
		this.loupVue = new LoupVue(tilePane, terrain);
		this.terrainVue = new TerrainVue(tilePane, terrain);
//		this.terrainVue.afficherTerrainVue(21,21,12);
		this.loup = new Loup();
	}

	@FXML
    void lancerPartie(ActionEvent event) {
		loupVue.afficherLoupVue(this.loup);
    }
	
}