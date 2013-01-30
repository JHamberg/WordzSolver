package sanasampo.data;

// @author JHamberg
import java.util.regex.Pattern;
import sanasampo.logic.Helper;

public class Ruudukko {

    private Pattern p;
    private int reuna;
    private String ruudukko[][];
    private String kirjaimet;
    private Helper h; 

    public Ruudukko() {
        
        //Hyväksytään vain skandinaaviset aakkoset
        p = Pattern.compile("^[a-zA-Z0-9äöåÅÄÖ]*$", Pattern.CASE_INSENSITIVE);
        h = new Helper();
    }

    public int getKoko() {
        return reuna;
    }

    public String[][] getRuudukko() {
        return ruudukko;
    }

    public boolean alusta(String k) {

        if (validate(k)) {
            kirjaimet = k;
            reuna = (int) Math.sqrt(k.length());
            ruudukko = new String[reuna][reuna]; //Luodaan AxA kokoinen ruudukko
            alustaRuudukko();
            return true;

        } else {
            return false;
        }
    }

    private void alustaRuudukko() {
        int pituus = 0;
        for (int i = 0; i < reuna; i++) {
            for (int j = 0; j < reuna; j++) {
                //Lisätään käyttäjän antamat kirjaimet ruudukkoon
                ruudukko[i][j] = Character.toString(kirjaimet.charAt(pituus));
                pituus++;
            }
        }
    }

    private boolean validate(String k) {
        if (k == null) { //Painettu cancel? 
            System.exit(0);
        }
        if (!h.onKahdenPotenssi(k.length()) //Tasasivuinen ruudukko?
                || k.isEmpty() //Tyhjä syöte?
                || k.contains(" ") //Välilyöntejä?
                || !p.matcher(k).find()) { //Erikoismerkkejä
            return false;
        } else {
            return true;
        }
    }

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

    public Ruudukko kloonaa() {
        return this;
    }

    public String getKirjaimet() {
        return kirjaimet;
    }
}
