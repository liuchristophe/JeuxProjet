package la_revenche_des_loups.modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Maison extends Acteur {

	private int yInf, ySup;
	private IntegerProperty affichagePv;

	public Maison(Jeu j) {
		super(j, 15, 5000, 3, 50);
		this.yInf = 11;
		this.ySup = 38;
		this.affichagePv = new SimpleIntegerProperty(super.getPV());
	}

	public int getYSup() {
		return this.ySup;
	}

	public int getYInf() {
		return this.yInf;
	}

	public IntegerProperty getPvMaison() {
		this.affichagePv.set(super.getPV());
		return this.affichagePv;
	}
	
	public void seFaitAttaquer(int pts) {
		super.seFaitAttaquer(pts);
		this.getPvMaison();
		
	}
	public void seDefend() {
		if (this.getCible() != null) {
			this.getCible().seFaitAttaquer(this.getPtsATT());
		}
	}

	public Acteur verifie(int x, int y, int peri) {
		ArrayList<Acteur> listeLoups = new ArrayList<Acteur>();
		for (int i = 0; i < this.getJeu().getListe().size();i++) {
			if(this.getJeu().getListe().get(i) instanceof Loup) {
				listeLoups.add(this.getJeu().getListe().get(i));
			}
		}
		for (int i = 0; i < listeLoups.size(); i++) {
			if ((listeLoups.get(i).getX() < x + peri) && ((listeLoups.get(i).getY() > y - (peri + 22))
					&& (listeLoups.get(i).getY() < y + peri))) {
				return listeLoups.get(i);
			}
		}

		return null;
	}
	
	public void cibleMaison() {
		if (verifie(this.getX(), this.ySup, this.getPerimetre()) != null) {
			this.setCible(verifie(this.getX(), this.ySup, getPerimetre()));
		} else if (verifie(this.getX(), this.yInf, this.getPerimetre()) != null) {
			this.setCible(verifie(this.getX(), this.yInf, getPerimetre()));
		}
	}

	public void changeCible() {
		if (!this.getCible().estVivant()) {
			cibleMaison();
		}
	}
	
	//Cette methode englobe toutes les action de la maison lors d'un tour de jeu
	public void agit() {
		if (this.getCible() != null) {
			super.getJeu().setNumeroAction(3);
			
			seDefend();
			changeCible();
		} else {
			cibleMaison();
		}
	}
	
	public String toString() {
		return "Maison : (" + this.getX() + ", " + this.yInf + "), PV : " + this.getPV();
	}

}
