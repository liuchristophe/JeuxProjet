package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Jeu {

	private Terrain terrain;
	private ArrayList<Loup> ListeLoups;
	private ArrayList<Tour> ListeTours;
	private Maison maison;

	public Jeu(Terrain t) {
		this.terrain = t;
		this.ListeLoups = new ArrayList<Loup>();
		this.ListeTours = new ArrayList<Tour>();
		this.maison = new Maison(this);
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

	public Loup getPremierLoup() {
		return this.ListeLoups.get(0);
	}

	public void ajouterLoup(Loup l) {
		this.ListeLoups.add(l);
		System.out.println("ajout d'un loup");
	}

	public void retirerLoup(Loup l) {
		this.ListeLoups.remove(l);
	}

	public void ajouterTour(Tour t) {
		this.ListeTours.add(t);
		System.out.println("ajout de tour");
	}

	public void retirerTour(Tour t) {
		this.ListeTours.remove(t);
	}

	public void reintialiser() {
		this.terrain = new Terrain();
		this.ListeLoups = new ArrayList<Loup>();
	}

	// Permet de vérifier si un loup se situe dans le périmètre d'attaque d'une tour
	public Loup verifieLoupTour(int x, int y, int peri) {
		for (int i = 0; i < this.ListeLoups.size(); i++) {
			if (((this.ListeLoups.get(i).getX() > x - peri) && (this.ListeLoups.get(i).getX() < x + peri))
					&& ((this.ListeLoups.get(i).getY() > y - peri) && (this.ListeLoups.get(i).getY() < y + peri))) {
				return this.ListeLoups.get(i);
			}
		}

		return null;
	}

	// Permet de vérifier si un loup se situe dans le périmètre d'attaque de la
	// maison
	public Loup verifieLoupMaison(int x, int y, int peri) {
		for (int i = 0; i < this.ListeLoups.size(); i++) {
			if ((this.ListeLoups.get(i).getX() < x + peri) && ((this.ListeLoups.get(i).getY() > y - (peri + 22))
					&& (this.ListeLoups.get(i).getY() < y + peri))) {
				return this.ListeLoups.get(i);
			}
		}

		return null;
	}

	// Permet de vérifier si une tour se situe dans le périmètre d'attaque d'un loup
	public Tour verifieTour(int x, int y, int peri) {
		for (int i = 0; i < this.ListeTours.size(); i++) {
			if ((this.ListeTours.get(i).getY() > y - peri && this.ListeTours.get(i).getY() < y + peri)
					&& (this.ListeTours.get(i).getX() > x - peri && this.ListeTours.get(i).getX() < x + peri)) {
				return this.ListeTours.get(i);
			}
		}

		return null;
	}

	// Les methodes ci-dessous sont les methodes d'action des différents acteurs

	// Le loup se déplace s'arrete lorsque il y a une tour dans son périmètre
	// d'attaque, puis attaque
	public void agitLoup() {
		for (int i = 0; i < this.ListeLoups.size(); i++) {
			this.ListeLoups.get(i).seDeplace();
			if (this.ListeLoups.get(i).getTourCible() != null) {
				this.ListeLoups.get(i).attaqueTour();
				this.ListeLoups.get(i).changeCible();
			} else {
				this.ListeLoups.get(i).tourCible();
			}
		}
	}

	public void agitTour() {
		for (int i = 0; i < this.ListeTours.size(); i++) {
			if (this.ListeTours.get(i).getLoupCible() != null) {
				this.ListeTours.get(i).seDefend();
				// on affiche sur la console que la tour se défend
				System.out.println("Tour " + this.ListeTours.get(i).getId() + " se d�fend");
				this.ListeTours.get(i).changeCible();
			} else {
				this.ListeTours.get(i).loupcible();
			}
		}
	}

	public void agitMaison() {
		if (this.maison.getLoupCible() != null) {
			this.maison.seDefend();
			// on affiche sur la console que la maison se défend
			System.out.println("Maison se défend");
			this.maison.changeCible();
		} else {
			this.maison.loupcible();
		}
	}
	
	
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

}