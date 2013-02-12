package sanasampo.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    private String oletus_sanakirja;
    
    /** Lista johon sanat luetaan tekstitiedostosta*/
    private ArrayList<String> sanat;
    
    /** Luettavan sanatiedoston tiedostopolku*/
    private String polku;

    /**
     * Parametriton konstruktori kutsuu (kuormitettua) itseään oletuksena
     * olevalla tiedostopolulla, joka on määritelty muuttujaosiossa.
     * 
     * Jos ohjelman toiminnan kannalta kriittisiä tiedostoja ei löydy
     * näytetään error ja suljetaan ohjelma. 
     *
     * @see sanasampo.data.Sanakirja#oletus_sanakirja
     */
    public Sanakirja() {
        try{oletus_sanakirja = new Tiedosto("dic\\dictionary").lueListaan().get(0);
            alusta("dic\\"+oletus_sanakirja);}
        
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Failed to read dictionary file!\n"
             + "Program will now terminate.");
             System.exit(0);}
        }
    
    /**
     * Konstruktori alustaa sanalistan lukemalla sanoja tekstitiedostosta, 
     * käyttäen luokkaa {@link sanasampo.data.Tiedosto} tiedostonhallintaan.
     * Tekstitiedosto metodille parametrina annetusta tiedostopolusta.
     
     * @param polku Käyttäjän antama tiedostopolku
     * @see sanasampo.data.Tiedosto#lueListaan()
     */
    public void alusta(String polku) throws FileNotFoundException, UnsupportedEncodingException, IOException, FileEmptyException {
        this.polku = polku;
        Tiedosto tk = new Tiedosto(polku);
        sanat = tk.lueListaan();
        
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
