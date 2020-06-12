package la_revenche_des_loups.controleur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import la_revenche_des_loups.modele.Bfs;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Maison;
import la_revenche_des_loups.modele.Terrain;
//import la_revenche_des_loups.vue.BFSVue;
import la_revenche_des_loups.vue.HistoriqueActionVue;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.modele.Tour_Bois;
import la_revenche_des_loups.modele.Tour_Brique;
import la_revenche_des_loups.modele.Tour_Paille;
import la_revenche_des_loups.vue.MaisonVue;
import la_revenche_des_loups.vue.TerrainVue;
import la_revenche_des_loups.vue.TourVue;

public class Controleur implements Initializable {

	private Terrain terrain;
	private Jeu jeu;
	private Maison maison;
	private Bfs bfs;
	
	private GameLoop gameloop;
	
	private TerrainVue terrainVue;
	private MaisonVue maisonVue;
	private TourVue tourVue;
//	private BFSVue bfsVue;
	@FXML
	private TilePane tilePane;
	@FXML
    private TilePane testBFS; //juste pour essaie
	@FXML
	private Pane tableDeJeu;
	
	//test affichage
	private HistoriqueActionVue historiqueVue;
	@FXML
    private Label labelVague;

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
	private Label infoActeur;
	
	@FXML
    private Label monnaieJoueur;

	private int TypeTour = 1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.terrain = new Terrain();
		
		this.jeu = new Jeu(terrain);
		this.terrainVue = new TerrainVue(this.tilePane, jeu.getTerrain());
		this.terrainVue.afficherTerrainVue(21, 21, 12);
		
		// ajout de la maison et du loup dans le jeu
		this.maison = new Maison(jeu);
		
		this.bfs = new Bfs(this.jeu);
		
		// ajout des sprites
		this.maisonVue = new MaisonVue(this.tableDeJeu, infoActeur);
		this.maisonVue.creerMaisonVue(maison);
		
		//Initialisation de l'historique d'action
		this.historiqueVue = new HistoriqueActionVue(jeu, labelAction1, labelAction2, labelAction3, labelAction4, labelAction5);
		
		// initialisation de la gameloop
		this.gameloop = new GameLoop(this.jeu, this.tableDeJeu, this.historiqueVue);
		
		/*
		try {
			this.tourBrique = new Image(new FileInputStream("src/la_revenche_des_loups/ressources/tour_brique.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.imageTourBrique = new ImageView(tourBrique);
		*/
		//this.monnaieJoueur.textProperty().bind(this.jeu.getMonnaie());
	}

	@FXML
	void vagueSuivante(ActionEvent event) {
		//this.historiqueVue.reinitialiserHistorique();
		this.gameloop.lancerVague();
		this.labelVague.setText("Vague numéro "+this.gameloop.getNumVague());
	}
	
	@FXML
	void lancerPartie(ActionEvent event) {
		this.labelVague.setText("Vague numéro "+this.gameloop.getNumVague());
		this.gameloop.lancerAnimation();
	}

	@FXML
	void quitterJeu(ActionEvent event) {
		this.gameloop.pause();
		//this.jeu.reintialiser();
		//System.out.println(this.jeu.bfs(99, 25, 2555));
	}

	@FXML
    public void cliqueTableDeJeu(MouseEvent click) {
        int x = ((int) click.getX()) / 12 - 1;
        int y = ((int) click.getY()) / 12 - 1;
        if(!this.bfs.verifieObstacle(x, y)) {
            if(this.jeu.limiterTours()) {
                if(!this.jeu.verifieTourAlentour(x, y, 5)) {
                    Tour tour;
                    if(TypeTour==1) {
                        tour = new Tour_Paille(this.jeu, x, y);
                    }
                    else if(TypeTour==2) {
                        tour = new Tour_Bois(this.jeu, x, y);
                    }
                    else {
                        tour = new Tour_Brique(this.jeu, x, y);
                    }
                    this.jeu.ajouterTour(tour);
                    this.tourVue = new TourVue(this.tableDeJeu, tour);
                    this.tourVue.afficherTourVue(tour);
                    this.bfs.ajoutObstacleTour(x, y);
                    //this.testBFS.getChildren().clear();
                    //this.bfsVue.afficherBFSVue(2, 1, 12);
                    System.out.println("Controleur.cliqueTableDeJeu [ ajout d un tour ]");
                    System.out.println("Controleur.cliqueTableDeJeu [ tour " + this.jeu.getNombreTours() + "/" + this.jeu.getLimiteTours() + " ]");
                }
                else {
                    System.out.println("Controleur.cliqueTableDeJeu [ tour dans le rayon de 5 tuile ]");
                }
            }
            else {
                System.out.println("Controleur.cliqueTableDeJeu [ fin tour " + this.jeu.getNombreTours() + "/" + this.jeu.getLimiteTours() + " ]");
            }
        }
    }
	
	  @FXML
	  void typeBrique(MouseEvent event) {
		  System.out.println("fonction tour brique");
		  this.TypeTour = 3;
	  }
	
	  @FXML
	  void typeBois(MouseEvent event) {
		  this.TypeTour = 2;
	  }
	  
	  @FXML
	  void typePaille(MouseEvent event) {
		  this.TypeTour = 1;
	  } 
	
//	//Test de bfs
//	@FXML
//	public void cliqueTableDeJeu(MouseEvent click) {
//		int x = ((int) click.getX()) / 12;
//		int y = ((int) click.getY()) / 12;
//		System.out.println("Controleur.cliqueTableDeJeu"+this.bfs.cheminBfs(x, y, 1615));
//		System.out.println("Controleur.cliqueTableDeJeu [ x:" + x + " y:" + y + " ]");
//		this.testBFS.getChildren().clear();
//		this.bfsVue.afficherCheminBFS(this.bfs.cheminBfs(x, y, 1615));
//	}
	
}
