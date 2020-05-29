
package la_revenche_des_loups.modele;

import java.util.ArrayList;

import la_revenche_des_loups.modele.Jeu;

public class Loup extends Acteur{
	private int vitesse;
	private String id;
	private static int num = 0;

	public Loup(Jeu j) {
		super(j, 99, (int) (Math.random() * 46) + 2, 30, 4, 3);
		this.vitesse = 2;
		this.id = "L" + num;
		num++;
	}

	public String getId() {
		return this.id;
	}


	public int getVitesse() {
		return this.vitesse;
	}

	public void seDeplace() {
		if (this.getX() > 15) {
			this.avance();
		}

		else if (this.getY() > 25 + (((int) (Math.random() * 28)) - 14) && this.getX() <= 15) {
			this.monte();
		}

		else if (this.getY() <= 25 + (((int) (Math.random() * 28)) - 14) && this.getX() <= 15) {
			this.descends();
		}

	}

	public ArrayList<int[][]> seDeplaceBFS(int posCibleX, int posCibleY) {
		return null;
	}
	
	public void avance() {
		int i = 0;
		while (this.getX() > 15 && i < this.vitesse) {
			this.setX(this.getX() - 1);
			i++;
		}
		if (i < this.vitesse) {
			while (i < this.vitesse) {
				if (this.getY() < 39) {
					this.setY(this.getY()+1);
					i++;
				} else if (this.getY() > 11) {
					this.setY(this.getY()-1);
					i++;
				}
			}
		}
	}

	public void monte() {
		int i = 0;
		while (this.getY() > 11 && this.vitesse > i) {
			this.setY(this.getY()-1);
			i++;
		}
		while (this.getY() < 39 && this.vitesse > i) {
			this.setY(this.getY()+1);
			i++;
		}
		if (i < this.vitesse) {
			this.monte();
		}
	}

	public void descends() {
		int i = 0;
		while (this.getY() < 39 && this.vitesse > i) {
			this.setY(this.getY()+1);
			i++;
		}
		while (this.getY() > 11 && this.vitesse > i) {
			this.setY(this.getY()-1);
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

	public Acteur verifie(int x, int y, int peri) {
		ArrayList<Acteur> listeTours = new ArrayList<Acteur>();
		for (int i = 0; i < this.getJeu().getListe().size();i++) {
			if(this.getJeu().getListe().get(i) instanceof Tour) {
				listeTours.add(this.getJeu().getListe().get(i));
			}
		}
		for (int i = 0; i < listeTours.size(); i++) {
			if ((listeTours.get(i).getY() > y - peri && listeTours.get(i).getY() < y + peri)
					&& (listeTours.get(i).getX() > x - peri && listeTours.get(i).getX() < x + peri)) {
				return listeTours.get(i);
			}
		}

		return null;
	}

	public void changeCible() {
		if (!this.getCible().estVivant()) {
			cible();
		}
	}
	
	public void attaqueTour() {
		if ((this.getX() + 1 == this.getCible().getX() || this.getX() - 1 == this.getCible().getX()
				|| this.getX() == this.getCible().getX())
				&& (this.getY() + 1 == this.getCible().getY() || this.getY() - 1 == this.getCible().getY()
						|| this.getY() == this.getCible().getY())) {
			arrete();
			// on affiche sur la console que le loup attaque
			System.out.println("loup attaque Tour");
			this.getCible().seFaitAttaquer(this.getPtsATT());
			if(!this.getCible().estVivant()) {
				remarche();
			}
		}
	}
	
	public void attaqueMaison() {
		if ((this.getX()== 15 &&(this.getY() <= 38 && this.getY() >= 11)) && this.getJeu().getMaison().estVivant()) {
			System.out.println("loup attaque Maison");
			arrete();
			this.getJeu().getMaison().seFaitAttaquer(this.getPtsATT());
		}
	}

	public void agit() {
            seDeplace();
            if (this.getCible() != null) {
                attaqueTour();
                changeCible();
            } else {
            	cible();
            }
            attaqueMaison();
    }
	
	public String toString() {
		return "Loup "+this.id+" : (" + this.getX() + ", " + this.getY() + "), PV : " + this.getPV();
	}
}