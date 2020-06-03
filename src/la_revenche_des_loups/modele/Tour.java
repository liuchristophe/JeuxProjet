package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Tour extends Acteur{
	
	private String id;
	private static int num = 0;

	public Tour(Jeu jeu, int x, int y, int pv, int perimetre, int degat ) {
		super(jeu, x, y, pv, perimetre, degat);
		this.id = "T" + num;
		num++;
		//this.jeu.ajouterTour(this);
		
		super.setTaille(3);
	}

	public Tour(Jeu jeu, int x, int y) {
		this(jeu, x, y, 10, 10, 2);
	}

	public Tour(Jeu jeu) {
		this(jeu, 40, 26);
	}

	public String getId() {
		return this.id;
	}
	
	public void seDefend() {
		if (this.getCible().estVivant()) {
			this.getCible().seFaitAttaquer(this.getPtsATT());
		}
	}

	public void changeCible() {
		if ((this.getCible().estVivant() == false) || (this.getCible().getX() < this.getX() - this.getPerimetre())
				|| (this.getCible().getY() < this.getY() - this.getPerimetre())
				|| (this.getCible().getY() < this.getY() + this.getPerimetre())) {
			cible();
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
			if ((listeLoups.get(i).getY() > y - peri && listeLoups.get(i).getY() < y + peri)
					&& (listeLoups.get(i).getX() > x - peri && listeLoups.get(i).getX() < x + peri)) {
				return listeLoups.get(i);
			}
		}

		return null;
	}
	
	public void agit() {
            if (this.getCible() != null) {
                seDefend();
                // on affiche sur la console que la tour se défend
                System.out.println("Tour " + getId() + " se défend");
                changeCible();
            } else {
                cible();
            }
	}

	public String toString() {
		return "Tour "+this.id+" : (" + this.getX() + ", " + this.getY() + "), PV : " + this.getPV();
	}
}
