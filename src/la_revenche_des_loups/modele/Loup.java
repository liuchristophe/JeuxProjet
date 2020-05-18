package la_revenche_des_loups.modele;

import la_revenche_des_loups.modele.Terrain;

public class Loup {
	private static Jeu jeu;
	private Terrain terrain;
	private int x, y;
	private int pv;
	private int vitesse;
	private int ptsATT;

	public Loup(Jeu j) {
		this.jeu=j;
		this.x = 99;
		this.y = (int) (Math.random() * 46) + 2;
		this.pv = 5;
		this.vitesse = 1;
		this.ptsATT = 3;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getPV() {
		return this.pv;
	}

	public int getVitesse() {
		return this.vitesse;
	}

	public int getPtsATT() {
		return this.ptsATT;
	}

	public Terrain getMap() {
		return this.terrain;
	}

	public void decrementerPV(int pts) {
		this.pv -= pts;
		if (this.pv==0) {
			this.meurt();
		}
	}

	public boolean estVivant() {
		return this.pv > 0;
	}

	public void meurt() {
		this.pv = 0;
		this.jeu.retirerLoup(this);
	}

	public void seDeplace() {
		int i=0;
		while (this.x > 16 && i < 25) {
			if (this.x > 16) {	
					this.avance();
				}
			
	
			else if (this.y < 11) {
				while (this.y < 11) {
					this.monte();
				}
			}
	
			else if (this.y > 39) {
				while (this.y > 39) {
					this.descends();
				}
			}
			i++;
		}
	}

	public void avance() {
		this.x--;
	}

	public void monte() {
		this.y++;
	}

	public void descends() {
		this.y--;
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}
