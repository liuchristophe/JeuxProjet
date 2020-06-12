package la_revenche_des_loups.modele;

public class Tour_Paille extends Tour{

	public Tour_Paille(Jeu jeu, int x, int y) {
		super(jeu,x,y,30,5,5,50);
	}
	
	public void AttaqueSpé() {
		for (int i=0;i<this.getJeu().getListeLoups().size();i++) {
			if ((this.getJeu().getListeLoups().get(i).getY() > this.getY() - this.getPerimetre() && this.getJeu().getListeLoups().get(i).getY() < this.getY() + this.getPerimetre())
					&& (this.getJeu().getListeLoups().get(i).getX() > this.getX() - this.getPerimetre() && this.getJeu().getListeLoups().get(i).getX() < this.getX() + this.getPerimetre())){
						
				//this.getJeu().getListeLoups().get(i).setVitesse((this.getJeu().getListeLoups().get(i).getVitesse())/2);
			}
		}
	}

}
