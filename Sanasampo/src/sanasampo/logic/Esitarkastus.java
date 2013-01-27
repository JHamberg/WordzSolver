package sanasampo.logic;

import sanasampo.data.Ruudukko;

//Tarkistaa, löytyykö ruudukosta edes tarpeeksi kirjaimia sanan muodostamiseen

public class Esitarkastus {
    Ruudukko ruudukko;
    
    public Esitarkastus(Ruudukko ruudukko){
        this.ruudukko = ruudukko;
    }
    
    public boolean suorita(String s){
        
        Ruudukko temp = alusta(s);
        
        for (int i = 0; i < s.length(); i++) {
            int x = temp.charSijainti(s.charAt(i))[0];
            int y = temp.charSijainti(s.charAt(i))[1];
            if (x == -1 && y == -1) { //Jos sanassa olevaa kirjainta ei ole
                return false;         //hylätään sana
            }
            temp.poistaRuutu(x, y); //Sama kirjain ei saa löytyä uudestaan
        }
        return true;
    }
    
    private Ruudukko alusta(String s){
        Ruudukko r = new Ruudukko();
        r.alusta(ruudukko.getKirjaimet());
        return r;
    }
    
    
}
