package la_revenche_des_loups.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tir {

	private String idTir;
	private IntegerProperty positionX;
	private IntegerProperty positionY;
	private static int num = 0;

	public Tir(String id, int xCible, int yCible) {
		this.idTir = "Tir" + num + id;
		this.positionX = new SimpleIntegerProperty((xCible+1) * 12);
		this.positionY = new SimpleIntegerProperty((yCible-1) * 12);
		this.num++;
	}

	public final IntegerProperty getPositionX() {
		return this.positionX;
	}

	public final IntegerProperty getPositionY() {
		return this.positionY;
	}

	public String getIdTir() {
		return this.idTir;
	}
}