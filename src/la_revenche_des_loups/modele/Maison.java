package la_revenche_des_loups.modele;

public class Maison {
	
	private Jeu jeu;
	private Terrain map;
	private int x, yInf,ySup;
	private int pv;
	private int ptsATT;
	private int perimetreX;
	private int perimetreY;

	public Maison(Jeu j) {
		this.jeu=j;
		this.x = 14;
		this.yInf=11;
		this.ySup = 38;
		this.pv = 10;
		this.ptsATT = 2;
		this.perimetreX = 3;
		this.perimetreY = 3;
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

	public int getPerimetreX() {
		return this.perimetreX;
	}

	public int getPerimetreY() {
		return this.perimetreY;
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
			
				if(this.jeu.Verifie(this.x,this.ySup,15)!=null) {
					this.jeu.Verifie(this.x,this.ySup,15).decrementerPV(this.ptsATT);
				}
				else if(this.jeu.Verifie(this.x,this.yInf,15)!=null) {
					this.jeu.Verifie(x,yInf,15).decrementerPV(this.ptsATT);
				}
					
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.yInf + "), PV : " + this.pv;
	}

}
