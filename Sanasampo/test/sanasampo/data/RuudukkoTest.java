/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.data;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonatan
 */
public class RuudukkoTest {
    Ruudukko r;
    Random rand;
     
    public RuudukkoTest() {
       r =  new Ruudukko();
       rand = new Random();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void containsCharPalauttaaOikein() {
        String c = "abcdefghi";
        r.alusta(c);
        int n = rand.nextInt(9); //Random to be certain 
        assertEquals(true, r.containsChar(c.charAt(n)));
    }
    
    @Test
    public void alustusTyhjallaTuottaaErrorin() {
        assertEquals(false, r.alusta(""));
    }
    
    @Test
    public void alustusVaaranKokoisellaTuottaaErrorin() {
        assertEquals(false, r.alusta("asdfghjk"));
    }
    
}
