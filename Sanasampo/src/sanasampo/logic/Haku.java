/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.logic;

import java.util.ArrayList;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;

/**
 *
 * @author Jonatan
 */
public class Haku {
    //Hakualgoritmi puuttuu yhä, TBI

    Hakemisto h;
    Ruudukko r;
    ArrayList<String> mahdolliset; //potentiaaliset sanat

    public Haku(Hakemisto h, Ruudukko r) {
        this.h = h;
        this.r = r;
        mahdolliset = new ArrayList<String>();
    }

    public void kaynnista() {
        for (Sanakirja s : h.getSanakirjat()) { //Suoritetaan haku jokaiselle sanakirjalle
            suoritaHaku(s);
        }
    }

    private void suoritaHaku(Sanakirja s) {
        eliminoiMahdottomat(s);
        for (String k : mahdolliset) {
            System.out.println(k);
        }
        System.out.println("mahdolliset size: " + mahdolliset.size());
    }

    private void eliminoiMahdottomat(Sanakirja s) {
        for (String sana : s.getSanat()) {
            if (checkExistance(sana)) {
                mahdolliset.add(sana);
            }
        }
    }

    private boolean checkExistance(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!r.containsChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
