package la_revenche_des_loups.modele;

import java.util.ArrayList;

public class Jeu {
	private Terrain terrain;
	private ArrayList<Loup>ListeLoups; 
	


	public Jeu(Terrain t){
		this.terrain=t;
		this.ListeLoups=new ArrayList<Loup>();
	}
	
	public void ajouterLoup(Loup l){
		this.ListeLoups.add(l);
	}
	
	public void retirerLoup(Loup l){
		this.ListeLoups.remove(l);
	}
	
	public Loup verifie(int x,int y,int peri) {
		for (int i=0;i<this.ListeLoups.size();i++) {
			if(((this.ListeLoups.get(i).getX()>x-peri)&&(this.ListeLoups.get(i).getX()<x+peri))&&((this.ListeLoups.get(i).getY()>y-peri)&&(this.ListeLoups.get(i).getY()<y+peri))) {
				return this.ListeLoups.get(i);
			}
		}
		
		return null;
	}
	
	
	
	
	

}