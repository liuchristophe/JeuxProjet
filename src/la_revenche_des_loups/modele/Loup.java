package la_revenche_des_loups.modele;

import la_revenche_des_loups.modele.Jeu;

public class Loup {
	private Jeu jeu;
	private Jeu terrain;
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

	public Jeu getMap() {
		return this.terrain;
	}

	public void decrementerPV(int pts) {
		this.pv -= pts;
	}

	public boolean estVivant() {
		return this.pv > 0;
	}

	public void meurt() {
		this.pv = 0;
	}

	public void seDeplace() {
		if (this.x > 17) {
			this.avance();
		}

		else if (this.y < 11 && this.x < 17) {
				this.monte();
		}

		else if (this.y > 39 && this.x < 17) {
				this.descends();
		}

	}

	public void avance() {
		int i = 0;
		while (this.x > 17 && i < this.vitesse) {
			this.x--;
			i++;
		}
		if(i < this.vitesse) {
			while(i < this.vitesse) {
				if (this.y < 39) {
					this.y++;
					i++;
				}
				else if(this.y > 11) {
					this.y--;
					i++;
				}
			}
		}
	}

	public void monte() {
		int i = 0;
		while(this.y > 11 && this.vitesse > i) {
			this.y--;
		}
		while(this.y < 39 && this.vitesse > i) {
			this.y++;
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public void descends() {

		int i = 0;
		while(this.y < 39 && this.vitesse > i) {
			this.y++;
		}
		while(this.y > 11 && this.vitesse > i) {
			this.y--;
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}
