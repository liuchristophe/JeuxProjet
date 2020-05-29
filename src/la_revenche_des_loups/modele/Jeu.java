package la_revenche_des_loups.modele;

import java.util.ArrayList;
import java.util.Collections;

public class Jeu {

	private Terrain terrain;
	private ArrayList<Loup> ListeLoups;
	private ArrayList<Tour> ListeTours;
	private Maison maison;
	private int[] tableauObstacle;

	public Jeu(Terrain t) {
		this.terrain = t;
		this.ListeLoups = new ArrayList<Loup>();
		this.ListeTours = new ArrayList<Tour>();
		this.maison = new Maison(this);
		this.tableauObstacle = new int[this.terrain.getLargeur()*this.terrain.getHauteur()];
		this.initTableauObstacle();
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

	public Loup getPremierLoup() {
		return this.ListeLoups.get(0);
	}

	public void ajouterLoup(Loup l) {
		this.ListeLoups.add(l);
	}

	public void retirerLoup(Loup l) {
		this.ListeLoups.remove(l);
	}

	public void ajouterTour(Tour t) {
		this.ListeTours.add(t);
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

	//Tour comme obstacle taille 3x3
	public void ajoutObstacleTour(int x, int y) {
		for(int idY = y; idY < y+3; idY++) {
			for(int idX = x; idX < x+3; idX++) {
				this.tableauObstacle[idX+idY*this.terrain.getLargeur()] = 1;
			}
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