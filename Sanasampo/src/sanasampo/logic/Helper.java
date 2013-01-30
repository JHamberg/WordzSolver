package sanasampo.logic;

import java.util.ArrayList;
import java.util.Collections;
import sanasampo.lang.SanaComparator;

public class Helper {
    
    //Perusmetodeja
    
    public boolean onKahdenPotenssi(long n) {
        if (n < 0) {
            return false;
        }
        long tst = (long) (Math.sqrt(n) + 0.5);
        return tst * tst == n;
    }
    
    public String yhdista(int x, int y) {
        return Integer.toString(x).concat(Integer.toString(y));
    }
    
    public ArrayList<String> reverseOrder(ArrayList<String> a){
        Collections.sort(a, new SanaComparator());
        Collections.reverse(a);
        return a;
    }
}
