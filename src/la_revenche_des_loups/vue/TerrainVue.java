package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Terrain;

public class TerrainVue {
	private Terrain terrain;
	private TilePane panneauJeu;
	
	public TerrainVue(TilePane tile, Terrain terrain){
		this.terrain = terrain;
		this.panneauJeu = tile;
	}
	
	private static void positionId(int id, int nbTuile, int[] suite) {
		if(id <= nbTuile) {
			suite[0] = id-1;
		}
		else {
			suite[1] = suite[1]+1;
			positionId(id-nbTuile, nbTuile, suite);
		}
	}
	
	public void afficherTerrainVue(int nbTuileLigne, int nbTuileColonne, int pixelTuile) {
		Image tileSet = null;
        try {
        	tileSet = new Image(new FileInputStream("src/la_revenche_des_loups/modele/Buch_Tiles.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		for (int x=0;x<this.terrain.getHauteur();x++) {
			for (int y=0;y<this.terrain.getLargeur();y++) {
				int codeTuile=this.terrain.codeTuile(x, y);
				ImageView imageview = new ImageView(tileSet);
				int[] position = new int[2];
				positionId(codeTuile, nbTuileLigne, position);
				Rectangle2D portVue = new Rectangle2D (position[0]*pixelTuile-1, position[1]*pixelTuile, pixelTuile, pixelTuile);
				imageview.setViewport(portVue);
				this.panneauJeu.getChildren().add(imageview);
			}
		}
	}
}
