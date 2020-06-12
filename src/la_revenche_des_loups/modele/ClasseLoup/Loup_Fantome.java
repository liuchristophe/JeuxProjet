package la_revenche_des_loups.modele.ClasseLoup;

import la_revenche_des_loups.modele.Acteur;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;

public class Loup_Fantome extends Loup{
	
	public Loup_Fantome(Jeu j,  int pv, int perimetre, int pts) {
		super(j,  pv, perimetre, pts);
	}
	
	public Loup_Fantome(Jeu j) {
		this(j,  700, 1, 25);
	}
	
    public void tracerChemin(Acteur cible) {
        int idCible;
        if(super.chemin.size() == 0 || super.chemin == null) {
            idCible = ((int)(Math.random()*25 + 0)+13)+super.getJeu().getTerrain().getLargeur()+15;
            super.chemin = super.bfs.cheminBfs(this.obtenirIdPosition(), idCible);
        }
    }
	
}
