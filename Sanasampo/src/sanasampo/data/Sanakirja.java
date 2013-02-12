package sanasampo.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sanasampo.lang.FileEmptyException;

 /**
  * Varastoi käyttäjän valitsemasta tai oletuksena olevasta tiedostosta 
  * luetut sanat ArrayList-tyyppiin. <br>Tarjoaa perustoiminnallisuudet 
  * sanakirjan hallintaa varten. 
  */

public final class Sanakirja {

    /** Oletuksena alustamiseen käytetty tiedostopolku*/
    private String DEFAULT_DICTIONARY;
    
    /** Lista johon sanat luetaan tekstitiedostosta*/
    private ArrayList<String> sanat;
    
    /** Luettavan sanatiedoston tiedostopolku*/
    private String polku;
    
    /**Virhetilan totuusarvo*/
    private boolean error = false;

    /**
     * Parametriton konstruktori kutsuu (kuormitettua) itseään oletuksena
     * olevalla tiedostopolulla, joka on määritelty muuttujaosiossa.
     *
     * @see sanasampo.data.Sanakirja#DEFAULT_DICTIONARY
     */
    public Sanakirja() throws FileNotFoundException, IOException, FileEmptyException {
        DEFAULT_DICTIONARY = new Tiedosto("dictionary").lueListaan().get(0);
        alusta(DEFAULT_DICTIONARY);
        }
    /**
     * Konstruktori alustaa sanalistan lukemalla sanoja tekstitiedostosta, 
     * käyttäen luokkaa {@link sanasampo.data.Tiedosto} tiedostonhallintaan.
     * Tekstitiedosto metodille parametrina annetusta tiedostopolusta.
     
     * @param polku Käyttäjän antama tiedostopolku
     * @see sanasampo.data.Tiedosto#lueListaan()
     */
    public void alusta(String polku) {
        this.polku = polku;

        try {
            Tiedosto tk = new Tiedosto(polku);
            sanat = tk.lueListaan();
        } catch (Exception e) {   //Tiedostoa ei löytynyt
            error = true;
        }
    }

    public boolean getErrorState() {
        return error; //TBI workaround
    }

    public String getTiedostoPolku() {
        return this.polku;
    }

    public int getKoko() {
        return sanat.size();
    }

    public String getSana(int i) {
        return sanat.get(i); //Tarkistus iteroinnin yhteydessa
    }

    public ArrayList<String> getSanat() {
        return sanat;
    }
}
