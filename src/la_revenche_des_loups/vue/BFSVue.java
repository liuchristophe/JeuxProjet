package la_revenche_des_loups.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Jeu;

public class BFSVue {
	private Jeu jeu;
	private TilePane panneau;
	private Image tileSet;
	
	public BFSVue(TilePane panneau, Jeu jeu){
		this.jeu = jeu;
		this.panneau = panneau;
		this.tileSet = null;
        try {
        	this.tileSet = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/testBFS.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void afficherBFSVue(int nbTuileLigne, int nbTuileColonne, int pixelTuile) {
		
		for (int y=0;y<this.jeu.getTerrain().getHauteur();y++) {
			for (int x=0;x<this.jeu.getTerrain().getLargeur();x++) {
				int codeTuile=this.jeu.retourTableauObstacle(x, y);
				ImageView imageview = new ImageView(this.tileSet);
				Rectangle2D portVue = null;
				if(codeTuile == 0) {
					portVue = new Rectangle2D(12,0,12,12);
				}
				else if(codeTuile == 1){
					portVue = new Rectangle2D(0,0,12,12);
				}
				imageview.setViewport(portVue);
				this.panneau.getChildren().add(imageview);
			}
		}
	}
	
	public void afficherBFSVue2(int nbTuileLigne, int nbTuileColonne, int pixelTuile) {
		for (int y=0;y<this.jeu.getTerrain().getHauteur();y++) {
			for (int x=0;x<this.jeu.getTerrain().getLargeur();x++) {
				int[] tabMarquer = this.jeu.tabMarquer(99, 25, 3333);
				int codeTuile=tabMarquer[y*this.jeu.getTerrain().getLargeur()+x];
				ImageView imageview = new ImageView(this.tileSet);
				Rectangle2D portVue;
				if(codeTuile == 0) {
					portVue = new Rectangle2D(12,0,12,12);
				}
				else {
					portVue = new Rectangle2D(0,0,12,12);
				}
				imageview.setViewport(portVue);
				this.panneau.getChildren().add(imageview);
			}
		}
	}
	
	public void afficherCheminBFS(ArrayList<Integer> bfs) {
		this.jeu.ajoutBFS(bfs);
		for (int y=0;y<this.jeu.getTerrain().getHauteur();y++) {
			for (int x=0;x<this.jeu.getTerrain().getLargeur();x++) {
				int codeTuile=this.jeu.retourTableauObstacle(x, y);
				ImageView imageview = new ImageView(this.tileSet);
				Rectangle2D portVue = null;
				if(codeTuile == 0) {
					portVue = new Rectangle2D(12,0,12,12);
				}
				else if(codeTuile == 1){
					portVue = new Rectangle2D(0,0,12,12);
				}
				else if(codeTuile == 2) {
					portVue = new Rectangle2D(0,12,12,12);
				}
				imageview.setViewport(portVue);
				this.panneau.getChildren().add(imageview);
			}
		}
	}
}
