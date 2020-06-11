package la_revenche_des_loups.modele;

import java.util.ArrayList;
import java.util.Collections;

public class Bfs {

	private Jeu jeu;
	private Terrain terrain;
	private int[] tableauObstacle;
	
	public Bfs(Jeu jeu) {
		this.jeu=jeu;
		this.terrain=this.jeu.getTerrain();
		this.tableauObstacle = new int[this.terrain.getHauteur()*this.terrain.getLargeur()];
		this.initTableauObstacle();
	}
	
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
	
	//liste.get(0) = listeDepart et liste.get(1) = listeVersCible
	public void listeDepartArriverBfs(int idDepart, ArrayList<Integer> listeDepart, ArrayList<Integer> listeVersCible){
		ArrayList<Integer> pile = new ArrayList<Integer>();
		int[] tabMarquage = this.tableauObstacle.clone();
		pile.add(idDepart);
		int idPrecedent;
		int idSuivant;
		while(pile.size() > 0) {
			idPrecedent = pile.get(0);
			
			//avance
			idSuivant = idPrecedent - 1;
			passageBFS(this.terrain.getTaille(), idSuivant, idPrecedent, tabMarquage, listeVersCible, listeDepart, pile);
			
			//monte
			idSuivant = idPrecedent - this.terrain.getLargeur();
			passageBFS(this.terrain.getTaille(), idSuivant, idPrecedent, tabMarquage, listeVersCible, listeDepart, pile);
			
			//descend
			idSuivant = idPrecedent + this.terrain.getLargeur();
			passageBFS(this.terrain.getTaille(), idSuivant, idPrecedent, tabMarquage, listeVersCible, listeDepart, pile);
			
			pile.remove(0);
		}
	}

	public ArrayList<Integer> cheminBfs(int x, int y, int idCible) {
		int idDepart = x+y*this.jeu.getTerrain().getLargeur();
		return this.cheminBfs(idDepart, idCible);
	}
	
	public ArrayList<Integer> cheminBfs(int idDepart, int idCible) {
		ArrayList<Integer> listeDepart = new ArrayList<Integer>();
		ArrayList<Integer> listeVersCible = new ArrayList<Integer>();
		this.listeDepartArriverBfs(idDepart, listeDepart, listeVersCible);
		int idSuivant;
		int posCible = idCible;
		
		if(!listeVersCible.contains(posCible)) {
			System.out.println();
			while(!listeVersCible.contains(posCible)) {
				posCible++;
			}
		}
		
//		if(tour) {
//			System.out.println("Bfs.cheminBfs[entre dans le cas du tour]");
//			posCible += 101;//Recentrer le pos de la tour
//			if(!listeVersCible.contains(idCible)) {
//				int i=0;
//				int posPrim = -1;
//				while(i<2){
//					
//					//cote tour droite
//					posPrim = this.cherchePosCible(posCible, listeVersCible, 2, i);
//					if(posPrim != -1) {
//						posCible = posPrim;
//					}
//					
//					//cote tour haute
//					posPrim = this.cherchePosCible(posCible, listeVersCible, -2*this.jeu.getTerrain().getLargeur(), i);
//					if(posPrim != -1) {
//						posCible = posPrim;
//					}
//					
//					//cote tour bas
//					posPrim = this.cherchePosCible(posCible, listeVersCible, 2*this.jeu.getTerrain().getLargeur(), i);
//					if(posPrim != -1) {
//						posCible = posPrim;
//					}
//					
//					//cote tour gauche
//					posPrim = this.cherchePosCible(posCible, listeVersCible, -2, i);
//					if(posPrim != -1) {
//						posCible = posPrim;
//					}
//					
//					i++;
//				}
//				
//				if(posPrim == -1) {
//					System.out.println("Bfs.cheminBfs[erreur pas de position alentour du tour trouver]");
//					posCible = idCible;
//				}
//			}
//		}
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		boolean fini = false;
		boolean erreur = false;
		idSuivant = posCible;
		while(!fini) {
			int i = 0;
			
			while(i<listeVersCible.size()) {
				if(listeVersCible.get(i) == idSuivant) {
					chemin.add(listeDepart.get(i));
					idSuivant = listeDepart.get(i);
					break;
				}
				i++;
			}
			
			if(i == listeVersCible.size()) {
				System.out.println("Bfs.cheminBfs erreur true");
				erreur = true;
				break;
			}
			
			if(idSuivant == idDepart) {
				break;
			}
		}
		
		if(erreur) {
			System.out.println("Bfs.cheminBfs[erreur du cheminement]");
		}
		
//		System.out.println("Bfs.cheminBfs[posCible: "+posCible+" et idCible: "+idCible+"]");
		Collections.reverse(chemin);
		return chemin;
	}
	
	private void passageBFS(int tailleTerrain, int idSuivant, int idPrecedent, int[] tabMarquage, ArrayList<Integer> listeVersCible, ArrayList<Integer> listeDepart, ArrayList<Integer> pile) {
		if(idSuivant < tailleTerrain) {
			if(tabMarquage[idSuivant] == 0) {
				listeVersCible.add(idSuivant);
				listeDepart.add(idPrecedent);
				pile.add(idSuivant);
				tabMarquage[idSuivant] = 1;
			}
		}
	}
	
	//dir signifie si c'est haut bas gauche droite avec -100, +100, -1, +1 
	public int cherchePosCible(int posCible, ArrayList<Integer> listeVersCible, int dir, int i) {
		if(dir == 2 || dir == -2) {
			i *= this.jeu.getTerrain().getLargeur();
		}
		
		int posCiblePrim = posCible+dir+i;
		if(listeVersCible.contains(posCiblePrim)) {
			return posCiblePrim;
		}
		posCiblePrim = posCible+dir-i;
		if(listeVersCible.contains(posCiblePrim)) {
			return posCiblePrim;
		}
		return -1;
	}
	
	// joueur place une tour
	public void ajoutObstacleTour(int x, int y) {
		for(int idY = y; idY < y+3; idY++) {
			for(int idX = x; idX < x+3; idX++) {
				this.tableauObstacle[idX+idY*this.terrain.getLargeur()] = 1;
			}
		}
	}
	
	// plus d'obstacle sur la tour car detruite
	public void enleveObstacleTour(int x, int y) {
		for(int idY = y; idY < y+3; idY++) {
			for(int idX = x; idX < x+3; idX++) {
				this.tableauObstacle[idX+idY*this.terrain.getLargeur()] = 0;
			}
		}
	}
	
	//pour BFSVue, affichage du chemin en orange
	public void ajoutBFS(ArrayList<Integer> bfs) {
		for(int id : bfs) {
			this.tableauObstacle[id] = 2;
		}
	}
	
	//Retourne un donnee dans tableauObstacle
	//Si tab[id] = 1 alors obstacle Sinon tab[id] = 0 pas d'obstacle
	public int retourTableauObstacle(int x, int y) {
		return tableauObstacle[x+y*this.terrain.getLargeur()];
	}
	
	//Si renvoie list alors bfs.list(int[0] == arrivee && int[1] == depart)
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
}
