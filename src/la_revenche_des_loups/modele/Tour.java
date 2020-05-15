package la_revenche_des_loups.modele;

public class Tour {
	private Jeu map;
	private int x;
	private int y;
	private int pv;
	private int perimetre;
	private int degat;

	public Tour(Jeu map, int pv, int degat, int perimetre, int x, int y) {
		this.map = map;
		this.pv = pv;
		this.degat = degat;
		this.perimetre = perimetre;
		this.x = x;
		this.y = y;
	}

	public Tour(Jeu m) {
		this(m, 10, 2, 10, 50, 26);
	}

	public void seDefend() {
		Loup loup = cible();
		if (loup.estVivant()) {
			loup.decrementerPV(this.degat);
		}
	}

	// La tour cible le loup jusqu'a ce qu'il quitte le perimetre de la tour ou est
	// mort
	public Loup cible() {
		int i = 0;
		Loup loupCibler = null;
		while (i < this.map.getLoups().size()) { // verifie chaque Loup
			int loupX = this.map.getLoups().get(i).getX();
			int loupY = this.map.getLoups().get(i).getY();
			// perimetre horizontal avant perimetre horizontal arriere perimetre vertical
			// bas perimetre vertical haut
			if (loupX <= this.x + this.perimetre && loupX >= this.x - this.perimetre && loupY <= this.y + this.perimetre
					&& loupY >= this.y - this.perimetre) {
				if (loupCibler != null) {
					// compare le nouveau loupCibler avec le loup actuelle du tableau
					if (this.map.getLoups().get(i).getX() < loupX) {
						loupCibler = this.map.getLoups().get(i);
					}
				} else {
					loupCibler = this.map.getLoups().get(i);
				}
			}
			i++;
		}
		return loupCibler;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPv() {
		return pv;
	}

	public int getPerimetre() {
		return perimetre;
	}

	public int getDegat() {
		return degat;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public void setPerimetre(int perimetre) {
		this.perimetre = perimetre;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}
