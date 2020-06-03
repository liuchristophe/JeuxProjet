package la_revenche_des_loups.modele;

import java.util.ArrayList;



public class Jeu {

	private Terrain terrain;
	private ArrayList<Acteur> listeActeurs;
	private ArrayList<Loup> listeLoups;
	private int nbLoups = 0;

	public Jeu(Terrain t) {
		this.terrain = t;
		this.listeActeurs = new ArrayList<Acteur>();
		this.listeLoups = new ArrayList<Loup>();
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
	
	public void ajouterLoup() {
		this.listeLoups.add(new Loup(this));
		this.nbLoups++;
	}

	public void retirerLoup(Loup l) {
		this.listeLoups.remove(l);
	}

	public void ajouterTour(Tour t) {
		this.listeActeurs.add(t);
		System.out.println("ajout de tour");
	}

	public void retirerTour(Tour t) {
		this.listeActeurs.remove(t);
	}
	
	public boolean loupSontMort() {
		for(int i = 0; i < this.listeLoups.size(); i++) {
			if (this.listeLoups.get(i).estVivant()) {
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
			System.out.println(this.listeActeurs.size());
			this.listeActeurs.get(i).agit();
		}
	}

	public boolean finPartie() {
		if(!this.getMaison().estVivant()) {
			return true;
		}
		return false;
	}
	
	public boolean finVague(int nbLoups) {
		if (this.nbLoups==nbLoups && loupSontMort()) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	/*
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
			}
		}
		//Tour comme obstacle taille 3x3
		
		for(Tour t : ListeTours) {
			int x = t.getX();
			int y = t.getY();
			for(int idY = y-1; idY < y+2; idY++) {
				for(int idX = x-1; idX < x+2; idX++) {
					tab[idX*idY] = 1;
				}
			}
		}
		return tab;
		
	}
	
	
	//Si renvoie list alors bfs.list(int[0] == arrivée && int[1] == départ)
	public ArrayList<int[]> bfs(int x, int y) { //idCible losque x + y*tailleTerrain en 1 dimension
		int largeurTerrain = this.terrain.getLargeur();
		int tailleTerrain = this.terrain.getHauteur()*largeurTerrain;
		int[] tabMarquer = this.tableauObstacle().clone();
		ArrayList<Integer> pile = new ArrayList<Integer>();
		ArrayList<int[]> bfs = new ArrayList<int[]>();
		pile.add(y*largeurTerrain + x);
		while(pile.size() > 0) {
			int id = pile.get(0);
			//Avant
			int idA = id-1;
			passageBFS(tailleTerrain, idA, id, tabMarquer, bfs);
			//Diagonal bas gauche
			int idDBG = id-1+largeurTerrain;
			passageBFS(tailleTerrain, idDBG, id, tabMarquer, bfs);
			//Diagonal haut gauche
			int idDHG = id-1-largeurTerrain;
			passageBFS(tailleTerrain, idDHG, id, tabMarquer, bfs);
			//Haut
			int idH = id - largeurTerrain;
			passageBFS(tailleTerrain, idH, id, tabMarquer, bfs);
			//Bas
			int idB = id + largeurTerrain;
			passageBFS(tailleTerrain, idB, id, tabMarquer, bfs);
			//Diagonal bas droit
			int idDBD = id + 1 +largeurTerrain;
			passageBFS(tailleTerrain, idDBD, id, tabMarquer, bfs);
			//Diagonal haut droit
			int idDHD = id + 1 + largeurTerrain;
			passageBFS(tailleTerrain, idDHD, id, tabMarquer, bfs);
			
			pile.remove(0);
		}
		return bfs;
	}
	
	private static void passageBFS(int tailleTerrain, int idArriver, int idPrecedent, int[] tabMarquer,ArrayList<int[]> bfs) {
		if(idArriver < tailleTerrain) {
			if(tabMarquer[idArriver] == 0) {
				int[] donné = {idArriver,idPrecedent};
				bfs.add(donné);
				tabMarquer[idArriver] = 1;
			}
		}
	}
*/
}