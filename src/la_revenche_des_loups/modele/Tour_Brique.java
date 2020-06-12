package la_revenche_des_loups.modele;

public class Tour_Brique extends Tour{
	
	public Tour_Brique(Jeu jeu, int x, int y) {
		super(jeu,x,y,30,15,15,250);	
	}
	
	public void AttaqueSpé() {
		if(this.nombreEnnemis()>=2) {
			for (int i=0;i<this.getJeu().getListeLoups().size();i++) {
				if ((this.getJeu().getListeLoups().get(i).getY() > this.getY() - this.getPerimetre() && this.getJeu().getListeLoups().get(i).getY() < this.getY() + this.getPerimetre())
						&& (this.getJeu().getListeLoups().get(i).getX() > this.getX() - this.getPerimetre() && this.getJeu().getListeLoups().get(i).getX() < this.getX() + this.getPerimetre())){
						
					this.getJeu().getListeLoups().get(i).seFaitAttaquer(10);
				}
			}		
		}
	}


}