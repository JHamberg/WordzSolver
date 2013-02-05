package sanasampo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sanasampo.data.Hakemisto;
import sanasampo.data.Ruudukko;
import sanasampo.data.Sanakirja;
import sanasampo.logic.Haku;
import sanasampo.ui.Kayttoliittyma;

/** Vastaanottaa käyttäjän syötteen, aloittaa haun, luo ja näyttää näkymän (GUI) */

public class Sampo {

    /** Ohjelman käyttöliittymä */
    private Kayttoliittyma ui;
    
    /** Sanakirjat sisältävä hakemisto */
    private Hakemisto h;
    
    /** Käyttäjän määrittelemät kirjaimet sisältävä ruudukko */
    private Ruudukko r;
    
    /** Käyttäjän syöte, jolla ruudukko alustetaan */
    private String input;
    
    /** Hakualgoritmin toteuttava luokka */
    private Haku haku;
    
    /** Kysyy käyttäjältä kirjaimet, pyrkii alustamaan niillä ruudukon ja näyttää
     * virhedialogin validoinnin epäonnistuessa. Aloittaa haun ja kutsuu esiin 
     * käyttöliittymän. Jos parametri löytyy koitetaan alustaa sillä. 
     * @see sanasampo.data.Ruudukko#validate(String)
     */
     public void kaynnista(String k){
        if(k.isEmpty()) kysyKirjaimet();
        else input = k;
        
        while (!r.alusta(input)) {
            JOptionPane.showMessageDialog(null, 
                    "Grid size should be equilateral (3x3, 4x4..) and contain no special characters!", 
                    "Grid Initialization Error!", JOptionPane.INFORMATION_MESSAGE);
            kysyKirjaimet();
        }
        hae();
        nayta();
    }

    /**Parametriton konstruktori välittää kuormitetulle konstruktorille 
     * tyhjän merkkijonon
     */
    public void kaynnista() throws FileNotFoundException, IOException {
       kaynnista("");
    }
    
   
    /** Alustaa hakemiston ja ruudukon */
    public void asenna() throws FileNotFoundException, IOException {
        h = new Hakemisto(new Sanakirja());
        r = new Ruudukko();
    }

    /** Näyttää käyttäjälle syötekentän ja kysyy syötettä */
    private void kysyKirjaimet() {
        input = JOptionPane.showInputDialog(null, "Anna kirjaimet: ", "Ruudukon valinta", 1);
    }

    /** Välittää hakualgoritmin toteuttavalle luokalle ruudukon ja käytetyn hakemiston
     * ja käynnistää haun */
    private void hae() {
        haku = new Haku(h, r);
        haku.kaynnista();
    }

    /** Kutsuu esiin käyttöliittymän (GUI), joka näyttää päättyneen haun tulokset.*/
    private void nayta() {
        ui = new Kayttoliittyma(r, haku.getTulos(), haku.getHitlist(), this);
        SwingUtilities.invokeLater(ui);
    }
    
    public ArrayList<String> haeTulos(){
        return haku.getTulos();
    }
    
    public Kayttoliittyma haeUi(){
        return ui;
    }
}
