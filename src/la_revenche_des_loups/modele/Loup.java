
package la_revenche_des_loups.modele;

import la_revenche_des_loups.modele.Jeu;

public class Loup {
	private Jeu jeu;
	private Jeu terrain;
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
<<<<<<< HEAD

		int i=0;
		while (this.x > 17 || i < 25) {
			if (this.x > 17) {	
					this.avance();
					i++;
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
		}
	}

	public void avance() {
		this.x--;

		if (this.x > 17) {
=======
		if (this.x > 16) {
>>>>>>> 5f21aac1225bbbdf4c5e77848701073fdd3d0a75
			this.avance();
			System.out.println("etape1");
		}

		else if (this.y > 11 && this.x <= 16) {
				this.monte();
				System.out.println("etape2");
		}

		else if (this.y < 39 && this.x <= 16) {
				this.descends();
				System.out.println("etape3");
		}
		System.out.println("etape fin\n---------------------------");

	}

<<<<<<< HEAD
=======
	public void avance() {
		int i = 0;
		while (this.x > 16 && i < this.vitesse) {
			this.x = this.x-1;
			System.out.println("avance X:" + this.x + " Y:" + this.y);
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

>>>>>>> 5f21aac1225bbbdf4c5e77848701073fdd3d0a75
	public void monte() {
		int i = 0;
		while(this.y > 11 && this.vitesse > i) {
			this.y--;
			i++;
			System.out.println("monte X:" + this.x + " Y:" + this.y);
		}
		while(this.y < 39 && this.vitesse > i) {
			this.y++;
			i++;
			System.out.println("descend X:" + this.x + " Y:" + this.y);
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
			System.out.println("descend X:" + this.x + " Y:" + this.y);
		}
		while(this.y > 11 && this.vitesse > i) {
			this.y--;
			i++;
			System.out.println("avance X:" + this.x + " Y:" + this.y);
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}