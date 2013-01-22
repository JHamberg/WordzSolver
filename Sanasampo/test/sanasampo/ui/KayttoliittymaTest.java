/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.ui;

import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sanasampo.data.Ruudukko;

/**
 *
 * @author Jonatan
 */
public class KayttoliittymaTest {
    Kayttoliittyma ui;
    
    public KayttoliittymaTest() {
       Ruudukko r = new Ruudukko();
       r.alusta("abcdefghi");
       ui= new Kayttoliittyma(r);
       SwingUtilities.invokeLater(ui);
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
    public void tbi() {}
}
