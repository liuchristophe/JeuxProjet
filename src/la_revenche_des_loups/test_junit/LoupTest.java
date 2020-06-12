package la_revenche_des_loups.test_junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.modele.Tour;

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
		l=new Loup(j);
		l.seFaitAttaquer(5);
		assertEquals(25, l.getPV(),"cas supeieur>0");
		l.seFaitAttaquer(50);
		assertEquals(0, l.getPV(),"cas supeieur mort");
	}
	@Test
	final void testCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup(j);
		l.cible();
		assertEquals(null, l.getCible(),"Aucune tour dans perimetre loup");
		t1=new Tour(j,l.getX()-2,l.getY());
		l.cible();
		assertEquals(t1, l.getCible(),"Tour dans perimetre loup");	
	}
	
	@Test
	final void TestattaqueTour(){
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup(j);
		t1=new Tour(j,l.getX()-2,l.getY());
		l.cible();
		l.attaqueTour();
		assertEquals(27, t1.getPV(),"Tour dans perimetre loup");	
	}
	
	@Test
	final void testChangeCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup(j);
		t1=new Tour(j,l.getX()-2,l.getY());
		l.cible();
		t1.meurt();
		l.changeCible();
		assertEquals(null, l.getCible(),"");
	}

}
