package sanasampo.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//@author JHamberg
public class Sanakirja {

    //Staattiset
    private static final String DEFAULT_DICTIONARY = "sanat.txt"; //in main folder 
    private static final int DEFAULT_LENGTH = 3; //word length
    
    private ArrayList<String> sanat;
    private String polku;
    private boolean error = false;

    public Sanakirja() throws FileNotFoundException, IOException {
        this(DEFAULT_DICTIONARY);
    }

    public Sanakirja(String polku){
        this.polku = polku;
        
        try {
            Tiedosto tk = new Tiedosto(polku);
            sanat = tk.lueListaan();
            
        } catch (Exception e) {   //Jos tiedostoa ei ole 
            System.out.println("File not found!"); //TBI Graafinen huomautus ja paluu
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
