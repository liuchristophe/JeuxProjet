package la_revenche_des_loups.vue;

import javafx.scene.control.Label;
import la_revenche_des_loups.modele.HistoriqueAction;
import la_revenche_des_loups.modele.Jeu;

public class HistoriqueActionVue {

	private Label l1;
	private Label l2;
	private Label l3;
	private Label l4;
	private Label l5;
	private HistoriqueAction historique;

	public HistoriqueActionVue(Jeu jeu, Label l1, Label l2, Label l3, Label l4, Label l5) {
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.l4 = l4;
		this.l5 = l5;
		this.historique = new HistoriqueAction(jeu);
		jeu.initHistorique(this.historique);
	}

	public void affichageHistorique() {
		this.l1.setText(this.historique.getListeDefile().get(0));
		this.l2.setText(this.historique.getListeDefile().get(1));
		this.l3.setText(this.historique.getListeDefile().get(2));
		this.l4.setText(this.historique.getListeDefile().get(3));
		this.l5.setText(this.historique.getListeDefile().get(4));
	}

	public void affichageFinParti() {
		this.l1.setText("*******************");
		this.l2.setText("*******************");
		this.l3.setText(" FIN DE LA VAGUE ");
		this.l4.setText("*******************");
		this.l5.setText("*******************");
	}

	/*
	public void reinitialiserHistorique() {
		this.l1.setText("");
		this.l2.setText("");
		this.l3.setText("");
		this.l4.setText("");
		this.l5.setText("");
		this.historique.reinitialiserListeDefile();
	}
	 */
}
