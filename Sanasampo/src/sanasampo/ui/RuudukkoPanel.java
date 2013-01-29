/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import sanasampo.data.Ruudukko;

public class RuudukkoPanel extends JPanel{
    private int koko;
    String[][] r;
    
    public RuudukkoPanel(int x, int y, Ruudukko grid){
        super(new GridLayout(x, y));
        this.setPreferredSize(new Dimension(450, 500));
        r = grid.getRuudukko();
        koko = grid.getKoko();
        luoKomponentit(r);
    }

    private void luoKomponentit(String[][] r) {
         for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                this.add(new JButton(r[i][j])); //jokaisesta ruudukon alkiosta painike
            }
    }
}
}
