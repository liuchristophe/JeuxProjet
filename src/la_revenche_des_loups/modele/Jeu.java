package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Jeu {

	private Terrain terrain;
	private ArrayList<Loup> ListeLoups;
	private ArrayList<Tour> ListeTours;
	private Maison maison;

	public Jeu(Terrain t) {
		this.terrain = t;
		this.ListeLoups = new ArrayList<Loup>();
		this.ListeTours = new ArrayList<Tour>();
		this.maison = new Maison(this);
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

	public Loup getPremierLoup() {
		return this.ListeLoups.get(0);
	}

	public void ajouterLoup(Loup l) {
		this.ListeLoups.add(l);
		System.out.println("ajout d'un loup");
	}

	public void retirerLoup(Loup l) {
		this.ListeLoups.remove(l);
	}

	public void ajouterTour(Tour t) {
		this.ListeTours.add(t);
		System.out.println("ajout de tour");
	}

	public void retirerTour(Tour t) {
		this.ListeTours.remove(t);
	}

	public void reintialiser() {
		this.terrain = new Terrain();
		this.ListeLoups = new ArrayList<Loup>();
	}

	// Permet de v�rifier si un loup se situe dans le p�rim�tre d'attaque d'une tour
	public Loup verifieLoupTour(int x, int y, int peri) {
		for (int i = 0; i < this.ListeLoups.size(); i++) {
			if (((this.ListeLoups.get(i).getX() > x - peri) && (this.ListeLoups.get(i).getX() < x + peri))
					&& ((this.ListeLoups.get(i).getY() > y - peri) && (this.ListeLoups.get(i).getY() < y + peri))) {
				return this.ListeLoups.get(i);
			}
		}

		return null;
	}

	// Permet de v�rifier si un loup se situe dans le p�rim�tre d'attaque de la
	// maison
	public Loup verifieLoupMaison(int x, int y, int peri) {
		for (int i = 0; i < this.ListeLoups.size(); i++) {
			if ((this.ListeLoups.get(i).getX() < x + peri) && ((this.ListeLoups.get(i).getY() > y - (peri + 22))
					&& (this.ListeLoups.get(i).getY() < y + peri))) {
				return this.ListeLoups.get(i);
			}
		}

		return null;
	}

	// Permet de v�rifier si une tour se situe dans le p�rim�tre d'attaque d'un loup
	public Tour verifieTour(int x, int y, int peri) {
		for (int i = 0; i < this.ListeTours.size(); i++) {
			if ((this.ListeTours.get(i).getY() > y - peri && this.ListeTours.get(i).getY() < y + peri)
					&& (this.ListeTours.get(i).getX() > x - peri && this.ListeTours.get(i).getX() < x + peri)) {
				return this.ListeTours.get(i);
			}
		}

		return null;
	}

	// Les methodes ci-dessous sont les methodes d'action des diff�rents acteurs

	// Le loup se d�place s'arrete lorsque il y a une tour dans son p�rim�tre
	// d'attaque, puis attaque
	public void agitLoup() {
		for (int i = 0; i < this.ListeLoups.size(); i++) {
			this.ListeLoups.get(i).seDeplace();
			if (this.ListeLoups.get(i).getTourCible() != null) {
				this.ListeLoups.get(i).attaqueTour();
				this.ListeLoups.get(i).changeCible();
			} else {
				this.ListeLoups.get(i).tourCible();
			}
		}
	}

	public void agitTour() {
		for (int i = 0; i < this.ListeTours.size(); i++) {
			if (this.ListeTours.get(i).getLoupCible() != null) {
				this.ListeTours.get(i).seDefend();
				// on affiche sur la console que la tour se d�fend
				System.out.println("Tour " + this.ListeTours.get(i).getId() + " se d�fend");
				this.ListeTours.get(i).changeCible();
			} else {
				this.ListeTours.get(i).loupcible();
			}
		}
	}

	public void agitMaison() {
		if (this.maison.getLoupCible() != null) {
			this.maison.seDefend();
			// on affiche sur la console que la maison se d�fend
			System.out.println("Maison se d�fend");
			this.maison.changeCible();
		} else {
			this.maison.loupcible();
		}
	}

}