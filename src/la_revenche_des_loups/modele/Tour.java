package la_revenche_des_loups.modele;

import java.util.ArrayList;

public abstract class Tour extends Acteur{
	
	private String id;
	private static int num = 0;
	private int prix;
	
	//Test tir
	private boolean seDefend;

	public Tour(Jeu jeu, int x, int y, int pv, int perimetre, int degat, int prix ) {
		super(jeu, x, y, pv, perimetre, degat);
		this.id = "T" + num;
		num++;
		this.prix = prix;
		//Test tir
		this.seDefend = false;
		super.getJeu().payerTour(this.prix);
		super.setTaille(3);
	}

	public Tour(Jeu jeu, int x, int y) {
		this(jeu, x, y, 30, 5, 2,0);
	}

	public Tour(Jeu jeu) {
		this(jeu, 40, 26);
	}

	public String getId() {
		return this.id;
	}
	
	public boolean tirEstLance() {
		return this.seDefend;
	}
	
	public void seDefend() {
		if (this.getCible().estVivant()) {
			this.getCible().seFaitAttaquer(this.getPtsATT());
		}
		//Test tir
		this.seDefend = true;
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
            	//Test Affichage
            	super.getJeu().setNumeroAction(2);
            	
                seDefend();
                //Test tir
        		this.seDefend = false;
        		super.getJeu().getListeTirs().add(new Tir(this.id, this.getCible().getX(), this.getCible().getY()));
                
                // on affiche sur la console que la tour se défend
                System.out.println("Tour.agit [ Tour " + getId() + " se défend ]");
                changeCible();
            } else {
                cible();
            }
	}

	public int nombreEnnemis() {
        int nbEnnemis=0;
        for (int i=0;i<this.getJeu().getListeLoups().size();i++) {
            if ((this.getJeu().getListeLoups().get(i).getY() > this.getY() - this.getPerimetre() && this.getJeu().getListeLoups().get(i).getY() < this.getY() + this.getPerimetre())
                    && (this.getJeu().getListeLoups().get(i).getX() > this.getX() - this.getPerimetre() && this.getJeu().getListeLoups().get(i).getX() < this.getX() + this.getPerimetre())){
                        i++;
            }
        }

        return nbEnnemis;
    }
	
	public String toString() {
		return "Tour "+this.id+" : (" + this.getX() + ", " + this.getY() + "), PV : " + this.getPV();
	}
}
