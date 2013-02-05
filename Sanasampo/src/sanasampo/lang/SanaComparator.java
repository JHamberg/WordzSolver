package sanasampo.lang;

import java.util.Comparator;

/** Vertailuluokka merkkijonojen (String) järjestämiseen pituuden mukaan*/

public class SanaComparator implements Comparator<String> {

    /** 
     * Toteuttaa rajapinnan metodin compare 
     * @param o1 Ensimmäinen vertailtava 
     * @param o2 Toinen vertailtava
     * @see sanasampo.logic.Helper
     */
    
    @Override
    public int compare(String o1, String o2) {
        if (o1.length() > o2.length()) {
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        }
        return o1.compareTo(o2);
    }
}
