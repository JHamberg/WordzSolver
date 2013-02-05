package sanasampo.logic;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class HelperTest {

    Helper h;

    public HelperTest() {
        h = new Helper();
    }

    @Test
    public void yhdistaminenToimii() {
        assertEquals("42", h.yhdista(4, 2));
    }
    
    @Test
    public void yhdistaminenToimiiNegatiivisilla() {
        assertEquals("4-2", h.yhdista(4, -2));
    }

    @Test
    public void onKahdenPotenssiToimiiNegatiivisilla() {
        assertEquals(false, h.onKahdenPotenssi(-4));
    }

    @Test
    public void onKahdenPotenssiToimii() {
        assertEquals(true, h.onKahdenPotenssi(4)
                && h.onKahdenPotenssi(16)
                && h.onKahdenPotenssi(25)
                && h.onKahdenPotenssi(36)
                && h.onKahdenPotenssi(49)
                && h.onKahdenPotenssi(64)
                && h.onKahdenPotenssi(81)
                && h.onKahdenPotenssi(100));
    }
    
    @Test
    public void reverseOrderToimii() {
        ArrayList<String> a = new ArrayList<String>(Arrays.asList("iso", "isompi", "isoin"));
        String apu ="";
        a = h.reverseOrder(a);
        for(String s: a){
            apu += s;
        }
        assertEquals("isompiisoiniso", apu);
    }
    
    @Test
    public void reverseOrderToimiiYhdenKokoiselle() {
        ArrayList<String> a = new ArrayList<String>(Arrays.asList("foreveralone"));
        String apu ="";
        a = h.reverseOrder(a);
        for(String s: a){
            apu += s;
        }
        assertEquals("foreveralone", apu);
    }
}
