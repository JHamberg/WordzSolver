/** Ohjelman alustus ja käynnistys */
package sanasampo;

import java.io.FileNotFoundException;
import java.io.IOException;

// @author JHamberg 
// Copyright (c) © 2013 

/** Käynnistää ohjelman */
public class Main {

    /** Luo ja alustaa Sampon, käynnistää ohjelman */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Sampo s = new Sampo();
        s.asenna();
        s.kaynnista();
    }
}
