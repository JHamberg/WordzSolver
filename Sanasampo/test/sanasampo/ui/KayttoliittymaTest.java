/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.SwingUtilities;
import org.junit.Test;
import sanasampo.Sampo;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;
import sanasampo.lang.FileEmptyException;
import sanasampo.logic.Haku;

/**
 *
 * @author Jonatan
 */
public class KayttoliittymaTest {
    Kayttoliittyma ui;
    
    public KayttoliittymaTest() throws FileNotFoundException, IOException, FileEmptyException {
       Hakemisto h = new Hakemisto(new Sanakirja());
       Ruudukko r = new Ruudukko();
       r.alusta("kissakoiratalomo");
       Sampo s = new Sampo();
       Haku haku = new Haku(h, r);
       haku.kaynnista();
       ui = new Kayttoliittyma(r, haku.getMahdolliset(), haku.getHitlist(), s);
       SwingUtilities.invokeLater(ui);
    }
    
     @Test
    public void tbi() {
        
     }
}
