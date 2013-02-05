package sanasampo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sanasampo.Sampo;

/** Tarkistaa onko valikkoelementtiä painettu ja suorittaa 
 * valintaan liittyvät operaatiot. 
 */
public class ValikkoKuuntelija implements ActionListener {

    /** Yläluokka uudelleenajoa varten */
    private Sampo s;
    
    //** Käyttöliittymä vanhan suorituksen piilottamiseksi */
    private Kayttoliittyma ui;

    /** Alustaa kuuntelijan muuttujat saaduilla parametreilla 
    *@param sa Yläluokka
    *@param u Käyttöliittymä 
    */
    public ValikkoKuuntelija(Sampo sa, Kayttoliittyma u) {
        s = sa;
        ui = u;
    }

    /** Valinnasta seuraavien kutsujen hallinta
     *@see sanasampo.Sampo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if(action.equals("Exit")){
            System.exit(0);
        }
        else if(action.equals("New grid")){
            try{s.kaynnista();}
            catch(Exception x){}
            ui.getFrame().setVisible(false);
        }

    }
}
