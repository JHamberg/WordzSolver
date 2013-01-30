package sanasampo.logic;

import java.util.ArrayList;
import java.util.TreeMap;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;


public class Haku {

    private TreeMap<String, ArrayList<String>> hitlist;
    private Hakemisto h;
    private Syvahaku s;
    private Esitarkastus e;
    private ArrayList<String> mahdolliset, osumat; //potentiaaliset sanat

    public Haku(Hakemisto h, Ruudukko r) {
        this.h = h;
        e = new Esitarkastus(r);
        s = new Syvahaku(r);
        mahdolliset = new ArrayList<String>();
        osumat = new ArrayList<String>();
        hitlist = new TreeMap<String, ArrayList<String>>();
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
                hitlist.put(k, s.getPolku());
            }
        }
    }
    
    public ArrayList<String> getTulos(){
        return osumat;
    }
    
    public ArrayList<String> getMahdolliset(){
        return mahdolliset;
    }
    
    public TreeMap<String, ArrayList<String>> getHitlist(){
        return hitlist;
    }
}
