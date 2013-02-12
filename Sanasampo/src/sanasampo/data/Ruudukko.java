package sanasampo.data;

// @author JHamberg
import java.util.regex.Pattern;
import sanasampo.logic.Helper;

/** Käyttäjän antamasta syötteestä luotu kirjainruudukko, josta 
 * voidaan hakea ja poistaa kirjaimia.<br> Ruudukon alustukseen liittyy
 * olennaisesti syötteen validointi.
 */

public class Ruudukko {

    /** Pattern ruudukon validointia varten*/
    private Pattern p;
    
    /** Ruudukon reunan pituus */
    private int reuna;
    
    /** Kaksiulotteinen array, johon kirjaimet luetaan*/
    private String ruudukko[][];
    
    /** Ruudukon sisältämät kirjaimet*/
    private String kirjaimet;
    
    /** Apuluokka ruudukon muodon tarkistamiseksi*/
    private Helper h;
    
    /** Konstruktori alustaa patternin, joka sisältää ehdon ruudukon
     * hyväksytyistä merkeistä (a-ö), sekä apuluokan
     * {@link sanasampo.logic.Helper} 
     */
    public Ruudukko() {
        p = Pattern.compile("^[a-zA-ZäöåÅÄÖ]*$", Pattern.CASE_INSENSITIVE);
        h = new Helper();
    }

    public int getKoko() {
        return reuna;
    }

    public String[][] getRuudukko() {
        return ruudukko;
    }

    /**
     * Validoi parametrina saadut kirjaimet ja alustaa ruudukon niillä
     *
     * @param k Käyttäjän antamat ruudukon kirjaimet
     * @return True jos alustus onnistui, false jos ei
     * @see sanasampo.data.Ruudukko#alustaRuudukko()
     * @see sanasampo.data.Ruudukko#validoi(String)
     */
    public boolean alusta(String k) {

        if (validoi(k)) {
            kirjaimet = k;
            reuna = (int) Math.sqrt(k.length());
            ruudukko = new String[reuna][reuna]; //Luodaan AxA kokoinen ruudukko
            alustaRuudukko();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lukee käyttäjän antamat kirjaimet {@link #ruudukko}-muuttujaan
     */
    private void alustaRuudukko() {
        int pituus = 0;
        for (int i = 0; i < reuna; i++) {
            for (int j = 0; j < reuna; j++) {
                ruudukko[i][j] = Character.toString(kirjaimet.charAt(pituus));
                pituus++;
            }
        }
    }

    /**
     * Tarkistaa, että parametrina saatu käyttäjän syöte ei ole tyhjä, sisällä
     * kiellettyjä merkkejä tai ole väärän kokoinen.
     *
     * @param k Käyttäjän antamat kirjaimet
     * @return True jos validointi menee läpi, false jos ei
     * @see sanasampo.logic.Helper#onKahdenPotenssi(long)
     */
    private boolean validoi(String k) {
        if (k == null) {
            System.exit(0);
            return false;
        }
        if (!h.onKahdenPotenssi(k.length()) //Tasasivuinen ruudukko?
                || k.length() > 100 //Liian iso?
                || k.isEmpty() //Tyhjä syöte?
                || k.contains(" ") //Välilyöntejä?
                || !p.matcher(k).find()) { //Erikoismerkkejä?
            return false;
        } else {
            return true;
        }
    }

    /**
     * Asettaa ruudukon solun [a,b] tyhjäksi osana {@link sanasampo.logic.Esitarkastus}-prosessia
     * 
     * @param a Solun y koordinaati
     * @param b Solun x koordinaati
     * @return True jos solu löytyi ja poistettiin, false jos ei
     */
    public boolean poistaRuutu(int a, int b) {
        for (int i = 0; i < reuna; i++) {
            for (int j = 0; j < reuna; j++) {
                if (i == a && j == b) {
                    ruudukko[i][j] = "";
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Palauttaa sen ruudun, josta parametrina saatu kirjain löytyy.
     * Haku tapahtuu iteroimalla ruudukko läpi. <br>Kirjaimen löytyessä
     * talletetaan solun x- ja y-koordinaatit taulukkoon. 
     * 
     * @param ch Etsittävä kirjain
     * @return Kaksialkioinen lista, joka sisältää solun koordinaatit
     */
    public int[] charSijainti(char ch) {
        int[] sijainnit = new int[2];
        String c = Character.toString(ch); //Käytössä String 
        for (int i = 0; i < reuna; i++) {
            for (int j = 0; j < reuna; j++) {
                if (ruudukko[i][j].equals(c)) {
                    sijainnit[0] = i;
                    sijainnit[1] = j;
                    return sijainnit;
                }
            }
        }
        sijainnit[0] = -1;
        sijainnit[1] = -1;
        return sijainnit;
    }

    /**
     * Palauttaa kopion oliosta
     * 
     * @return Ruudukko-olio
     */
    public Ruudukko kloonaa() {
        return this;
    }
    
    public String getKirjaimet() {
        return kirjaimet;
    }
}
