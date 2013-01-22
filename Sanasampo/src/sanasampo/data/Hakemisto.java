/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.data;

import java.util.ArrayList;

/**
 *
 * @author Jonatan
 */
public class Hakemisto {
    private ArrayList<Sanakirja> hakemisto;
    public Hakemisto(Sanakirja perus){
        hakemisto = new ArrayList<Sanakirja>();
        hakemisto.add(perus);
    }
    
    public Hakemisto(){
        hakemisto = new ArrayList<Sanakirja>();
    }
    
    public boolean addSanakirja(Sanakirja sanakirja){
        for(Sanakirja s : hakemisto){
            if (s.getPolku().equals(sanakirja.getPolku())){ //Polku on uniikki jokaiselle
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
