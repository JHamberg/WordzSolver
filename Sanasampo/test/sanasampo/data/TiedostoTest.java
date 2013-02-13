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

public class TiedostoTest {

    static final String testDir = "test/sanasampo/files/";
    static final String fileName = "tiedostoTest.txt";
    File f;
    Tiedosto tiedosto;
    Writer output;

    public TiedostoTest() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        f = new File(testDir + fileName);
        f.createNewFile();
        tiedosto = new Tiedosto(testDir + fileName);
        output = new BufferedWriter(new FileWriter(f));
    }

    @After
    public void tearDown() {
        f.delete();
    }

    @Test
    public void josEiTiedostoaLuodaanSellainen() {
        assertTrue(tiedosto.onOlemassa());
    }

    @Test
    public void tukeeSkandinaavisiaKirjaimia() throws IOException, FileEmptyException {
        output.write("lyödä åkea");
        output.close();
        assertTrue((tiedosto.lueListaan().get(0).contains("ä")
                         && tiedosto.lueListaan().get(0).contains("ö")
                         && tiedosto.lueListaan().get(0).contains("å")));
    }

    @Test (expected = FileEmptyException.class)
    public void tyhjaTiedostoHeittaaPoikkeuksen() throws FileNotFoundException, UnsupportedEncodingException, FileEmptyException, IOException {
        f.delete();
        f = new File(testDir + fileName);
        f.createNewFile();
        Tiedosto t = new Tiedosto(testDir + fileName);
        t.lueListaan();
    }
}
