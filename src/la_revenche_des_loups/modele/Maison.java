package la_revenche_des_loups.modele;

public class Maison {

	private Jeu jeu;

	private int x, yInf, ySup;
	private int pv;
	private int ptsATT;
	private int perimetre;
	private Loup loupCible;

	public Maison(Jeu j) {
		this.jeu = j;
		this.x = 15;
		this.yInf = 11;
		this.ySup = 38;
		this.pv = 10;
		this.ptsATT = 2;
		this.perimetre = 3;
	}

	public int getX() {
		return this.x;
	}

	public int getYSup() {
		return this.ySup;
	}

	public int getYInf() {
		return this.yInf;
	}

	public int getPV() {
		return this.pv;
	}

	public int getPtsATT() {
		return this.ptsATT;
	}

	public int getPerimetre() {
		return this.perimetre;
	}

	public Loup getLoupCible() {
		return this.loupCible;
	}

	public void decrementerPV(int pts) {
		this.pv -= pts;
	}

	public boolean estDetruite() {
		return this.pv > 0;
	}

	public void seDetruit() {
		this.pv = 0;
	}

	public void seDefend() {
		if (this.loupCible != null) {
			this.loupCible.decrementerPV(this.ptsATT);
		}
	}

	public void loupcible() {
		if (this.jeu.verifieLoupMaison(this.x, this.ySup, this.perimetre) != null) {
			this.loupCible = this.jeu.verifieLoupMaison(this.x, this.ySup, this.perimetre);
		} else if (this.jeu.verifieLoupMaison(this.x, this.yInf, this.perimetre) != null) {
			this.loupCible = this.jeu.verifieLoupMaison(this.x, this.yInf, this.perimetre);
		}
	}

	public void changeCible() {
		if (!this.loupCible.estVivant()) {
			this.loupcible();
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.yInf + "), PV : " + this.pv;
	}

}
