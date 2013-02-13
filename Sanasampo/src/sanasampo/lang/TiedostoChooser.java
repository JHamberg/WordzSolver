/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.lang;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/** Tarjoaa toiminnot tiedoston valintaan ja 
 * tiedostonimen hakemiseen. */
public class TiedostoChooser extends JFileChooser{
    JFrame frame;
    
    /**Konstruktori ottaa vastaan framen, jotta avautuvan
     * dialogin sijainti saadaan oikein 
     @param frame JFrame Pääikkunan kehys */
    public TiedostoChooser(JFrame frame){
        this.frame = frame;
        
        //Filtterit
        this.setAcceptAllFileFilterUsed(false);
        this.addChoosableFileFilter(new DictionaryFilter());
        this.setMultiSelectionEnabled(false);
        
        //Avataava hakemisto
        this.setCurrentDirectory(new File("./dic"));
    }
    
    /** Näyttää tiedostonvalinta näkymän ja kertoo, onnistuiko valinta 
     *  @return True jos valittiin tiedosto, false jos ei.*/
    public boolean kysy(){
         if (this.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) return true;
         else return false;
    }
    
    /** Palauttaa valittuna olevan tiedoston nimen*/
    public String getValitunNimi(){
        return this.getSelectedFile().getName();
    }

}
