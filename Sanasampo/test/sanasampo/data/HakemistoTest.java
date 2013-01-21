package sanasampo.data;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jonatan
 */
public class HakemistoTest {
    Hakemisto h;
    Sanakirja s1;
    Sanakirja s2;
    
    public HakemistoTest() throws FileNotFoundException {
         s1 = new Sanakirja();
         s2 = new Sanakirja("kissa.txt");
         h = new Hakemisto(new Sanakirja());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sanakirjaLisataanVainKerran() throws FileNotFoundException {
        Sanakirja s = new Sanakirja();
        Sanakirja s2 = new Sanakirja("sanat.txt");
        h.addSanakirja(s);
        assertEquals(true, !h.addSanakirja(s2));
    }
}
