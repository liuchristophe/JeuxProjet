package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Terrain {

	private int[][] terrain;
	private ArrayList<Loup> loups;
	private int nbTours;

	public Terrain() {
		this.terrain = new int[100][50];
	}

	public int getNbTours() {
		return this.nbTours;
	}

	public int[][] getMap() {
		return this.terrain;
	}

	public ArrayList<Loup> getLoups() {
		return this.loups;
	}

	public void ajouter(Loup newLoup) {
		this.loups.add(newLoup);
	}
}
