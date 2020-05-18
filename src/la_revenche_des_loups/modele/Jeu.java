package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Jeu {

	private Terrain terrain;
	private ArrayList<Loup> loups;
	private int nbTours;

	public Jeu() {
		this.terrain = new Terrain();
		this.loups = new ArrayList<Loup>();
	}

	public ArrayList<Loup> getLoups() {
		return this.loups;
	}

	public void ajouter(Loup newLoup) {
		this.loups.add(newLoup);
	}
}
