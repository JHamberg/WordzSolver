/** Tiedon näyttäminen ja pääsy käyttäjän perustoimintoihin*/
package sanasampo.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import sanasampo.Sampo;
import sanasampo.data.Ruudukko;
import sanasampo.data.Tiedosto;
import sanasampo.lang.DictionaryFilter;
import sanasampo.lang.FileEmptyException;
import sanasampo.logic.Helper;


/** Näyttää käyttäjän syötteen ruudukko-elementtinä ja siitä saadun tulosteen
 * interaktiivisena listana. Tarjoaa menu-valikon, josta suora pääsy perustoiminnallisuuksiin.
 */

public final class Kayttoliittyma implements Runnable {
   
    
    /** Apuluokka sanalistan järjestyksen kääntämiseen
    * @see #luoKomponentit(Container)*/
    private Helper helper;
    /** Ruudukko joka sisällytetään RuudukkoPaneeliin*/
    private Ruudukko r;
    /** Yläluokka ohjelman uudelleenajoa varten */
    private Sampo sampo;
    /** Tiedosto käyttöohjeiden näyttämiseen */
    private Tiedosto help; 
    /** Ohjelman avaaja käyttöohjeita varten */
    Desktop dt;
    /** Käyttöliittymän näkymä */
    private JFrame frame;
    /** Haun tulokset näyttävä lista */
    private JList list;
    /** Ruudukon alkiot painikkeina näyttävä elementti */
    private RuudukkoPanel rp;
    /** Tiedostonvalitsija sanakirjan vaihtamiseen */
    private JFileChooser fc; 
    
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
    /** Valittu sanakirja */
    Tiedosto valittu;
    /** Edellinen sanakirjan tiedostonimi */
    String edellinen;
    
    
    /** Konstruktori joka saa parametrinaan ruudukon, listan haun tuloksista polkuineen), sekä yläluokan.
     * @param r Kirjaimet sisältävä ruudukko
     * @param m Myöhemmin järjestettävä lista haun löytämistä sanoista
     * @param h Polut sisältävä tietorakenne, jota käytetään kirjainten korostamisessa
     * @param sa Yläluokka ohjelman uudelleenajoa varten
     */
    public Kayttoliittyma(Ruudukko r, ArrayList<String> m, TreeMap<String, ArrayList<String>> h, Sampo sa){
        sanat = m;
        osumat = h;
        this.r = r;
        sampo = sa;
        koko = r.getKoko();
        helper = new Helper();
        try{ valittu = new Tiedosto("dic\\dictionary");
        edellinen = valittu.lueListaan().get(0);
        } catch(Exception e){
            naytaUiError();
        }
    }

    
    /** Luo 600x500 kokoisen ikkunan, kutsuu komponentit luovaa metodia ja näyttää kehyksen
     * @see #luoKomponentit(java.awt.Container) 
     */
    @Override
    public void run() {
        frame = new JFrame("Sanasampo");
        frame.setPreferredSize(new Dimension(600, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            luoKomponentit(frame.getContentPane());
        } catch (Exception e) {}
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /** Alustaa ylävalikon, sekä näkymät sanalistalle ja ruudukolle. Lisää kuuntelijat 
     * @param container AWT-komponentit sisältävä säiliö
     */
    private void luoKomponentit(Container container) throws IOException, FileEmptyException {
        frame.setJMenuBar(new MenuValikko(sampo, this)); //Menupalkki
        
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
    
    /** Näyttää ohjelman tiedot erillisessä ikkunassa */ 
    public void naytaAbout(){
        JOptionPane.showMessageDialog(frame, "<html><h3>About program</h3>"
                + "<font size=-2>"
                + "Author: JHamberg<br>"
                + "Version: Beta Release 2 (BR2)<br>"
                + "Contact: JHamberg@Outlook.com<br><br>"
                + "Copyright (C) 2013"
                + "</font></html>");
    }
    
    /** Uudelleenkäynnistää haun esim. eri sanakirjalla */
    public void uudelleenKaynnista() throws FileNotFoundException, IOException, FileEmptyException{
        try{sampo.asenna();
        sampo.kaynnista(r.getKirjaimet());
        this.getFrame().setVisible(false);
        this.getFrame().dispose(); 
       }
            catch(Exception x){
                JOptionPane.showMessageDialog(frame, "Error: Selected dictionary is invalid!"
                        + "\nPlease try again.");
                vaihdaSanakirjaa();
                valittu.kirjoita(edellinen);
            }
    }
    
    /** Aloittaa uuden kierroksen */
     public void kaynnista(){
        try{sampo.asenna();
            sampo.kaynnista();
            this.getFrame().setVisible(false);}
            catch(Exception x){
                JOptionPane.showMessageDialog(frame, "Error: Dictionary not found!"
                        + "\nPlease use another dictionary.");
            }
            
    }
    
    /** Antaa pääluokalle kehotteen sulkea ohjelma */
    public void exit(){
        sampo.exit();
    }
    
    /** Avaa käyttöohjeet.pdf-tiedoston käyttäjän oletusohjelmassa*/
    public void naytaHelp() throws IOException{
        help = new Tiedosto("kayttoohjeet.pdf");
        help.avaa();
    }
    
    /** Avaa tiedostonäkymän ja antaa käyttäjälle mahdollisuuden vaihtaa sanakirjaa
     * kirjoittaa käytetyn sanakirjan nimen dictionary-tiedostoon */
    public void vaihdaSanakirjaa() throws FileNotFoundException, UnsupportedEncodingException, IOException, FileEmptyException{
        fc = new JFileChooser();
        
        //Filtterit
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new DictionaryFilter());
        fc.setMultiSelectionEnabled(false);
        
        //Avataava hakemisto
        fc.setCurrentDirectory(new File(".\\dic"));
        if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){ 
            valittu.kirjoita(fc.getSelectedFile().getName());
            uudelleenKaynnista();
        }
    }
    
    /** Näyttää virheilmoituksen jos PDF-tiedostoa ei pystytä avaamaan */
    public void helpFailure(){
        JOptionPane.showMessageDialog(frame, "<html><h3>Error!</h3>"
                + "File could not be opened. Instructions <br>"
                + "can be found from the installation location of this program.<br><br>"
                + "To open the file, you need a program,<br> that can read "
                + "files in PDF-format.</html>");
    }
    
    /** Palauttaa Sanakirjana käytetyn tiedoston nimen ilman tiedostopäätettä */
    public String getKieli() throws IOException, FileEmptyException {
        return valittu.lueListaan().get(0).replaceFirst("[.][^.]+$", "");
    }
    
    public void naytaUiError(){
         JOptionPane.showMessageDialog(frame, "Failed to create UI!\n"
                 + "Program will terminate.");
    }
    
    public JList getList(){
        return list;
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public String getKirjaimet(){
        return r.getKirjaimet();
    }
    
    public int getListanKoko(){
        return osumat.size();
    }

   
    
    
}
