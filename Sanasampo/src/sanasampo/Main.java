package sanasampo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;
import sanasampo.logic.Haku;
import sanasampo.ui.Kayttoliittyma;

// @author JHamberg 
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //TBI: Siirrä ohjelman alustus omaan luokkaansa

        Hakemisto h = new Hakemisto(new Sanakirja());
      
         Scanner s = new Scanner(System.in);
        
         System.out.print("Ruudukon kirjaimet: ");
         String input = s.nextLine();
         Ruudukko r = new Ruudukko();
         while(!r.alusta(input)){
         System.out.print("\nError: Ruudukko ei kelpaa!\nAnna uudet kirjaimet: ");
         input = s.nextLine();
         }
         
         Haku haku = new Haku(h, r);
         haku.kaynnista();
         Kayttoliittyma kayttoliittyma = new Kayttoliittyma(r); //Tehty koodaamisen helpottamiseksi
         SwingUtilities.invokeLater(kayttoliittyma);
    }
}
