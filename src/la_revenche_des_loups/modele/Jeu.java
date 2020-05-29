package la_revenche_des_loups.modele;

import java.util.ArrayList;
import java.util.Collections;

public class Jeu {

	private Terrain terrain;
<<<<<<< HEAD
	private ArrayList<Loup> ListeLoups;
	private ArrayList<Tour> ListeTours;
	private Maison maison;
	private int[] tableauObstacle;
=======
	private ArrayList<Acteur> listeActeurs;
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git

	public Jeu(Terrain t) {
		this.terrain = t;
<<<<<<< HEAD
		this.ListeLoups = new ArrayList<Loup>();
		this.ListeTours = new ArrayList<Tour>();
		this.maison = new Maison(this);
		this.tableauObstacle = new int[this.terrain.getLargeur()*this.terrain.getHauteur()];
		this.initTableauObstacle();
=======
		this.listeActeurs = new ArrayList<Acteur>();
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git
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
<<<<<<< HEAD
		this.ListeLoups.add(l);
=======
		this.listeActeurs.add(l);
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git
	}

	public void retirerLoup(Loup l) {
		this.listeActeurs.remove(l);
	}

	public void ajouterTour(Tour t) {
<<<<<<< HEAD
		this.ListeTours.add(t);
=======
		this.listeActeurs.add(t);
		System.out.println("ajout de tour");
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git
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
<<<<<<< HEAD

	//Tour comme obstacle taille 3x3
	public void ajoutObstacleTour(int x, int y) {
		for(int idY = y; idY < y+3; idY++) {
			for(int idX = x; idX < x+3; idX++) {
				this.tableauObstacle[idX+idY*this.terrain.getLargeur()] = 1;
=======
	
	
	
	
	
	
	
	
	public int[] tableauObstacle() {
		int tailleTerrain = this.terrain.getLargeur()*this.terrain.getHauteur();
		int tab[] = new int[tailleTerrain];
		for(int i=0; i < tailleTerrain; i++) {
			int id = this.terrain.codeTuile(i);
			if( id == 121 || id == 194 || id == 362 || id == 340) {
				tab[i] = 1;
			}
			else if(id == 160 || id == 5) {
				tab[i] = 0;
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git
			}
		}
<<<<<<< HEAD
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
=======
		//Tour comme obstacle taille 3x3
		/*
		for(Tour t : ListeTours) {
			int x = t.getX();
			int y = t.getY();
			for(int idY = y-1; idY < y+2; idY++) {
				for(int idX = x-1; idX < x+2; idX++) {
					tab[idX*idY] = 1;
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git
				}
			}
		}
<<<<<<< HEAD
		return verifie;
=======
		return tab;
		*/
>>>>>>> branch 'dev' of https://github.com/liuchristophe/JeuxProjet.git
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
	
	
	
	//Si renvoie list alors bfs.list(int[0] == arrivée && int[1] == départ)
	public ArrayList<Integer> bfs(int x, int y, int idCible) { //idCible losque x + y*tailleTerrain en 1 dimension
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
			//Recule
//			if(id%100!=99) {
//				int idR = id + 1;
//				passageBFS(tailleTerrain, idR, id, tabMarquer, bfsArriver, bfsDepart, pile);
				pile.remove(0);
//			}
		}
		System.out.println("partie 2 Jeu.bfs");
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		int depart = y*largeurTerrain + x;
		int arriver = idCible;
		boolean erreur = false;
		while(arriver != depart && !erreur) {
			int i=bfsArriver.size()-1;
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
		return chemin;
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
	
	public boolean existeTour() {
		return ListeTours.size()>0 ? true : false;
	}

}