/** Ohjelman alustus ja käynnistys */
package sanasampo;

import java.io.FileNotFoundException;
import java.io.IOException;
import sanasampo.lang.FileEmptyException;

// @author JHamberg 
// Copyright (c) © 2013 

/** Käynnistää ohjelman */
public class Main {

    /** Luo ja alustaa Sampon, käynnistää ohjelman */
    public static void main(String[] args) throws FileNotFoundException, IOException, FileEmptyException {
        Sampo s = new Sampo();
        s.asenna(); 
        s.kaynnista();
    }
}
