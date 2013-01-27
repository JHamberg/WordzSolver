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

    Hakemisto h;
    Ruudukko r;
    Syvahaku s;
    Esitarkastus e;
    ArrayList<String> mahdolliset, osumat; //potentiaaliset sanat

    public Haku(Hakemisto h, Ruudukko r) {
        this.h = h;
        this.r = r;
        e = new Esitarkastus(r);
        s = new Syvahaku(r);
        mahdolliset = new ArrayList<String>();
        osumat = new ArrayList<String>();
    }

    public void kaynnista() {
        for (Sanakirja s : h.getSanakirjat()) { //Suoritetaan haku jokaiselle sanakirjalle
            suoritaHaku(s);
        }
    }

    private void suoritaHaku(Sanakirja s) {
        eliminoiMahdottomat(s);
        syvaHaku();
    }

    private void eliminoiMahdottomat(Sanakirja s) {
        for (String sana : s.getSanat()) {
            if (e.suorita(sana)) {
                mahdolliset.add(sana);
            }
        }
    }
    
    private void syvaHaku(){
       for(String k : mahdolliset){
            if(s.suorita(k)){
                osumat.add(k);
            }
        }
        
        for (String h : osumat) { //Debug
        }
    }
    
    public ArrayList<String> getTulos(){
        return osumat;
    }
    
    public ArrayList<String> getMahdolliset(){
        return mahdolliset;
    }
}
