package sanasampo.logic;

import java.util.ArrayList;
import java.util.TreeMap;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;

/** Hakualgoritmi, joka suorittaa esitarkastuksen ja syvähaun sanakirjan sanoilla, 
 * palauttaa ruudukosta löytyvät sanat. Palauttaa myös kirjaimien
 * koordinaatit, joista sana muodostuu ruudukon korostusta varten.
 * @see sanasampo.logic.Esitarkastus
 * @see sanasampo.logic.Syvahaku
 * @see sanasampo.data.Sanakirja
 * @see sanasampo.ui.RuudukkoPanel#korostaPolku(String, TreeMap) 
 */
public class Haku {

    /** Lista joka sisältää löytyneet sanat ja niihin linkitetyt polut */
    private TreeMap<String, ArrayList<String>> polutJaOsumat;
    
    /** Hakemisto jonka sanakirjoista sanaehdokkaat haetaan */
    private Hakemisto h;
    
    /** Rekursiivisen hakualgoritmin suorittava luokka */
    private Syvahaku s;
    
    /** Esitarkastuksen suorittava luokka */
    private Esitarkastus e;
    
    /** Lista esitarkastuksen tuloksista, joille syvähaku suoritetaan */
    private ArrayList<String> mahdolliset; 
        
    /** Lista löytyneistä sanoista */
    private ArrayList<String> osumat; 

    /** Alustaa hakuun tarvittavat muuttujat ja luokat */
    public Haku(Hakemisto h, Ruudukko r) {
        this.h = h;
        e = new Esitarkastus(r);
        s = new Syvahaku(r);
        mahdolliset = new ArrayList<String>();
        osumat = new ArrayList<String>();
        polutJaOsumat = new TreeMap<String, ArrayList<String>>();
    }

    /** Käynnistää hakuprosessin */
    public void kaynnista() {
        for (Sanakirja s : h.getSanakirjat()) { //Suoritetaan haku jokaiselle sanakirjalle
            suoritaHaku(s);
        }
    }

    /** Suorittaa kaksi hakuvaihetta 
     @param s Käsiteltävä sanakirja*/
    private void suoritaHaku(Sanakirja s) {
        eliminoiMahdottomat(s);
        syvaHaku();
        
    }

    /** Suorittaa jokaiselle sanalle esitarkastuksen ja lisää osumat listaan 
     @param s Käsiteltävä sanakirja */
    private void eliminoiMahdottomat(Sanakirja s) {
        for (String sana : s.getSanat()) {
            if (e.suorita(sana)) {
                mahdolliset.add(sana);
            }
        }
    }
    
    /** Suorittaa syvähaun esitarkastuksen tuloksille ja lisää lopulliset tulokset
     * niille tarkoitettuihin tietorakenteisiin. */
    private void syvaHaku(){
       for(String k : mahdolliset){
            if(s.suorita(k)){
                osumat.add(k);
                polutJaOsumat.put(k, s.getPolku());
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
        return polutJaOsumat;
    }
}
