package sanasampo.data;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import sanasampo.lang.FileEmptyException;


/** Tiedostojen peruskäsittely. Sisällön listaan lukeminen. */

public class Tiedosto {

    /** Tiedostonlukija*/
    private BufferedReader br;
    
    /**Käsiteltävä tiedosto*/
    private File file;

    /** Tiedoston ulkoiseen avaamiseen */
    Desktop desktop;
    
    //** Lista johon sanat luetaan tiedostosta */ 
    ArrayList<String> sisalto; 
    
    /**
     * Konstruktori alustaa {@link sanasampo.data.Tiedosto#file}-olion polulla,
     * jonka metodi saa parametrina. 
     * 
     * @param polku Tiedostopolku
     */
    public Tiedosto(String polku) throws FileNotFoundException, UnsupportedEncodingException {
         file = new File(polku);
    }

    /**
     * Iteroi tiedoston lukien jokaisen rivin listaan. Tukee 
     * <a href="http://fi.wikipedia.org/wiki/ISO_8859-1"> ISO-8859-1</a>-merkistöä.
     * 
     * @return Tiedostosta luetut sanat listana
     */
    public ArrayList<String> lueListaan() throws IOException, FileEmptyException {
        
        //ISO-8859-1 tukee ääkkösiä
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1")); 
        sisalto = new ArrayList<String>();
        String sana;
        while ((sana = br.readLine()) != null) {
            if (sana.length() >= 3) sisalto.add(sana);
        }
        if(sisalto.isEmpty()){throw new FileEmptyException("File is empty!");}
        br.close();
        return sisalto;
    }
    
    /** Avaa tiedoston toisessa ohjelmassa */
    public void avaa() throws IOException{
        desktop = Desktop.getDesktop();
        desktop.open(file); 
    }
    
    /** Palauttaa totuusarvon tiedoston olemassaolosta */
    public boolean onOlemassa(){
        return file.exists();
    }
    
    public void kirjoita(String s) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(s);
        writer.close();
    }
   
    public String getNimi(){
        return file.getName().replaceFirst("[.][^.]+$", "");
    }

}
