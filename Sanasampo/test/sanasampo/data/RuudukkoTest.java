/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.data;

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
     
    public RuudukkoTest() {
       r =  new Ruudukko();
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
