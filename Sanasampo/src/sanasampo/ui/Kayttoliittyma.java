/** Tiedon näyttäminen ja pääsy käyttäjän perustoimintoihin*/
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


/** Näyttää käyttäjän syötteen ruudukko-elementtinä ja siitä saadun tulosteen
 * interaktiivisena listana. Tarjoaa menu-valikon, josta suora pääsy perustoiminnallisuuksiin.
 */

public class Kayttoliittyma implements Runnable {
   
    
    /** Apuluokka sanalistan järjestyksen kääntämiseen
    * @see #luoKomponentit(Container)*/
    private Helper helper;
    /** Ruudukko joka sisällytetään RuudukkoPaneeliin*/
    private Ruudukko r;
    /** Yläluokka ohjelman uudelleenajoa varten */
    private Sampo s;
    
    /** Käyttöliittymän näkymä */
    private JFrame frame;
    /** Haun tulokset näyttävä lista */
    private JList list;
    /** Ruudukon alkiot painikkeina näyttävä elementti */
    private RuudukkoPanel rp;
    /** Listan kuuntelija ruudukon sanojen korostamiseen 
     * @see sanasampo.ui.RuudukkoPanel#korostaPolku(String, TreeMap)
     */
    private ListaKuuntelija lk;

    /** Lista haun antamista sanoista ruudukko-polkuineen */
    private TreeMap<String, ArrayList<String>> osumat;
    /** Lista haun antamista sanoista */
    private ArrayList<String> sanat;
    /** Näytettävän ruudukon koko */
    private int koko;
    
    /** Konstruktori joka saa parametrinaan ruudukon, listan haun tuloksista polkuineen), sekä yläluokan.
     * @param r Kirjaimet sisältävä ruudukko
     * @param m Myöhemmin järjestettävä lista haun löytämistä sanoista
     * @param h Polut sisältävä tietorakenne, jota käytetään kirjainten korostamisessa
     * @param sa Yläluokka ohjelman uudelleenajoa varten
     */
    public Kayttoliittyma(Ruudukko r, ArrayList<String> m, TreeMap<String, ArrayList<String>> h, Sampo sa) {
        sanat = m;
        osumat = h;
        this.r = r;
        s = sa;
        koko = r.getKoko();
        helper = new Helper();
    }

    
    /** Luo 600x500 kokoisen ikkunan, kutsuu komponentit luovaa metodia ja näyttää kehyksen
     * @see #luoKomponentit(java.awt.Container) 
     */
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

    /** Alustaa ylävalikon, sekä näkymät sanalistalle ja ruudukolle. Lisää kuuntelijat 
     * @param container AWT-komponentit sisältävä säiliö
     */
    private void luoKomponentit(Container container) {
        frame.setJMenuBar(new MenuValikko(s, this)); //Menupalkki
        
        helper.reverseOrder(sanat);
        list = new JList(sanat.toArray());
        frame.add(new JScrollPane(list), BorderLayout.CENTER); //Sanalista
        
        rp = new RuudukkoPanel(koko, koko, r); //Ruudukko
        
        addListeners();
        frame.add(rp, BorderLayout.EAST);
    }
    
    /** Lisää käyttöliittymälle kuuntelijat */
    private void addListeners(){
        lk = new ListaKuuntelija(list, rp, osumat);
        list.addListSelectionListener(lk);
    }
    
    public JList getList(){
        return list;
    }


    public JFrame getFrame() {
        return frame;
    }
    
    
    
}
