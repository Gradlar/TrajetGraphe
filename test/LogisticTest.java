package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import src.Direction;
import src.Ville;
import src.Logistic;

import org.junit.jupiter.api.BeforeEach;

public class LogisticTest {
    // public final String data[] = new String[]{
    //     "villeA;villeB;Train;60;1.7;80",
    //     "villeB;villeD;Train;22;2.4;40",
    //     "villeA;villeC;Train;42;1.4;50",
    //     "villeB;villeC;Train;14;1.4;60",
    //     "villeC;villeD;Avion;110;150;22",
    //     "villeC;villeD;Train;65;1.2;90"
    //     };
    public Logistic l = new Logistic();
    public Ville a, b, c, d, e;
    public ArrayList<Ville> v1, v2, v3;
    public ArrayList<Direction> d1, d2;
    public ArrayList<String[]> data1, data2;


    @BeforeEach
	public void initTest() {
        l = new Logistic();
        a = new Ville("Dijon");
        b = new Ville("Lille");
        c = new Ville("Lyon");
        d = new Ville("Clermont-Ferrand");
        e = new Ville("villeE");
        
        v1 = new ArrayList<Ville>();
        v1.add(a);
        v1.add(b);
        v1.add(d);
        v1.add(c);

        v2 = new ArrayList<Ville>();
        v2.add(b);
        v2.add(a);
        v2.add(c);
        v2.add(d);
        
        v3 = new ArrayList<Ville>();
        v3.add(a);
        v3.add(b);
        v3.add(c);
        v3.add(e);
        
        d2 = new ArrayList<Direction>();
        data1 = new ArrayList<String[]>();
    }

    @Test
	public void testCreateVilles() {
        l.csvFileLogistic("./data.csv");
        l.createVilles();
        assertEquals(l.getVilles(), v2);
        assertFalse(l.getVilles().equals(v1));
        assertFalse(l.getVilles().equals(v3));

        assertTrue(l.getVilles().contains(a));
        assertTrue(l.getVilles().contains(b));
        assertTrue(l.getVilles().contains(c));
        assertTrue(l.getVilles().contains(d));
        assertFalse(l.getVilles().contains(e));
    }

    @Test
    public void testCreateDirection() {
        l.csvFileLogistic("./data.csv");
        l.createDirection();
        assertNotNull(l.getDirections()); 
    }

    @Test
    public void testGetData() {
        l.csvFileLogistic("./data.csv");
        assertNotNull(l.getData()); 
    }

    @Test
    public void testGetCorrespondance() {
        l.csvFileLogistic("./data.csv");
        l.csvFileLogistic("./dataCorrespondance.csv");
        l.createVilles();
        l.createDirection();
        l.correspondance();
        assertNotNull(l.getCorrespondance());
    }

}
