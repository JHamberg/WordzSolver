package sanasampo.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import sanasampo.data.Ruudukko;

// author JHamberg
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private String[][] ruudukko;
    private int koko;
    private ArrayList<String> sanat;

    public Kayttoliittyma(Ruudukko r, ArrayList<String> m) {
        sanat = m;
        ruudukko = r.getRuudukko();
        koko = r.getKoko();
    }

    @Override
    public void run() {
        frame = new JFrame("Sanasampo");
        frame.setPreferredSize(new Dimension(700, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JPanel pane1 = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        
        fileMenu.add("New grid");

        JList list = new JList(sanat.toArray());
        list.setLayout(null);
        // list.setPreferredSize(list.getMaximumSize());
        frame.add(pane1, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel(new GridLayout(koko, koko));
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                buttonPanel.add(new JButton(ruudukko[i][j])); //jokaisesta ruudukon alkiosta painike
            }
        }
        buttonPanel.setPreferredSize(new Dimension(450, 500));
        //    frame.setResizable( false );
        frame.add(buttonPanel, BorderLayout.EAST);
        frame.add(new JScrollPane(list), BorderLayout.CENTER);
        // container.add(buttonPanel);
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public String naytaRuudukkoDialog(){
        String mene = JOptionPane.showInputDialog(null, "Anna kirjaimet: ", "Ruudukon valinta", 1);
        return mene;
    }
}
