
package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Loup extends Acteur{
	private int vitesse;
	private String id;
	private static int num = 0;
	private Bfs bfs;
	private ArrayList<Integer> chemin;
	private boolean cibleTour;
	
	public Loup(Jeu j) {
		super(j, 99, (int) (Math.random() * 46) + 2, 30, 4, 4);
		this.vitesse = 1;
		this.id = "L" + num;
		num++;
		this.bfs = new Bfs(super.getJeu());
		int idCible = ((int)(Math.random()*25 + 0)+13)*this.getJeu().getTerrain().getLargeur()+15-103;
		this.chemin = this.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
		this.cibleTour = false;
//		this.chemin = null;
	}

	public String getId() {
		return this.id;
	}
	
	public int getVitesse() {
		return this.vitesse;
	}

//	public void deplacerAvecBfs(Acteur cible) {
//		int idCible = cible.obtenirIdPosition();
//		ArrayList<Integer> cheminBfs = this.bfs.cheminBfs(this.getX(), this.getY(), idCible);
//		System.out.println("Loup.deplacerAvecBfs"+cheminBfs);
//	}

	public ArrayList<Integer> getChemin() {
		return this.chemin;
	}
	
	public void seDeplace(Acteur cible) {
		int idCible;
		if(cible == null && this.cibleTour) {
			idCible = ((int)(Math.random()*25 + 0)+13)*this.getJeu().getTerrain().getLargeur()+15-103;
			System.out.println("Loup seDeplace idCible: "+idCible);
			this.chemin = this.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
			this.cibleTour = false;
		}
		else if(cible != null){
			idCible = cible.obtenirIdPosition();
			this.chemin = this.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
			this.cibleTour = true;
		}
		
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
//						System.out.println("Loup.seDeplace[avance car "+this.chemin.get(0)+"-"+this.chemin.get(1)+" = "+((int) this.chemin.get(0)-this.chemin.get(1))+"]");
						break;
						
					case 100:
						this.setY(this.getY()-1);
//						System.out.println("Loup.seDeplace[monte car "+this.chemin.get(0)+"-"+this.chemin.get(1)+" = "+((int) this.chemin.get(0)-this.chemin.get(1))+"]");
						break;
						
					case -100:
						this.setY(this.getY()+1);
//						System.out.println("Loup.seDeplace[descend car "+this.chemin.get(0)+"-"+this.chemin.get(1)+" = "+((int) this.chemin.get(0)-this.chemin.get(1))+"]");
						break;
						
					default:
						//tu ne fait rien
						System.out.println("Loup.seDeplace[Le loup s'arret soit il est arriver, soit y a un bug]");
						break;
					}
				}
				this.chemin.remove(0);
				System.out.println("Loup.seDeplace[x: "+this.getX()+" et y: "+this.getY()+"]");
			}
			else {
				System.out.println("Loup seDeplace est arriver au maison");
			}
		}
	}
	
	public void arrete() {
		this.vitesse = 0;
	}

	public void remarche() {
		this.vitesse = 1;
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
		if ((this.getX() + 3 >= this.getCible().getX() || this.getX() - 3 <= this.getCible().getX()
				|| this.getX() == this.getCible().getX())
				&& (this.getY() + 3 >= this.getCible().getY() || this.getY() - 3 <= this.getCible().getY()
						|| this.getY() == this.getCible().getY())) {
			arrete();
			// on affiche sur la console que le loup attaque
			System.out.println("Loup.attaqueTour [ loup attaque Tour ]");
			
			//Test Affichage
			super.getJeu().setNumeroAction(1);
			
			this.getCible().seFaitAttaquer(this.getPtsATT());
			if(!this.getCible().estVivant()) {
				remarche();
			}
		}
	}
	
	public void attaqueMaison() {
		if ((this.getX()== 15 &&(this.getY() <= 38 && this.getY() >= 11)) && this.getJeu().getMaison().estVivant()) {
			System.out.println("Loup.attaqueMaison [ loup attaque Maison ]");
			arrete();
			
			//Test Affichage
			super.getJeu().setNumeroAction(0);
			
			
			this.getJeu().getMaison().seFaitAttaquer(this.getPtsATT());
		}
	}

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