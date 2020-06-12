package la_revenche_des_loups.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;
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
		l=new Loup_Gris(j,20,4);
		l.seFaitAttaquer(5);
		assertEquals(15, l.getPV(),"cas supérieur>0");
		l.seFaitAttaquer(50);
		assertEquals(0, l.getPV(),"cas supérieur mort");
	}
	@Test
	final void testCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup(j,20,4);
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
		l=new Loup(j,20,4);
		t1=new Tour_Paille(j,l.getX(),l.getY());
		l.cible();
		l.attaqueTour();
		assertEquals(26, t1.getPV(),"Tour dans périmetre loup");	
	}
	
	@Test
	final void testChangeCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup(j,20,4);
		t1=new Tour_Paille(j,l.getX()-2,l.getY());
		l.cible();
		t1.meurt();
		l.changeCible();
		assertEquals(null, l.getCible(),"");
	}

}
