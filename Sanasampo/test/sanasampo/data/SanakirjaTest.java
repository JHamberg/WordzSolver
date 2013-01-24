package sanasampo.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

// @author JHamberg

public class SanakirjaTest {

    Sanakirja s;
    File f;
    Writer output;

    public SanakirjaTest() throws FileNotFoundException, IOException {
        s = new Sanakirja();
        f = new File("temp.txt"); //ISSUE temp is not removed! 
        output = new BufferedWriter(new FileWriter(f));
    }

    
    @After
    public void tearDown() {
        System.out.println("kutsuttu kerran");
        f.delete(); //Tuhotaan TEMP tiedosto testin jalkeen
    }
    
    @Test
    public void ensimmainenSanaOikea() throws IOException {
        assertEquals("voi", s.getSana(0));
    }

    @Test
    public void sanakirjassaSanoja() throws FileNotFoundException {
        assertEquals(false, s.getSana(0).isEmpty());
    }

    @Test
    public void alustusIlmanParametriaToimii() throws FileNotFoundException, IOException{
        assertEquals(true, !(new Sanakirja().getPolku().isEmpty()));
    }

    @Test
    public void olematonSanakirjaPalauttaaErrorin() throws FileNotFoundException, IOException{
        assertEquals(false, !(new Sanakirja("f8d4c5d3c5809257f483c97a36db8d57.txt").getErrorState()));
    }

    @Test
    public void yksiSanaisenKirjanKoko() throws IOException {
        output.write("abc123");
        output.close();
        assertEquals(1, new Sanakirja("temp.txt").getKoko());
    }
    
    
    @Test
    public void kaksiSanaisenKirjanKoko() throws IOException {
        output.write("abc123");
        output.write(System.getProperty("line.separator") + "321cba");
        output.close();
        assertEquals(2, new Sanakirja("temp.txt").getKoko());
    }
}
