package sanasampo.data;

import java.util.Random;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class RuudukkoTest {

    Ruudukko r;
    Random rand;

    public RuudukkoTest() {
        r = new Ruudukko();
        rand = new Random();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void alustusTyhjallaTuottaaErrorin() {
        assertEquals(false, r.alusta(""));
    }

    @Test
    public void erikoisMerkitEivatKelpaa() {
        assertEquals(true, !r.alusta("a!bc")
                && !r.alusta("a_bc")
                && !r.alusta("üabc")
                && r.alusta("äöåq"));
    }
    
    @Test
    public void valilyonnitEivatKelpaa() {
        assertEquals(true, !r.alusta("a bc"));
    }
    
    @Test
    public void skandinaavisetAakkosetKelpaavat(){
        assertEquals(true, r.alusta("äöåa"));
    }
    
    @Test
    public void alustusVaaranKokoisellaTuottaaErrorin() {
        assertEquals(false, r.alusta("asdfghjk"));
    }
}
