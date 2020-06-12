package la_revenche_des_loups.modele.ClasseTour;

import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;

public class Tour_Paille extends Tour{
	
	public Tour_Paille(Jeu jeu, int x, int y) {
		super(jeu,x,y,300,5,20,200);
	}
	
	public static int getPrix(){
		return 200;
	}

}
