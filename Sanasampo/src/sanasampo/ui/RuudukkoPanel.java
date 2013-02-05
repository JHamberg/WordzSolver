/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import sanasampo.data.Ruudukko;
import sanasampo.logic.Helper;

/** Esittää käyttäjän antaman syötteen siinä muodossa, jossa
 * ohjelma (ja todennäköisesti myös käyttäjä sen hahmottaa.
 * Mahdollistaa sanapolkujen korostamisen ja ruudukon päivittämisen
 * @see sanasampo.Sampo#input
 */
public class RuudukkoPanel extends JPanel {

    /** Ruudukon koko*/
    private int koko;
    
    /** Ruudukko kaksiulotteisena arrayna iterointia varten */
    private String[][] r;
    
    /** Taulukko joka sisältää ruudukossa olevat painikkeet ja 
     yksiselitteisen esityksen niiden koordinaateille */
    private HashMap<JButton, String> buttonMap;
    
    /** Apuluokka solukoordinaattien yhdistämistä varten*/
    Helper h;

    /** Alustaa tarvittavat muuttujat ja toteuttaa JPanelin 
     * määreet. Kutsuu komponentit luovaa metodia. 
     * @param x Ruudukon korkeus
     * @param y Ruudukon leveys
     * @see #luoKomponentit(String[][])
     */
    public RuudukkoPanel(int x, int y, Ruudukko grid) {
        super(new GridLayout(x, y));
        this.setPreferredSize(new Dimension(450, 500));
        
        h = new Helper();
        r = grid.getRuudukko();
        buttonMap = new HashMap<JButton, String>();
        koko = grid.getKoko();
        
        luoKomponentit(r);
    }

    /** Iteroid taulukon ja lisää jokaisen kirjaimen painikkeena ruudukkoon 
     @param r Ruudukko kaksiulotteisena taulukkona */
    private void luoKomponentit(String[][] r) {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                JButton uusi = new JButton(r[i][j]);
                buttonMap.put(uusi, h.yhdista(i, j));
                this.add(uusi); //jokaisesta ruudukon alkiosta painike
            }
        }
    } 
    
    /** Poistaa käytöstä ne painikeet, jotka ovat indeksoituina 
     * osumalistassa (korostus).
     *@param k Korostettava sana  
     *@param osumat Lista sanoista ja niiden poluista
     */
    public void korostaPolku(String k, TreeMap<String, ArrayList<String>> osumat) {
        //Poistetaan aiemmat korostukset
        uudelleenPiirra();
        //Korostetaan painikkeet jotka sijaitsevat polun koordinaateissa
        for (String polku : osumat.get(k)) {
            for (JButton j : buttonMap.keySet()) {
                if (buttonMap.get(j).equals(polku)) {
                    j.setEnabled(false);
                }
            }
        }
    }
    
    /** Piirtää ruudukon uudestaan. Asettaa kaikki painikkeet käytettäviksi*/
    public void uudelleenPiirra(){
        for (JButton j : buttonMap.keySet()) {
            j.setEnabled(true);
        }
    }
}
