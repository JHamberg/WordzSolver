package sanasampo.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;
import sanasampo.lang.FileEmptyException;

public class HakuTest {
    Ruudukko r;
    Hakemisto h;
    String ruudukko;
    Haku haku;
    
    ArrayList<String> tulokset;
    
    public HakuTest() throws FileNotFoundException, IOException, FileEmptyException {
        r = new Ruudukko();
        h = new Hakemisto(new Sanakirja());
        ruudukko = "xxxx"
                 + "talo"
                 + "xxxx"
                 + "xxxx";
         r.alusta(ruudukko);
        haku = new Haku(h, r);
        haku.kaynnista();
        tulokset = haku.getTulos();
    }

    @Test
    public void hakuLoytaaOikeatSanat() {
        assertEquals(true, haku.getTulos().contains("talo"));
    }
    
    
}
