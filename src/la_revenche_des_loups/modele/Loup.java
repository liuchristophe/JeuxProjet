
package la_revenche_des_loups.modele;

import java.util.ArrayList;

import la_revenche_des_loups.modele.Jeu;

public class Loup {
	private Jeu jeu;
	private int x, y;
	private int pv;
	private int perimetre;
	private int vitesse;
	private int ptsATT;
	private String id;
	private static int num = 0;
	private Tour tourCible;

	public Loup(Jeu j) {
		this.jeu = j;
		this.x = 99;
		this.y = (int) (Math.random() * 46) + 2;
		this.pv = 5;
		this.vitesse = 2;
		this.ptsATT = 3;
		this.perimetre = 4;
		this.id = "L" + num;
		num++;
		this.jeu.ajouterLoup(this);
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

<<<<<<< HEAD
	public Tour getTourCible() {
		return this.tourCible;
	}

=======
>>>>>>> 25285c4a28ab4fec34a424f3f58c6f8264c867cd
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

		else if (this.y > 25 + (((int) (Math.random() * 28)) - 14) && this.x <= 15) {
			this.monte();
		}

		else if (this.y <= 25 + (((int) (Math.random() * 28)) - 14) && this.x <= 15) {
			this.descends();
		}

	}

	public ArrayList<int[][]> seDeplaceBFS(int posCibleX, int posCibleY) {
		return null;
	}
	
	public void avance() {
		int i = 0;
		while (this.x > 15 && i < this.vitesse) {
			this.x = this.x - 1;
			i++;
		}
		if (i < this.vitesse) {
			while (i < this.vitesse) {
				if (this.y < 39) {
					this.y++;
					i++;
				} else if (this.y > 11) {
					this.y--;
					i++;
				}
			}
		}
	}

	public void monte() {
		int i = 0;
		while (this.y > 11 && this.vitesse > i) {
			this.y--;
			i++;
		}
		while (this.y < 39 && this.vitesse > i) {
			this.y++;
			i++;
		}
		if (i < this.vitesse) {
			this.monte();
		}
	}

	public void descends() {
		int i = 0;
		while (this.y < 39 && this.vitesse > i) {
			this.y++;
			i++;
		}
		while (this.y > 11 && this.vitesse > i) {
			this.y--;
			i++;
		}
		if (i < this.vitesse) {
			this.monte();
		}
	}

	public void arrete() {
		this.vitesse = 0;
	}

	public void remarche() {
		this.vitesse = 2;
	}

	public void tourCible() {
		this.tourCible = this.jeu.verifieTour(x, y, perimetre);
	}

	public void changeCible() {
		if (this.tourCible.estDetruite()) {
			this.tourCible();
		}
	}

	public void attaqueTour() {
		if ((this.x + 1 == this.tourCible.getX() || this.x - 1 == this.tourCible.getX()
				|| this.x == this.tourCible.getX())
				&& (this.y + 1 == this.tourCible.getY() || this.y - 1 == this.tourCible.getY()
						|| this.y == this.tourCible.getY())) {
			arrete();
			// on affiche sur la console que le loup attaque
			System.out.println("loup attaque");
			this.tourCible.decrementerPV(ptsATT);
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}