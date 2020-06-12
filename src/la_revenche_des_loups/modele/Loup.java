package la_revenche_des_loups.modele;


import la_revenche_des_loups.modele.Jeu;

import java.util.ArrayList;

<<<<<<< HEAD

public class Loup extends Acteur{
=======
public abstract class Loup extends Acteur{
>>>>>>> dev
	private int vitesse;
	private String id;
	private static int num = 0;
	protected Bfs bfs;
	protected ArrayList<Integer> chemin;
	protected boolean cibleTour;
	
	public Loup(Jeu jeu, int pv, int perimetre, int degat) {
		super(jeu, 99, (int) (Math.random() * 46) + 2, pv, perimetre, degat);
		this.vitesse = 1;
		this.id = "L" + num;
		num++;
		this.bfs = new Bfs(super.getJeu());
		int idCible = ((int)(Math.random()*25 + 0)+13)*this.getJeu().getTerrain().getLargeur()+15-103;
		this.chemin = this.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
		this.cibleTour = false;
	}
	
	public String getId() {
		return this.id;
	}

	public ArrayList<Integer> getChemin() {
		return this.chemin;
	}
	
	public abstract void tracerChemin(Acteur cible);
	
	public void seDeplace(Acteur cible) {
		tracerChemin(cible);
		if(this.chemin == null) {
			System.out.println("Loup.seDeplace[chemin = null]");
		}
		else {
			if(this.chemin.size()>0) {
				for(int i = 0; i<this.vitesse; i++) {
					int dir;
					if(this.chemin.size()<2) {
						dir = 0;
					}
					else {
						dir = this.chemin.get(0) - this.chemin.get(1);
					}
					switch(dir) {
					case 1:
						this.setX(this.getX()-1);
						break;
					case 100:
						this.setY(this.getY()-1);
						break;
					case -100:
						this.setY(this.getY()+1);
						break;
					default:
						//tu ne fait rien
						break;
					}
				}
				this.chemin.remove(0);
			}
		}
	}
	
	public void arrete() {
		this.vitesse = 0;
	}

	public void remarche() {
		this.vitesse = 1;
	}

	//permet de vérifier si une tour se trouve dans le périmetre d'un loup
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
		if ((this.getX() + 3 >= this.getCible().getX() || this.getX() - 3 <= this.getCible().getX()
				|| this.getX() == this.getCible().getX())
				&& (this.getY() + 3 >= this.getCible().getY() || this.getY() - 3 <= this.getCible().getY()
						|| this.getY() == this.getCible().getY())) {
			arrete();
			
			super.getJeu().setNumeroAction(1);
			
			this.getCible().seFaitAttaquer(this.getPtsATT());
			if(!this.getCible().estVivant()) {
				remarche();
			}
		}
	}
	
	public void attaqueMaison() {
		if ((this.getX()== 15 &&(this.getY() <= 38 && this.getY() >= 11)) && this.getJeu().getMaison().estVivant()) {
			arrete();
			
			super.getJeu().setNumeroAction(0);
			
			this.getJeu().getMaison().seFaitAttaquer(this.getPtsATT());
		}
	}

	//Cette methode englobe toutes les action du loup lors d'un tour de jeu
	public void agit() {
            if (this.getCible() != null) {
                attaqueTour();
                changeCible();
            } else {
            	cible();
                seDeplace(this.getCible());
            }
            attaqueMaison();
    }
	
	public String toString() {
		return "Loup "+this.id+" : (" + this.getX() + ", " + this.getY() + "), PV : " + this.getPV();
	}
}