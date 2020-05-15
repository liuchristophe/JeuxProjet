package la_revenche_des_loups.controleur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import la_revenche_des_loups.modele.Terrain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{

	private Terrain tuileMap;
    @FXML private TilePane tilePane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.tuileMap=new Terrain();
		afficherTerrain(21,12);
//		testTileSet(21, 12);
	}
	
	public void testTileSet(int nbTuile, int pixelTuile) {
		Image tileSet = null;
        try {
        	tileSet = new Image(new FileInputStream("src/la_revenche_des_loups/modele/Buch_Tiles.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
        }
		for(int y = 0; y < nbTuile; y++) {
			for (int x = 0; x < nbTuile; x++) {
				ImageView imageview = new ImageView(tileSet);
		        imageview.setImage(tileSet);
		        Rectangle2D portVue = new Rectangle2D (x*pixelTuile-1, y*pixelTuile, pixelTuile, pixelTuile);
		        imageview.setViewport(portVue);
				this.tilePane.getChildren().add(imageview);
			}
		}
	}
	
	private void afficherTerrain(int nbTuile, int pixelTuile) {
		Image tileSet = null;
        try {
        	tileSet = new Image(new FileInputStream("src/la_revenche_des_loups/modele/Buch_Tiles.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
		for (int x=0;x<tuileMap.getHauteur();x++) {
			for (int y=0;y<tuileMap.getLargeur();y++) {
				int codeTuile=tuileMap.codeTuile(x, y);
		        ImageView imageview = new ImageView(tileSet);
		        int[] position = new int[2];
		        positionId(codeTuile, nbTuile, position);
		        Rectangle2D portVue = new Rectangle2D (position[0]*pixelTuile-1, position[1]*pixelTuile, pixelTuile, pixelTuile);
		        imageview.setViewport(portVue);
				this.tilePane.getChildren().add(imageview);
			}
		}
	}
	
	public static void positionId(int id, int nbTuile, int[] suite) {
		if(id <= nbTuile) {
			suite[0] = id-1;
		}
		else {
			suite[1] = suite[1]+1;
			positionId(id-nbTuile, nbTuile, suite);
		}
	}

}