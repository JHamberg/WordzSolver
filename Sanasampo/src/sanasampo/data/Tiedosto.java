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
import sanasampo.lang.FileEmptyException;

public class Tiedosto {

    private FileReader fr;
    private BufferedReader br;
    private File file;

    public Tiedosto(String polku) throws FileNotFoundException, UnsupportedEncodingException {
        file = new File(polku);
        fr = new FileReader(polku);
    }

    public ArrayList<String> lueListaan() throws IOException, FileEmptyException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
        ArrayList<String> temp = new ArrayList<String>();
        String sana;
        
        while ((sana = br.readLine()) != null) {
            if (sana.length() >= 3) {
                temp.add(sana);
            }
        }
        if(temp.isEmpty()){throw new FileEmptyException("File is empty!");}
        
        br.close();
        return temp;
    }
    
    public boolean exists(){
        return file.exists();
    }
}
