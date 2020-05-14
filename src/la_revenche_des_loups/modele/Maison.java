package la_revenche_des_loups.modele;

public class Maison {

	private Terrain map;
	private int x, y;
	private int pv;
	private int ptsATT;
	private int perimetreX;
	private int perimetreY;

	public Maison() {
		this.x = 0;
		this.y = 0;
		this.pv = 10;
		this.ptsATT = 2;
		this.perimetreX = 3;
		this.perimetreY = 3;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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
		for (int i = 0; i < this.map.getLoups().size(); i++) {
			if ((this.map.getLoups().get(i).getX() <= this.perimetreX)
					&& (this.map.getLoups().get(i).getY() <= this.y - this.perimetreY
							|| this.map.getLoups().get(i).getY() >= this.y - this.perimetreY)) {
				this.map.getLoups().get(i).decrementerPV(this.ptsATT);
			}
		}
	}

	public String toString() {
		return "Position : (" + this.x + ", " + this.y + "), PV : " + this.pv;
	}

}
