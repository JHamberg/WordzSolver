package sanasampo.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import sanasampo.data.Ruudukko;

// author JHamberg
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Ruudukko r;
    private int koko;
    private ArrayList<String> sanat;

    public Kayttoliittyma(Ruudukko r, ArrayList<String> m) {
        sanat = m;
        this.r = r;
        koko = r.getKoko();
    }

    @Override
    public void run() {
        frame = new JFrame("Sanasampo");
        frame.setPreferredSize(new Dimension(600, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        
        //Menupalkki
        frame.setJMenuBar(new Menu());
        
        //Sanalista
        JList list = new JList(sanat.toArray());
        frame.add(new JScrollPane(list), BorderLayout.CENTER);
       
        //Kirjainruudukko
        frame.add(new RuudukkoPanel(koko, koko, r), BorderLayout.EAST);
        
    }

    public JFrame getFrame() {
        return frame;
    }
}
