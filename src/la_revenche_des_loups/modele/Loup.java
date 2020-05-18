package la_revenche_des_loups.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Loup {
	private Jeu jeu;
	private Jeu terrain;
	private IntegerProperty x, y;
	private int pv;
	private int vitesse;
	private int ptsATT;

	public Loup(Jeu j) {
		this.jeu=j;
		this.x = new SimpleIntegerProperty(99);
		this.y = new SimpleIntegerProperty((int) (Math.random() * 46) + 2);
		this.pv = 5;
		this.vitesse = 1;
		this.ptsATT = 3;
	}

	public IntegerProperty getXProperty() {
		return this.x;
	}

	public int getX() {
		return this.x.getValue();
	}
	
	public void setX(int newX) {
		this.x.setValue(newX);;
	}
	
	public IntegerProperty getYProperty() {
		return this.y;
	}

	public int getY() {
		return this.y.getValue();
	}

	public void setY(int newY) {
		this.y.setValue(newY);;
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
		if (this.x.getValue() > 16) {
			this.avance();
		}

		else if (this.y.getValue() < 11 && this.x.getValue() < 16) {
				this.monte();
		}

		else if (this.y.getValue() > 39 && this.x.getValue() < 16) {
				this.descends();
		}

	}

	public void avance() {
		int i = 0;
		while (this.x.getValue() > 16 && i < this.vitesse) {
			this.x.setValue(this.x.getValue()-1);
			i++;
		}
		if(i < this.vitesse) {
			while(i < this.vitesse) {
				if (this.y.getValue() < 39) {
					this.y.setValue(this.y.getValue()+1);
					i++;
				}
				else if(this.y.getValue() > 11) {
					this.y.setValue(this.y.getValue()-1);
					i++;
				}
			}
		}
	}

	public void monte() {
		int i = 0;
		while(this.y.getValue() > 11 && this.vitesse > i) {
			this.y.setValue(this.y.getValue()-1);
		}
		while(this.y.getValue() < 39 && this.vitesse > i) {
			this.y.setValue(this.y.getValue()+1);
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public void descends() {
		int i = 0;
		while(this.y.getValue() < 39 && this.vitesse > i) {
			this.y.setValue(this.y.getValue()+1);
		}
		while(this.y.getValue() > 11 && this.vitesse > i) {
			this.y.setValue(this.y.getValue()-1);
		}
		if(i < this.vitesse) {
			this.monte();
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}
