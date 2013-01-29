package sanasampo.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class HakemistoTest {
    Hakemisto h;
    Sanakirja s1;
    Sanakirja s2;
    
    public HakemistoTest() throws FileNotFoundException, IOException {
         s1 = new Sanakirja();
         s2 = new Sanakirja("kissa.txt");
         h = new Hakemisto(new Sanakirja());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sanakirjaLisataanVainKerran() throws FileNotFoundException, IOException {
        Sanakirja s = new Sanakirja();
        Sanakirja s3 = new Sanakirja("sanat.txt");
        h.addSanakirja(s);
        assertEquals(true, !h.addSanakirja(s3));
    }
}
