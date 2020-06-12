package la_revenche_des_loups.modele;

public class Tour_Bois extends Tour{
	
	public Tour_Bois(Jeu jeu, int x, int y) {
		super(jeu,x,y,50,9,9,125);
	}
	
	public static int getPrix() {
		return 125;
	}
	
//	public void AttaqueSpe() {
//		if(this.getCible()!=null) {
//			this.getCible().setX((this.getCible().getX()+5));
//			this.getCible().seFaitAttaquer(5);
//			((Loup) this.getCible()).remarche();
//			}
//	}
	
}
