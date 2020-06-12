package la_revenche_des_loups.modele.ClasseTour;

import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Tour;

public class Tour_Bois extends Tour{
	
	public Tour_Bois(Jeu jeu, int x, int y) {
		super(jeu,x,y,700,5,35,450);
	}
	
	public static int getPrix() {
		return 125;
	}
	
}
