package la_revenche_des_loups.modele;

import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Jeu {

	private Terrain terrain;
	private int[] tableauObstacle;
	private ArrayList<Acteur> listeActeurs;
	private IntegerProperty limiteTours;
	private IntegerProperty nombreTours;

	public Jeu(Terrain t) {
		this.terrain = t;
		this.tableauObstacle = new int[this.terrain.getLargeur()*this.terrain.getHauteur()];
		this.initTableauObstacle();
		this.listeActeurs = new ArrayList<Acteur>();
		this.limiteTours = new SimpleIntegerProperty(5);
		this.nombreTours = new SimpleIntegerProperty(0);
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
	
	public Acteur getMaison() {
		for(int i = 0; i < this.listeActeurs.size(); i++) {
			if (this.listeActeurs.get(i) instanceof Maison) {
				return this.listeActeurs.get(i);
			}
		}
		return null;
	}
/*
	public Loup getPremierLoup() {
		for(int i = 0; i < this.listeActeurs.size(); i++) {
			if (this.listeActeurs.get(i) instanceof Loup) {
				return this.listeActeurs.get(i);
			}
		}
		return null;
	}
*/
	
	public void ajouterActeur(Acteur a) {
		this.listeActeurs.add(a);
	}
	
	public void retirerActeur(Acteur a) {
		this.listeActeurs.remove(a);
	}
	
	public void ajouterLoup(Loup l) {
		this.listeActeurs.add(l);
	}

	public void retirerLoup(Loup l) {
		this.listeActeurs.remove(l);
	}

	public void ajouterTour(Tour t) {
		this.listeActeurs.add(t);
		this.nombreTours.setValue(this.nombreTours.getValue()+1);;
	}
	
	public boolean limiterTours() {
		return this.limiteTours.getValue() > this.nombreTours.getValue() ? true : false;
	}

	public void retirerTour(Tour t) {
		this.listeActeurs.remove(t);
	}
	
	public boolean loupSontMort() {
		for(int i = 0; i < this.listeActeurs.size(); i++) {
			if (this.listeActeurs.get(i) instanceof Loup && this.listeActeurs.get(i).estVivant()) {
				return false;
			}
		}
		return true;
	}

	public void reintialiser() {
		this.terrain = new Terrain();
		this.listeActeurs = new ArrayList<Acteur>();
	}

	public void agir() {
		for(int i = 0; i < this.listeActeurs.size(); i++) {
			this.listeActeurs.get(i).agit();
		}
	}

	public boolean finPartie() {
		if(loupSontMort() || !this.getMaison().estVivant()) {
			return true;
		}
		return false;
	}
	
	public void vague() {
		
	}
	
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
	
	// //////////////////////////////////////BFS////////////////////////////////////// //
	
	
	//Tour comme obstacle taille 3x3
	public void ajoutObstacleTour(int x, int y) {
		for(int idY = y; idY < y+3; idY++) {
			for(int idX = x; idX < x+3; idX++) {
				this.tableauObstacle[idX+idY*this.terrain.getLargeur()] = 1;
			}
		}
	}
	
	public void ajoutBFS(ArrayList<Integer> bfs) {
		for(int id : bfs) {
			this.tableauObstacle[id] = 2;
		}
	}
	
	//Retourne un donnée dans tableauObstacle
	//Si tab[id] = 1 alors obstacle Sinon tab[id] = 0 pas d'obstacle
	public int retourTableauObstacle(int x, int y) {
		return tableauObstacle[x+y*this.terrain.getLargeur()];
	}
	
	public boolean verifieObstacle(int x, int y) {
		boolean verifie = false;
		for(int idY = y; idY < y+3; idY++) {
			for(int idX = x; idX < x+3; idX++) {
				if(retourTableauObstacle(idX, idY) == 1) {
					verifie = true;
				}
			}
		}
		return verifie;
	}
	
	
	public int[] tabMarquer(int x, int y, int idCible) { //idCible losque x + y*tailleTerrain en 1 dimension
		int largeurTerrain = this.terrain.getLargeur();
		int tailleTerrain = this.terrain.getHauteur()*largeurTerrain;
		int[] tabMarquer = this.tableauObstacle.clone();
		ArrayList<Integer> pile = new ArrayList<Integer>();
		ArrayList<Integer> bfsArriver = new ArrayList<Integer>();
		ArrayList<Integer> bfsDepart = new ArrayList<Integer>();
		pile.add(y*largeurTerrain + x);
		tabMarquer[y*largeurTerrain + x] = 1;
		while(pile.size() > 0) {
			int id = pile.get(0);
			//Avant
			int idA = id-1;
			passageBFS(tailleTerrain, idA, id, tabMarquer, bfsArriver, bfsDepart, pile);
			//Haut
			int idH = id - largeurTerrain;
			passageBFS(tailleTerrain, idH, id, tabMarquer, bfsArriver, bfsDepart, pile);
			//Bas
			int idB = id + largeurTerrain;
			passageBFS(tailleTerrain, idB, id, tabMarquer, bfsArriver, bfsDepart, pile);
			pile.remove(0);
		}
		return tabMarquer;
	}
	
	//Si renvoie 1 = avance, 2 = monte, 3 = descend)+
	public int bfs(int x, int y, int idCible) { //idCible losque x + y*tailleTerrain en 1 dimension
		int largeurTerrain = this.terrain.getLargeur();
		int tailleTerrain = this.terrain.getHauteur()*largeurTerrain;
		int[] tabMarquer = this.tableauObstacle.clone();
		ArrayList<Integer> pile = new ArrayList<Integer>();
		ArrayList<Integer> bfsArriver = new ArrayList<Integer>();
		ArrayList<Integer> bfsDepart = new ArrayList<Integer>();
		int pos = y*largeurTerrain + x;
		pile.add(pos);
		tabMarquer[y*largeurTerrain + x] = 1;
		while(pile.size() > 0) {
			int id = pile.get(0);
			//Avant
			int idA = id-1;
			passageBFS(tailleTerrain, idA, id, tabMarquer, bfsArriver, bfsDepart, pile);
			//Haut
			int idH = id - largeurTerrain;
			passageBFS(tailleTerrain, idH, id, tabMarquer, bfsArriver, bfsDepart, pile);
			//Bas
			int idB = id + largeurTerrain;
			passageBFS(tailleTerrain, idB, id, tabMarquer, bfsArriver, bfsDepart, pile);
			//Recule
//			if(id%100!=99) {
//				int idR = id + 1;
//				passageBFS(tailleTerrain, idR, id, tabMarquer, bfsArriver, bfsDepart, pile);
				pile.remove(0);
//			}
		}
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		int depart = y*largeurTerrain + x;
		int arriver = idCible;
		boolean erreur = false;
		while(arriver != depart && !erreur) {
			int i=bfsArriver.size()-1; // /////////////////////////////////A verifier////////////
			while(i>0 && bfsArriver.get(i) != arriver) {
				i--;
			}
			if(bfsArriver.get(i) == arriver){
				chemin.add(arriver);
				arriver = bfsDepart.get(i);
			}
			else {
				erreur = true;
				System.out.println("Jeu.bfs[ ne trouve pas ]");
				System.out.println(idCible + " existe?" + bfsArriver.contains(idCible));
			}
		}
		Collections.reverse(chemin);
		// Convertir bfs pour Loup seDeplace
		int idPrec = pos;
		int idSuiv = chemin.get(0);
		
		// pour avancer id = 2000 - 1999 == 1
		if(idPrec-idSuiv == 1) {
			return 1;
		}
		// pour monter id = 2000 - 1900 == 100
		else if(idPrec-idSuiv == this.getTerrain().getLargeur()) {
			return 2;
		}
		// pour descendre id = 2000 - 2100 == -100
		else if(idPrec-idSuiv == -this.getTerrain().getLargeur()) {
			return 3;
		}
		return 0;
	}
	
	//Methode permettant ajouter un chemin de tous les bfs puis marque sur un tableau comme case utiliser
	//donnée[0] = idArriver donnée[1] = idPrecedent
	private static void passageBFS(int tailleTerrain, int idArriver, int idPrecedent, int[] tabMarquer, ArrayList<Integer> bfsArriver, ArrayList<Integer> bfsDepart, ArrayList<Integer> pile) {
		if(idArriver < tailleTerrain) {
			if(tabMarquer[idArriver] == 0) {
				bfsArriver.add(idArriver);
				bfsDepart.add(idPrecedent);
				pile.add(idArriver);
				tabMarquer[idArriver] = 1;
			}
		}
	}

}