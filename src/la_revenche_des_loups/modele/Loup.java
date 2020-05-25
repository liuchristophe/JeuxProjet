
package la_revenche_des_loups.modele;

import java.util.ArrayList;

import la_revenche_des_loups.modele.Jeu;

public class Loup {
	private Jeu jeu;
	private int x, y;
	private int pv;
	private int vitesse;
	private int ptsATT;
	private String id;
	private static int num = 0;

	public Loup(Jeu j) {
		this.jeu=j;
		this.x = 99;
		this.y = (int) (Math.random() * 46) + 2;
		this.pv = 5;
		this.vitesse = 3;
		this.ptsATT = 3;
		this.id = "L"+ num;
		num++;
	}
	
	public String getId() {
		return this.id;
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
		if (this.x > 15) {
			this.avance();
		}

		else if (this.y > 25+(((int)(Math.random()*28))-14) && this.x <= 15) {
				this.monte();
		}

		else if (this.y <= 25+(((int)(Math.random()*28))-14) && this.x <= 15) {
				this.descends();
		}

	}

	public ArrayList<int[][]> seDeplaceBFS(int posCibleX, int posCibleY) {
		return null;
	}
	
	public void avance() {
		int i = 0;
		while (this.x > 15 && i < this.vitesse) {
			this.x = this.x-1;
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
			i++;
		}
		while(this.y < 39 && this.vitesse > i) {
			this.y++;
			i++;
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public void descends() {
		int i = 0;
		while(this.y < 39 && this.vitesse > i) {
			this.y++;
			i++;
		}
		while(this.y > 11 && this.vitesse > i) {
			this.y--;
			i++;
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}