/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.lang;

import java.util.Comparator;

/**
 *
 * @author Jonatan
 */
public class SanaComparator implements Comparator<String> {

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
