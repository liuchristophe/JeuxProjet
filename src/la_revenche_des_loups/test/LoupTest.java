package la_revenche_des_loups.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import la_revenche_des_loups.modele.HistoriqueAction;
import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.modele.Tour;
import la_revenche_des_loups.modele.ClasseLoup.Loup_Gris;
import la_revenche_des_loups.modele.ClasseTour.Tour_Paille;

class LoupTest {
	Terrain t;
	Jeu j;
	
	@Test
	final void testseFaitAttaquer() {
		Terrain t;
		Jeu j;
		Loup l;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup_Gris(j);
		l.seFaitAttaquer(5);
		assertEquals(245, l.getPV(),"cas supérieur>0");
		l.seFaitAttaquer(50);
		assertEquals(195, l.getPV(),"cas supérieur mort");
	}
	
	@Test
	final void testCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup_Gris(j);
		l.cible();
		assertEquals(null, l.getCible(),"Aucune tour dans périmetre loup");
		t1=new Tour_Paille(j,l.getX()-2,l.getY());
		l.cible();
		assertEquals(t1, l.getCible(),"Tour dans périmetre loup");	
	}
	
	@Test
	final void TestattaqueTour(){
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup_Gris(j);
		t1=new Tour_Paille(j,l.getX(),l.getY());
		this.j.initHistorique(new HistoriqueAction(j));
		l.cible();
		l.attaqueTour();
		assertEquals(285, t1.getPV(),"Tour dans périmetre loup");	
	}
	
	@Test
	final void testChangeCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup_Gris(j);
		t1=new Tour_Paille(j,l.getX()-2,l.getY());
		this.j.initHistorique(new HistoriqueAction(j));
		l.cible();
		t1.meurt();
		l.changeCible();
		assertEquals(null, l.getCible(),"");
	}

}
