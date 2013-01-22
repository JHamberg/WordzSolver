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
    public int apinavakio = 101;

    public Sanakirja() throws FileNotFoundException, IOException {
        this(DEFAULT_DICTIONARY);
    }

    public Sanakirja(String polku) throws FileNotFoundException, IOException {
        try {
            FileReader r = new FileReader(polku);
            BufferedReader lukija = new BufferedReader(r);
            sanat = alustaSanakirja(lukija);
            
        } catch (FileNotFoundException e) {   //Jos tiedostoa ei ole 
            System.out.println("Not found!"); //TBI Graafinen huomautus ja paluu
            error = true;
        }
    }

    private ArrayList<String> alustaSanakirja(BufferedReader r) throws IOException{
            
            ArrayList<String> temp = new ArrayList<String>();

            String sana;
            while ((sana = r.readLine()) != null) {
                if (sana.length() >= DEFAULT_LENGTH) {
                    temp.add(sana);
                }
            }
            r.close();
            return temp;
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

    public void viewSanakirja() {
        //TODO
    }
}
