package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Maison extends Acteur {

	private int yInf, ySup;
	private Jeu jeu;

	public Maison(Jeu j) {
		super(j, 15, 150, 3, 5);
		this.yInf = 11;
		this.ySup = 38;
		this.jeu = j;
	}

	public int getYSup() {
		return this.ySup;
	}

	public int getYInf() {
		return this.yInf;
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
	
	public void agit() {
		if (this.getCible() != null) {
			//Test Affichage
			this.jeu.setNumeroAction(3);
			
			seDefend();
			// on affiche sur la console que la maison se défend
			System.out.println("Maison.agit [ Maison se défend ]");
			changeCible();
		} else {
			cibleMaison();
		}
	}
	
	public String toString() {
		return "Maison : (" + this.getX() + ", " + this.yInf + "), PV : " + this.getPV();
	}

}
