package sanasampo.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import sanasampo.lang.FileEmptyException;

public class HakemistoTest {
    Hakemisto h;
    Sanakirja s1;
    Sanakirja s2;
    
    public HakemistoTest() throws FileNotFoundException, IOException, FileEmptyException {
         s1 = new Sanakirja();
         h = new Hakemisto(new Sanakirja());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sanakirjaLisataanVainKerran() throws FileNotFoundException, IOException, FileEmptyException {
        Sanakirja s = new Sanakirja();
        Sanakirja s3 = new Sanakirja();
        h.addSanakirja(s);
        assertEquals(true, !h.addSanakirja(s3));
    }
}
