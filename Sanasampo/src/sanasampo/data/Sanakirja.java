/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jonatan
 */
public class Sanakirja {

    private static final String DEFAULT_DICTIONARY = "sanat.txt"; // in main folder
    private ArrayList<String> sanat;
    private Scanner s;
    private String polku;
    private boolean error = false;

    public Sanakirja() throws FileNotFoundException {
        this(DEFAULT_DICTIONARY);
    }

    public Sanakirja(String polku) {
        try {
            this.polku = polku;
            s = new Scanner(new File(polku));
            sanat = new ArrayList<String>();
            String sana; 
            
            while (s.hasNext()) {
                sana = s.next();
                if(sana.length() >= 3){
                    sanat.add(sana);
                }
                //Luetaan sanat tiedostosta listaan
            }
            s.close();
        } catch (FileNotFoundException e) {   //Jos tiedostoa ei ole 
            System.out.println("Sanakirja 404"); //TBI Graafinen huomautus ja paluu
            error = true;
        }

    }
    

    public boolean getErrorState() {
        return error; //TBI workaround
    }

    public String getPolku() {
        return this.polku;
    }

    public int getReuna() {
        return sanat.size();
    }

    public String getSana(int i) {
        return sanat.get(i); //Tarkistus iteroinnin yhteydessa
    }

    public void viewSanakirja() {
        //TODO
    }
}
