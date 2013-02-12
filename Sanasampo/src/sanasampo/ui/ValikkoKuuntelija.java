package sanasampo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sanasampo.Sampo;

/** Tarkistaa onko valikkoelementtiä painettu ja suorittaa 
 * valintaan liittyvät operaatiot. 
 */
public class ValikkoKuuntelija implements ActionListener {
    
    //** Käyttöliittymä vanhan suorituksen piilottamiseksi */
    private Kayttoliittyma ui;

    /** Alustaa kuuntelijan muuttujat saaduilla parametreilla 
    *@param u Käyttöliittymä 
    */
    public ValikkoKuuntelija(Kayttoliittyma u) {
        ui = u;
    }

    /** Valinnasta seuraavien kutsujen hallinta
     *@see sanasampo.Sampo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if(action.equals("Exit")){
            ui.exit();
        }
        else if(action.equals("New grid..")){
            ui.kaynnista();
        }
        else if(action.equals("About")){
            ui.naytaAbout();
        }
        
        else if(action.equals("Change dictionary..")){
            try {
                ui.vaihdaSanakirjaa();
            } catch (Exception ex) {
            }
        }
        
        else if (action.equals("Help")){
            try {ui.naytaHelp();} 
            catch (Exception ex) {
                ui.helpVirhe();
            }
        }
        
        else if(action.equals("About")){
            ui.naytaAbout();
        }

    }
}
