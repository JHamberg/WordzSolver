package sanasampo.logic;

import static org.junit.Assert.*;
import org.junit.Test;
import sanasampo.data.Ruudukko;

/**
 *
 * @author Jonatan
 */
public class EsitarkastusTest {
    Esitarkastus e;
    Ruudukko r;
    
    public EsitarkastusTest() {
        r = new Ruudukko();
        r.alusta("kisa");
        e = new Esitarkastus(r);
    }
    
    @Test
    public void eiHyvaksySamaaKirjaintaUudestaan() {
        assertEquals(false, e.suorita("kasa"));
    }
    
    @Test
    public void loytaaSanoja() {
        assertEquals(true, (e.suorita("kisa")&&
                            e.suorita("aski")&&
                            e.suorita("sika")&&
                            e.suorita("kasi")));
    }
}
