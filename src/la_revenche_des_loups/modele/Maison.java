package la_revenche_des_loups.modele;

public class Maison {
	
	private Jeu jeu;
	private Terrain map;
	private int x, yInf,ySup;
	private int pv;
	private int ptsATT;
	private int perimetre;


	public Maison(Jeu j) {
		this.jeu=j;
		this.x = 15;
		this.yInf=11;
		this.ySup = 38;
		this.pv = 10;
		this.ptsATT = 2;
		this.perimetre = 3;
	}

	public int getX() {
		return this.x;
	}

	public int getYSup() {
		return this.ySup;
	}
	
	public int getYInf() {
		return this.yInf;
	}

	public int getPV() {
		return this.pv;
	}

	public int getPtsATT() {
		return this.ptsATT;
	}

	public int getPerimetre() {
		return this.perimetre;
	}


	public Terrain getMap() {
		return this.map;
	}

	public void decrementerPV(int pts) {
		this.pv -= pts;
	}

	public boolean estDetruite() {
		return this.pv > 0;
	}

	public void seDetruit() {
		this.pv = 0;
	}

	// A VERIFIER
	public void seDefend() {
			
				if(this.jeu.verifie(this.x,this.ySup,this.perimetre)!=null) {
					this.jeu.verifie(this.x,this.ySup,15).decrementerPV(this.ptsATT);
				}
				else if(this.jeu.verifie(this.x,this.yInf,this.perimetre)!=null) {
					this.jeu.verifie(x,yInf,15).decrementerPV(this.ptsATT);
				}
					
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.yInf + "), PV : " + this.pv;
	}

}
