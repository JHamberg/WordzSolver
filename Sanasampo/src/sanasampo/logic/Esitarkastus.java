/** Sovelluslogiikka ja apuluokka */
package sanasampo.logic;

import sanasampo.data.Ruudukko;

/** Karsii sanoista pois ne joita ei kirjaimien, tai niiden määrien perusteella 
 voi muodostaa*/

public class Esitarkastus {
    /** Ruudukko joka sisältää kirjaimet */
    private Ruudukko ruudukko;
    
    /** Alustaa ruudukon parametrista saadulla */
    public Esitarkastus(Ruudukko ruudukko){
        this.ruudukko = ruudukko;
    }
    
    /** Suorittaa esitarkastuksen luomalla väliaikaisen ruudukon. 
     * Jos kirjain löytyy, poistetaan sen ruudukosta duplikaattien 
     * välttämiseksi. Löytymättömän kirjaimien koordinaatit ovat 
     * (-1,-1). 
     * 
     * @param s Etsittävä sana
     * @return True jos sana voidaan muodostaa ruudukon kirjaimista.
     * False jos ruudukossa ei ole jotain sanan kirjaimista, tai niitä
     * on liian vähän.
     * @see sanasampo.data.Ruudukko#charSijainti(char)
     */
    public boolean suorita(String s){
        
        Ruudukko temp = alusta();
        
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
    
    /** Alustaa väliaikaisen ruudukon alkuperäisellä ruudukolla. */
    private Ruudukko alusta(){
        Ruudukko r = new Ruudukko();
        r.alusta(ruudukko.getKirjaimet());
        return r;
    }
}
