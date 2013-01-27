/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author Jonatan
 */
public class Tiedosto {

    FileReader fr;
    BufferedReader br;

    public Tiedosto(String polku) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(polku);
        fr = new FileReader(polku);
      br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
        //br = new BufferedReader(fr);
    }

    public ArrayList<String> lueListaan() throws IOException {
        ArrayList<String> temp = new ArrayList<String>();

        String sana;
        while ((sana = br.readLine()) != null) {
            if (sana.length() >= 3) {
                temp.add(sana);
            }
        }
        br.close();
        return temp;
    }
}
