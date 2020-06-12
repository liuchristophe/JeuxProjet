package la_revenche_des_loups.modele.ClasseLoup;

import la_revenche_des_loups.modele.Acteur;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;

public class Loup_Gris extends Loup{
	
	public Loup_Gris(Jeu j, int pv, int perimetre, int pts) {
		super(j,  pv, perimetre, pts);
	}

	public Loup_Gris(Jeu j) {
		this(j,  250, 4, 15);
	}

	public void tracerChemin(Acteur cible) {
		int idCible;
		if(cible == null && super.cibleTour) {
			idCible = ((int)(Math.random()*25 + 0)+13)*this.getJeu().getTerrain().getLargeur()+15-103;
			super.chemin = super.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
			super.cibleTour = false;
		}
		else if(cible != null){
			idCible = cible.obtenirIdPosition();
			super.chemin = super.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
			super.cibleTour = true;
		}
	}
	
}
