package sanasampo.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class RuudukkoTest {

    Ruudukko r;

    public RuudukkoTest() {
        r = new Ruudukko();
    }

    @Test
    public void alustusTyhjallaTuottaaErrorin() {
        assertFalse(r.alusta(""));
    }
    
    @Test
    public void ruudukonKokoPalauttaaOikein() {
        r.alusta("abcdefghijklmnop");
        assertEquals(4, r.getKoko());
    }
    
    @Test
    public void ruudustaPoistaminenToimii(){
        r.alusta("abcdefghijklmnop");
        r.poistaRuutu(1, 1);
        assertEquals(-1, r.kirjaimenSijainti('f')[0]);
    }
    
    @Test
    public void erikoisMerkitEivatKelpaa() {
        assertTrue(!r.alusta("a!bc")
                && !r.alusta("a_bc")
                && !r.alusta("üabc")
                && r.alusta("äöåq"));
    }
    
    @Test
    public void valilyonnitEivatKelpaa() {
        assertTrue(!r.alusta("a bc"));
    }
    
    @Test
    public void skandinaavisetAakkosetKelpaavat(){
        assertTrue(r.alusta("äöåa"));
    }
    
    @Test
    public void alustusVaaranKokoisellaTuottaaErrorin() {
        assertFalse(r.alusta("asdfghjk"));
    }
    
    @Test
    public void alustusLiianIsollaTuottaaErrorin() {
        assertFalse(r.alusta("fnwusplyvsorexwaxkjqjywftexuhsrbthovawuzitqxnucigxzhpnmxzjjuhaybzxxynztpewmofuagophwonzhrhtcirfqmuemq"));
    }
    
    @Test
    public void alustusLiianPienellaTuottaaErrorin() {
        assertFalse(r.alusta("a"));
    }
    
    @Test
    public void kirjaimenSijaintiPalauttaaOikein(){
        r.alusta("abcdefghijklmnop");
        assertEquals(1, r.kirjaimenSijainti('b')[1]);
    }
}
