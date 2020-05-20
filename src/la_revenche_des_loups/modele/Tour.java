package la_revenche_des_loups.modele;

public class Tour {
	private int x;
	private int y;
	private int pv;
	private int perimetre;
	private int degat;
	private Jeu jeu;
	private Loup loupCible;
	private String id;
	private static int num = 0;

	public Tour(Jeu jeu, int pv, int degat, int perimetre, int x, int y) {
		this.jeu = jeu;
		this.pv = pv;
		this.degat = degat;
		this.perimetre = perimetre;
		this.x = x;
		this.y = y;
		this.id = "T"+num;
		num++;
	}

	public Tour(Jeu jeu, int x, int y) {
		this(jeu, 10,2,10,x,y);
	}
	
	public Tour(Jeu jeu) {
		this(jeu, 40, 26);
	}

	public void seDefend() {
		if (this.loupCible.estVivant()) {
			this.loupCible.decrementerPV(this.degat);
		}
	}

	// La tour cible le loup jusqu'a ce qu'il quitte le perimetre de la tour ou est
	// mort
	public void Loupcible() {
		this.loupCible=this.jeu.verifie(this.x,this.y,this.perimetre);	
	}
	
	public void ChangeCible() {
		if((this.loupCible.estVivant()==false)||(this.loupCible.getX()<this.x-this.perimetre)||(this.loupCible.getY()<this.y-this.perimetre)||(this.loupCible.getY()<this.y+this.perimetre)) {
			this.Loupcible();
		}
	}
	
	public String getId() {
		return this.id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPv() {
		return pv;
	}

	public int getPerimetre() {
		return perimetre;
	}

	public int getDegat() {
		return degat;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public void setPerimetre(int perimetre) {
		this.perimetre = perimetre;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}
}
