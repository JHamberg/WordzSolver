/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.data;

import java.util.Random;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

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
    public void alustusTyhjallaTuottaaErrorin() {
        assertEquals(false, r.alusta(""));
    }
    
    @Test
    public void alustusVaaranKokoisellaTuottaaErrorin() {
        assertEquals(false, r.alusta("asdfghjk"));
    }
    
}
