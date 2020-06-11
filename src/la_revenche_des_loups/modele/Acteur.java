package la_revenche_des_loups.modele;



public abstract class Acteur {

	private Jeu jeu;
	private int x, y;
	private int pv;
	private int perimetre;
	private int ptsATT;
	private int taille;
	private Acteur cible;
	
	public Acteur(Jeu j, int x, int y, int pv, int perimetre, int pts) {
		this.jeu = j;
		this.x = x;
		this.y = y;
		this.pv = pv;
		this.perimetre = perimetre;
		this.ptsATT = pts;
		this.cible = null;
		this.jeu.ajouterActeur(this);
	}
	
	public Acteur(Jeu j, int x, int pv, int perimetre, int pts) {
		this.jeu = j;
		this.x = x;
		this.pv = pv;
		this.perimetre = perimetre;
		this.ptsATT = pts;
		this.cible = null;
		this.jeu.ajouterActeur(this);
	}
	
	public int getX() {
		return this.x;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getPV() {
		return this.pv;
	}
	
	public void setPV(int newPV) {
		this.pv = newPV;
	}
	
	public int getPtsATT() {
		return this.ptsATT;
	}
	
	public int getPerimetre() {
		return this.perimetre;
	}
	
	public Jeu getJeu() {
		return this.jeu;
	}
	
	public void seFaitAttaquer(int pts) {
		this.pv -= pts;
		if (!estVivant()) {
			meurt();
		}
	}
	
	public boolean estVivant() {
		return this.pv > 0;
	}

	public void meurt() {
		this.pv = 0;
		System.out.println("Acteur.meurt [ "+this+" est mort ]");
		
		//Test Affichage
		if (this instanceof Loup) {
			this.jeu.setNumeroAction(4);
			this.jeu.retirerActeur(this);
		}
		else if (this instanceof Tour) {
			this.jeu.setNumeroAction(5);
			this.jeu.retirerActeur(this);
		}
		
		
		
	}
	
	public void cible() {
		this.cible = verifie(this.x, this.y, this.perimetre);
	}
	
	public Acteur getCible() {
		return this.cible;
	}
	
	public void setCible(Acteur a) {
		this.cible = a;
	}
	
	protected void setTaille(int taille) {
		this.taille = taille;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public int obtenirIdPosition() {
		return this.x+this.y*this.getJeu().getTerrain().getLargeur();
	}
	
	public abstract void changeCible();
	
	public abstract void agit();
	
	public abstract Acteur verifie(int x, int y, int peri);
}
