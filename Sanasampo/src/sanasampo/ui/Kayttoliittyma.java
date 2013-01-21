package sanasampo.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import sanasampo.data.Ruudukko;


// author JHamberg

public class Kayttoliittyma implements Runnable{
    private JFrame frame;
    private String[][] ruudukko;
    private int koko;

    public Kayttoliittyma(Ruudukko r){
       ruudukko = r.getRuudukko();
       koko = r.getKoko();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Sanasampo");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
     private void luoKomponentit(Container container) {
         JPanel buttonPanel = new JPanel(new GridLayout(koko,koko));
         for(int i=0;i<koko;i++)
             {
             for(int j=0;j<koko;j++)
                {
                  buttonPanel.add(new JButton(ruudukko[i][j])); //jokaisesta ruudukon alkiosta painike
                }
              } 
         buttonPanel.setPreferredSize(new Dimension(500, 500));
         container.add(buttonPanel);
    }

    public JFrame getFrame() {
        return frame;
    }
}
