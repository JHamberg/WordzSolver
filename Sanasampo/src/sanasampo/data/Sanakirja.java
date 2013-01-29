package sanasampo.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Sanakirja {

    //Staattiset
    private static final String DEFAULT_DICTIONARY = "sanat.txt";
    private ArrayList<String> sanat;
    private String polku;
    private boolean error = false;

    public Sanakirja() throws FileNotFoundException, IOException {
        this(DEFAULT_DICTIONARY);
    }

    public Sanakirja(String polku) {
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

    public String getPolku() {
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
