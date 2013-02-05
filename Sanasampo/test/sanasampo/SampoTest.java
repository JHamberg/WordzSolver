/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo;

import java.io.IOException;
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
public class SampoTest {
    Sampo s;
    
    public SampoTest() {
        s = new Sampo();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sampoHoitaaKaiken() throws IOException {
        s.asenna();
        s.kaynnista("aina");
        assertEquals(true, s.haeTulos().contains("aina"));
    }
}
