package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Jeu {
	
	private Terrain terrain;
	private ArrayList<Loup>ListeLoups; 
	

	public Jeu(Terrain t){
		this.terrain=t;
		this.ListeLoups=new ArrayList<Loup>();
	}
	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public Loup getLoup(){
		return this.ListeLoups.get(0);
	}
	
	public void ajouterLoup(Loup l){
		this.ListeLoups.add(l);
	}
	
	public void retirerLoup(Loup l){
		this.ListeLoups.remove(l);
	}
	
	public void reintialiser() {
        this.terrain=new Terrain();
        this.ListeLoups=new ArrayList<Loup>();
    }
	
	public Loup verifie(int x,int y,int peri) {
		for (int i=0;i<this.ListeLoups.size();i++) {
			if(((this.ListeLoups.get(i).getX()>x-peri)&&(this.ListeLoups.get(i).getX()<x+peri))&&((this.ListeLoups.get(i).getY()>y-peri)&&(this.ListeLoups.get(i).getY()<y+peri))) {
				return this.ListeLoups.get(i);
			}
		}
		
		return null;
	}
	
	
	public int[] tableauObstacle() {
		int tailleTerrain = this.terrain.getLargeur()*this.terrain.getHauteur();
		int tab[] = new int[tailleTerrain];
		for(int i=0; i < tailleTerrain; i++) {
			int id = this.terrain.codeTuile(i);
			if( id == 121 || id == 194 || id == 362 || id == 340) {
				tab[i] = 1;
			}
			//Manque du collision de la tour
			else if(id == 160 || id == 5) {
				tab[i] = 0;
			}
		}
		return tab;
	}
	
	//Si renvoie list alors bfs.list(int[0] == arrivée && int[1] == départ)
	public ArrayList<int[]> bfs(int x, int y) { //idCible losque x + y*tailleTerrain en 1 dimension
		int largeurTerrain = this.terrain.getLargeur();
		int tailleTerrain = this.terrain.getHauteur()*largeurTerrain;
		int[] tabMarquer = new int[tailleTerrain];
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