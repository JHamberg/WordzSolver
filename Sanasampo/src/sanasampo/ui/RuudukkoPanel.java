/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import sanasampo.data.Ruudukko;
import sanasampo.logic.Helper;

public class RuudukkoPanel extends JPanel {

    private int koko;
    private String[][] r;
    private HashMap<JButton, String> buttonMap;
    Helper h;

    public RuudukkoPanel(int x, int y, Ruudukko grid) {
        super(new GridLayout(x, y));
        this.setPreferredSize(new Dimension(450, 500));
        
        h = new Helper();
        r = grid.getRuudukko();
        buttonMap = new HashMap<JButton, String>();
        koko = grid.getKoko();
        
        luoKomponentit(r);
    }

    private void luoKomponentit(String[][] r) {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                JButton uusi = new JButton(r[i][j]);
                buttonMap.put(uusi, h.yhdista(i, j));
                this.add(uusi); //jokaisesta ruudukon alkiosta painike
            }
        }
    }

    public void highlight(String k, TreeMap<String, ArrayList<String>> hitlist) {
        //Poistetaan aiemmat korostukset
        for (JButton j : buttonMap.keySet()) {
            j.setEnabled(true);
        }

        //Korostetaan painikkeet jotka sijaitsevat polun koordinaateissa
        for (String polku : hitlist.get(k)) {
            for (JButton j : buttonMap.keySet()) {
                if (buttonMap.get(j).equals(polku)) {
                    j.setEnabled(false);
                }
            }
        }
        
    }
}
