package la_revenche_des_loups.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.exception.MonException;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Maison;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.vue.HistoriqueActionVue;
import la_revenche_des_loups.vue.MaisonVue;
import la_revenche_des_loups.vue.TerrainVue;

public class Controleur implements Initializable {

	private Terrain terrain;
	private Jeu jeu;
	private Maison maison;

	private GameLoop gameloop;
	private ActionControleur actionControleur;

	private TerrainVue terrainVue;
	private MaisonVue maisonVue;

	@FXML
	private TilePane tilePane;
	@FXML
	private Pane tableDeJeu;

	@FXML
	private Label labelVague;

	private HistoriqueActionVue historiqueVue;
	@FXML
	private Label labelAction1;
	@FXML
	private Label labelAction2;
	@FXML
	private Label labelAction3;
	@FXML
	private Label labelAction4;
	@FXML
	private Label labelAction5;

	@FXML
	private Label monnaieJoueur;
	@FXML
	private Label pvMaison;

	@FXML
	private Label labelNbMaxTour;
	@FXML
    private Label labelTourSelection;
	private int TypeTour = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain = new Terrain();
		this.jeu = new Jeu(terrain);
		this.terrainVue = new TerrainVue(this.tilePane, jeu.getTerrain());
		this.terrainVue.afficherTerrainVue(21, 21, 12);

		// ajout de la maison dans le jeu
		this.maison = new Maison(jeu);
		// ajout des sprites de la maison
		this.maisonVue = new MaisonVue(this.tableDeJeu);
		this.maisonVue.creerMaisonVue(maison);
		this.pvMaison.textProperty().bind(this.maison.getPvMaison().asString());

		// Initialisation de l'historique d'action
		this.historiqueVue = new HistoriqueActionVue(jeu, labelAction1, labelAction2, labelAction3, labelAction4, labelAction5);

		// initialisation de la gameloop
		try {
			this.gameloop = new GameLoop(this.jeu, this.tableDeJeu, this.historiqueVue);
		} catch (MonException e) {
			e.printStackTrace();
		}

		// affichage de la monnaie du joueur
		this.monnaieJoueur.textProperty().bind(this.jeu.getMonnaieProperty().asString());
		
		// affichage du nombre de tour du joueur
		this.labelNbMaxTour.textProperty().bind(this.jeu.getNombreToursProperty().asString());

		this.actionControleur = new ActionControleur(this.jeu);
	}

	@FXML
	void vagueSuivante(ActionEvent event) {
		if (!this.jeu.getPartiEstLance()) {
			this.gameloop.lancerVague();
			this.labelVague.setText("Vague numéro " + this.gameloop.getNumVague()+"/5");
		}
	}

	@FXML
	void lancerPartie(ActionEvent event) {
		if (this.gameloop.getNumVague() == 1 && !this.jeu.getPartiEstLance()) {
			this.labelVague.setText("Vague numéro " + this.gameloop.getNumVague()+"/5");
			this.gameloop.lancerAnimation();
		}
	}

	@FXML
	public void cliqueTableDeJeu(MouseEvent click) {
		int x = ((int) click.getX()) / 12 - 1;
		int y = ((int) click.getY()) / 12 - 1;
		actionControleur.ajouteTourDansJeu(x, y, this.TypeTour, this.tableDeJeu);
		actionControleur.tourSelection(this.TypeTour, this.labelTourSelection);
	}

	@FXML
	void typeBrique(MouseEvent event) {
		this.TypeTour = 3;
		actionControleur.tourSelection(this.TypeTour, this.labelTourSelection);
		
	}

	@FXML
	void typeBois(MouseEvent event) {
		this.TypeTour = 2;
		actionControleur.tourSelection(this.TypeTour, this.labelTourSelection);
	}

	@FXML
	void typePaille(MouseEvent event) {
		this.TypeTour = 1;
		actionControleur.tourSelection(this.TypeTour, this.labelTourSelection);
	}
	
}
