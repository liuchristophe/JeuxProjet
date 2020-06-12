package la_revenche_des_loups.test_junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import la_revenche_des_loups.modele.Jeu;
import la_revenche_des_loups.modele.Loup;
import la_revenche_des_loups.modele.Terrain;
import la_revenche_des_loups.modele.Tour;

class TourTest {
Terrain t;
Jeu j;

	@Test
	void testCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		t1=new Tour(j);	
		t1.cible();
		assertEquals(null, t1.getCible(),"Aucune tour dans périmetre loup");
		l=new Loup(j);
		t1.setY(l.getY()+2);
		t1.cible();
		assertEquals(l, t1.getCible(),"Tour dans périmetre loup");	
	}
	
	@Test
	final void TestseDefend(){
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		l=new Loup(j);
		t1=new Tour(j,l.getX()-2,l.getY());
		t1.cible();
		t1.seDefend();
		assertEquals(25, l.getPV(),"Tour dans périmetre loup");	
	}
}
