package sanasampo.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import sanasampo.Sampo;
import sanasampo.data.Ruudukko;
import sanasampo.logic.Helper;


public class Kayttoliittyma implements Runnable {
   
    //Luokat
    private Helper make;
    private Ruudukko r;
    private Sampo s;
    
    //GUI-elementit
    private JFrame frame;
    private JList list;
    private RuudukkoPanel rp;
    private ListaKuuntelija lk;

    //Muut
    private TreeMap<String, ArrayList<String>> hitlist;
    private ArrayList<String> sanat;
    private int koko;
    
    public Kayttoliittyma(Ruudukko r, ArrayList<String> m, TreeMap<String, ArrayList<String>> h, Sampo sa) {
        sanat = m;
        hitlist = h;
        this.r = r;
        s = sa;
        koko = r.getKoko();
        make = new Helper();
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
        frame.setJMenuBar(new MenuValikko(s, this)); //Menupalkki
        
        make.reverseOrder(sanat);
        list = new JList(sanat.toArray());
        frame.add(new JScrollPane(list), BorderLayout.CENTER); //Sanalista
        
        rp = new RuudukkoPanel(koko, koko, r); //Ruudukko
        
        addListeners();
        frame.add(rp, BorderLayout.EAST);
    }
    
    private void addListeners(){
        lk = new ListaKuuntelija(list, rp, hitlist);
        list.addListSelectionListener(lk);
    }
    
    public JList getList(){
        return list;
    }


    public JFrame getFrame() {
        return frame;
    }
    
    
    
}
