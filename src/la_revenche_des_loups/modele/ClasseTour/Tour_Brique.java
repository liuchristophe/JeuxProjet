package la_revenche_des_loups.modele.ClasseTour;

import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;

public class Tour_Brique extends Tour{
	
	public Tour_Brique(Jeu jeu, int x, int y) {
		super(jeu,x,y,1500,5,50,1000);	
	}
	
	public static int getPrix() {
		return 250;
	}


}