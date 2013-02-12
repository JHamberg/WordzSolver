package sanasampo.logic;

import java.util.ArrayList;
import java.util.Collections;
import sanasampo.lang.SanaComparator;

/** Sisältää useasti käytetyt perusmetodit ja siten pitää luokkien pituudet
 * järkevinä. */
public class Helper {

    /**Tarkistaa onko luvun neliöjuuri kokonaisluku. Jos ehto pätee, on
     * parametrina saadun luvun kokoinen ruudukko tasasivuinen
     * @see sanasampo.data.Ruudukko#validoi(String)*/
    public boolean onKahdenPotenssi(long n) {
        if (n < 0) {
            return false;
        }
        long tst = (long) (Math.sqrt(n) + 0.5);
        return tst * tst == n;
    }

    /**Yhdistää kaksi integer-muuttujaa merkkijonoksi
     * @see sanasampo.logic.Syvahaku#notAccessed(int, int, ArrayList)
     * @see sanasampo.logic.Syvahaku#tN(int, int, int, ArrayList)*/
    public String yhdista(int x, int y) {
        return Integer.toString(x).concat(Integer.toString(y));
    }
    
    /** Palauttaa parametrina saadun luvun verran välilyöntejä
     @param i Haluttujen välilyöntien määrä
     @return String joka sisältää välilyönnit */
    public String tyhjaTila(int i){
        String valilyonnit = "";
        for(int j=0; j<i;j++) valilyonnit += " ";
        return valilyonnit;
    }

    /**
     * Kääntää ja järjestää listan laskevasti sanan pituuden mukaan käyttäen
     * {@link sanasampo.lang.SanaComparator}-oliota
     */
    public ArrayList<String> kaannaJarjestys(ArrayList<String> a) {
        Collections.sort(a, new SanaComparator());
        Collections.reverse(a);
        return a;
    }

    /** Muuttaa annetun sanan ensimmäisen kirjaimen isoksi */
    public String isoAlkuKirjain(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
    
    
}
