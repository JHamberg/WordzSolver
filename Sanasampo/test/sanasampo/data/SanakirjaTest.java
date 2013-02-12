package sanasampo.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import sanasampo.lang.FileEmptyException;

public class SanakirjaTest {
    static final String testDir = "test\\sanasampo\\files\\";
    static final String fileName = "sanakirjaTest.txt";
    Sanakirja s;
    File f;
    Writer output;

    public SanakirjaTest() throws FileNotFoundException, IOException, FileEmptyException {
        s = new Sanakirja();
        f = new File(testDir + fileName); //ISSUE temp is not removed! 
        f.createNewFile();
        output = new BufferedWriter(new FileWriter(f));
    }

    @After
    public void tearDown() {
        f.delete(); //Tuhotaan TEMP tiedosto testin jalkeen
    }
    
    @Test (expected = FileNotFoundException.class)
    public void olematonSanakirjaPalauttaaErrorin() throws FileNotFoundException, IOException, FileEmptyException{
        Sanakirja s1 = new Sanakirja();
        s1.alusta("f8d4c5d3c5809257f483c97a36db8d57.txt");
    }
    
    @Test
    public void ensimmainenSanaOikea() throws IOException {
        assertEquals("voi", s.getSana(0));
    }
    
    @Test
    public void getTiedostoPolkuToimii() throws IOException, FileNotFoundException, UnsupportedEncodingException, FileEmptyException {
        output.write("abc123");
        output.close();
        Sanakirja s1 = new Sanakirja();
        s1.alusta(testDir+fileName);
        assertEquals("test\\sanasampo\\files\\sanakirjaTest.txt", s1.getTiedostoPolku());
    }

    @Test
    public void sanakirjassaSanoja() throws FileNotFoundException {
        assertFalse(s.getSana(0).isEmpty());
    }

    @Test
    public void alustusIlmanParametriaToimii() throws FileNotFoundException, IOException, FileEmptyException{
        assertTrue(!(new Sanakirja().getTiedostoPolku().isEmpty()));
    }

    @Test
    public void yksiSanaisenKirjanKoko() throws IOException, FileEmptyException {
        output.write("abc123");
        output.close();
        Sanakirja s1 = new Sanakirja();
        s1.alusta(testDir+fileName);
        assertEquals(1, s1.getKoko());
    }
    
    
    @Test
    public void kaksiSanaisenKirjanKoko() throws IOException, FileEmptyException {
        output.write("abc123");
        output.write(System.getProperty("line.separator") + "321cba");
        output.close();
        Sanakirja s1 = new Sanakirja();
        s1.alusta(testDir+fileName);
        assertEquals(2, s1.getKoko());
    }
}
