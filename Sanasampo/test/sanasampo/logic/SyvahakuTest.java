package sanasampo.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;

public class SyvahakuTest {
    Ruudukko r;
    Haku h;
    Hakemisto kirjasto;
    
    public SyvahakuTest() throws FileNotFoundException, IOException {
        kirjasto = new Hakemisto(new Sanakirja());
        r = new Ruudukko();
        r.alusta("kissakoiratalomo");
        h = new Haku(kirjasto, r);
        h.kaynnista();
    }
    
    @Test
    public void loytaaLyhyimman() {
       assertEquals("aro", h.getTulos().get(0));
    }
    
    @Test
    public void loytaaPisimman() {
       assertEquals("lomakoti", h.getTulos().get(h.getTulos().size()-1));
    }
    
    @Test
    public void loytaaVainSivuttainLinkittyneen() {
       assertEquals(true, h.getTulos().contains("lama"));
    }
    
    @Test
    public void mahdollisetEiOleSamaKuinOsumat() { //Tässä tapauksessa 
       assertEquals(false, h.getTulos().equals(h.getMahdolliset()));
    }
    
}
