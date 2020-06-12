package la_revenche_des_loups.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TourTest {
Terrain t;
Jeu j;

	@Test
	void testCible() {
		Loup l;
		Tour t1;
		t=new Terrain();
		j=new Jeu(t);
		t1=new Tour_Paille(j,99,10);	
		t1.cible();
		assertEquals(null, t1.getCible(),"Aucune tour dans périmetre loup");
		l=new Loup(j,20,4);
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
		l=new Loup(j,20,4);
		t1=new Tour_Paille(j,l.getX()-2,l.getY());
		t1.cible();
		t1.seDefend();
		assertEquals(25, l.getPV(),"Tour dans périmetre loup");	
	}
}
