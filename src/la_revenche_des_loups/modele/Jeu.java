package la_revenche_des_loups.modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Blanc;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Gris;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Fantome;
import la_revenche_des_loups.vue.LoupVue;



public class Jeu {

	private Terrain terrain;
	private int[] tableauObstacle;
	private ArrayList<Acteur> listeActeurs;
	private ArrayList<Loup> listeLoups;
	private ArrayList<Tour> listeTour;
	private ArrayList<Tir> listeTirs;
	private int nbLoups = 0;
	private boolean partieLance;
	private int numeroAction;
	private Bfs bfs;
	private HistoriqueAction historique;
	private IntegerProperty limiteTours;
	private IntegerProperty nombreTours;
	private IntegerProperty monnaie;

	public Jeu(Terrain t) {
		this.terrain = t;
		this.tableauObstacle = new int[this.terrain.getLargeur()*this.terrain.getHauteur()];
		this.initTableauObstacle();
		this.listeActeurs = new ArrayList<Acteur>();
		this.listeLoups = new ArrayList<Loup>();
		this.listeTour = new ArrayList<Tour>();
		this.limiteTours = new SimpleIntegerProperty(5);
		this.nombreTours = new SimpleIntegerProperty(0);
		this.partieLance = false;
		this.bfs = new Bfs(this);
		this.listeTirs = new ArrayList<Tir>();
		this.monnaie = new SimpleIntegerProperty(1200);
	}
	
	public Bfs getBfs() {
		return this.bfs;
	}
	
	public IntegerProperty getMonnaieProperty() {
		return this.monnaie;
	}
	
	public int getMonnaie() {
		return this.monnaie.getValue();
	}
	
	public IntegerProperty getLimiteToursProperty() {
		return this.limiteTours;
	}
	
	public int getLimiteTours() {
		return this.limiteTours.getValue();
	}
	
	public IntegerProperty getNombreToursProperty() {
		return this.nombreTours;
	}
	
	public int getNombreTours() {
		return this.nombreTours.getValue();
	}
	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public ArrayList<Acteur> getListe() {
		return this.listeActeurs;
	}
	
	public ArrayList<Loup> getListeLoups() {
		return this.listeLoups;
	}
	
	public ArrayList<Tour> getListeTours() {
		return this.listeTour;
	}
	
	public ArrayList<Tir> getListeTirs(){
		return this.listeTirs;
	}
	
	public boolean getPartiEstLance() {
		return this.partieLance;
	}
	
	public void changeStatutParti() {
		if(this.partieLance) {
			this.partieLance = false;
		}
		else {
			this.partieLance = true;
		}
	}
	
	public Acteur getMaison() {
		for(int i = 0; i < this.listeActeurs.size(); i++) {
			if (this.listeActeurs.get(i) instanceof Maison) {
				return this.listeActeurs.get(i);
			}
		}
		return null;
	}

	
	public void ajouterActeur(Acteur a) {
		this.listeActeurs.add(a);
	}
	
	public void retirerActeur(Acteur a) {
		this.listeActeurs.remove(a);
	}
	
	public void ajouterLoup() {
        if(this.nbLoups%10==0&&this.nbLoups!=0) {
            this.listeLoups.add(new Loup_Blanc(this));
        }
        else if(this.nbLoups%4==0&&this.nbLoups!=0) {
            this.listeLoups.add(new Loup_Fantome(this));
        }
        else{
            this.listeLoups.add(new Loup_Gris(this));
        }
        this.nbLoups++;
    }

	public void retirerLoup(Loup l) {
		this.listeLoups.remove(l);
	}

	public void ajouterTour(Tour t) {
		this.listeTour.add(t);
		this.nombreTours.setValue(this.nombreTours.getValue()+1);;
	}
	
	public void retirerTour(Tour t) {
		this.listeActeurs.remove(t);
		this.listeTour.remove(t);
	}
	
	

	//selection du numero d'action lors de l'affichage de l'historique
	public int getNumeroAction(){
		return this.numeroAction;
	}
	public void setNumeroAction(int num){
		this.numeroAction = num;
		this.historique.miseAJourListeDefile(this.numeroAction);
	}
	public void initHistorique(HistoriqueAction historique) {
		this.historique = historique;
	}
	
	
	//test payer tour
	public void payerTour(int prix) {
		if (prix <= this.monnaie.getValue()) {
			this.monnaie.setValue(this.monnaie.getValue()-prix);
		}
	}
	
	
	
	//Avoir un tableau pour voir s'il y a un obstacle
	//Si tab[id] = 1 alors obstacle Sinon tab[id] = 0 pas d'obstacle
	public void initTableauObstacle() {
		int tailleTerrain = this.terrain.getLargeur()*this.terrain.getHauteur();
		for(int i=0; i < tailleTerrain; i++) {
			int id = this.terrain.codeTuile(i);
			if( id == 121 || id == 194 || id == 362 || id == 340) {
				this.tableauObstacle[i] = 1;
			}
			else if(id == 160 || id == 5) {
				this.tableauObstacle[i] = 0;
			}
		}
	}
	
	
	
	public boolean limiterTours() {
		return this.limiteTours.getValue() > this.nombreTours.getValue() ? true : false;
	}
	
	public boolean loupSontMort() {
		for(int i = 0; i < this.listeLoups.size(); i++) {
			if (this.listeLoups.get(i).estVivant()) {
				return false;
			}
		}
		return true;
	}
	
	
	// ci dessous les méthodes d'action durant la parti
	
	public void agir() {
		for(int i = 0; i < this.listeActeurs.size(); i++) {
			this.listeActeurs.get(i).agit();
		}
	}

	public boolean finPartie() {
		if(!this.getMaison().estVivant()) {
			return true;
		}
		return false;
	}
	
	public boolean finVague(int nbLoups, int numVague) {
		if (this.nbLoups==nbLoups && loupSontMort()) {
			this.monnaie.set(this.monnaie.getValue()+450*numVague);
			return true;
		}
		return false;
	}
	
	
	//Déplacement methode de gameloop
	public void nouveauLoup(int nbFrame,int nbLoups) {
        if (nbFrame % 10 == 0 && this.getListeLoups().size()<nbLoups) {
            this.ajouterLoup();
        }
    }

    public void vague(int nbFrames,LoupVue loupVue,int nbLoups) {
        nouveauLoup(nbFrames,nbLoups);
        for (int i = 0; i < this.getListeLoups().size(); i++) {
            loupVue.afficherLoupVue(this.getListeLoups().get(i));
        }
        agir();
    }
	
	//permet de vérifier ci des tours sont deja placer lors du placement d'une tour dans le jeu
	public boolean verifieTourAlentour(int x, int y, int espacement) {
		for(Acteur a : this.listeActeurs) {
			if(a instanceof Tour) {
				for(int yy = a.getY(); yy < a.getY()+a.getTaille(); yy++) {
					for(int xx = a.getX(); xx < a.getX()+a.getTaille(); xx++) {
						if(xx <= x+espacement+2 && xx >= x-espacement+2 && yy <= y+espacement+2 && yy >= y-espacement+2) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void miseAJourToursLimite() {
		this.limiteTours.set(this.limiteTours.get()+1);
		this.nombreTours.set(this.nombreTours.get()-1);
	}

}