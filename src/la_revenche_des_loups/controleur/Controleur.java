package la_revenche_des_loups.controleur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.vue.TerrainVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{

	private Terrain terrain;
	private TerrainVue terrainVue;
    @FXML private TilePane tilePane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain=new Terrain();
		this.terrainVue = new TerrainVue(tilePane, terrain);
		this.terrainVue.afficherTerrainVue(21,21,12);
	}

}