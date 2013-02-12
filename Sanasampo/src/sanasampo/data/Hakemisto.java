/** Tiedon- ja tiedostojenhallinta */
package sanasampo.data;

import java.util.ArrayList;

/** Sisältää listan sanakirjoja ja niiden hallintaan tarvittavat metodit. 
  * Tällä hetkellä ohjelma tukee vain yhtä sanakirjaa kerrallaan, mutta 
  * laajennukselle on jätetty varaa */

public class Hakemisto {
    
    /** Lista johon  {@link sanasampo.data.Sanakirja}-oliot talletetaan */
    private ArrayList<Sanakirja> hakemisto;
    
    /** Parametrillinen konstruktori alustaa hakemiston ja lisää
     * siihen parametrina saadun Sanakirjan 
     * @param oletus Oletuksena käytettävä sanakirja 
     * @see sanasampo.data.Sanakirja
     */
    public Hakemisto(Sanakirja oletus){
        hakemisto = new ArrayList<Sanakirja>();
        hakemisto.add(oletus);
    }
    
    /** Toimii kuten {@link #Hakemisto(Sanakirja)}
     * mutta alustettuun hakemistoon ei lisätä sanakirjaa.*/
    public Hakemisto(){
        hakemisto = new ArrayList<Sanakirja>();
    }
    
    /** Lisää parametrina saadun sanakirjan hakemistoon. <br>
     * Tarkistetaan myös ettei lisättävä olio löydy jo hakemistosta.
     @see sanasampo.data.Sanakirja#getTiedostoPolku()
     @param sanakirja Lisättävä sanakirja*/
    public boolean lisaaSanakirja(Sanakirja sanakirja){
        for(Sanakirja s : hakemisto){
            if (s.getTiedostoPolku().equals(sanakirja.getTiedostoPolku())){ //Polku on uniikki jokaiselle
                return false;
            }
        }
        hakemisto.add(sanakirja);
        return true;
    }
    
    public ArrayList<Sanakirja> getSanakirjat(){
        return hakemisto;
    }
    
    
    
}
