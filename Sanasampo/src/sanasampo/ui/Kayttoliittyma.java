/** Tiedon näyttäminen ja pääsy käyttäjän perustoimintoihin*/
package sanasampo.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import sanasampo.Sampo;
import sanasampo.data.Ruudukko;
import sanasampo.data.Tiedosto;
import sanasampo.lang.FileEmptyException;
import sanasampo.lang.TiedostoChooser;
import sanasampo.logic.Helper;


/** Näyttää käyttäjän syötteen ruudukko-elementtinä ja siitä saadun tulosteen
 * interaktiivisena listana. Tarjoaa menu-valikon, josta suora pääsy perustoiminnallisuuksiin.
 */

public final class Kayttoliittyma implements Runnable {
   
    
    /** Apuluokka sanalistan järjestyksen kääntämiseen
    * @see #luoKomponentit(Container)*/
    private Helper helper;
    /** Ruudukko joka sisällytetään RuudukkoPaneeliin*/
    private Ruudukko ruudukko;
    /** Yläluokka ohjelman uudelleenajoa varten */
    private Sampo sampo;
    /** Tiedosto käyttöohjeiden näyttämiseen */
    private Tiedosto help; 
    /** Ohjelman avaaja käyttöohjeita varten */
    Desktop desktop;
    /** Käyttöliittymän näkymä */
    private JFrame frame;
    /** Haun tulokset näyttävä lista */
    private JList list;
    /** Ruudukon alkiot painikkeina näyttävä elementti */
    private RuudukkoPanel ruudukkoPanel;
    /** Tiedostonvalitsija sanakirjan vaihtamiseen */
   private TiedostoChooser tiedostoValitsija;
    
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
        this.ruudukko = r;
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
        //Menupalkki
        frame.setJMenuBar(new MenuValikko(sampo, this)); 
       
        //Sanalista
        helper.kaannaJarjestys(sanat);
        list = new JList(sanat.toArray());
        frame.add(new JScrollPane(list), BorderLayout.CENTER); 
        
        //Ruudukko
        ruudukkoPanel = new RuudukkoPanel(koko, koko, ruudukko); 
        
      
        lisaaKuuntelijat();
        frame.add(ruudukkoPanel, BorderLayout.EAST);
    }
    
    
    /** Lisää käyttöliittymälle kuuntelijat */
    private void lisaaKuuntelijat(){
        lk = new ListaKuuntelija(list, ruudukkoPanel, osumat);
        list.addListSelectionListener(lk);
    }
    
    /** Näyttää ohjelman tiedot erillisessä ikkunassa */ 
    public void naytaAbout(){
        JOptionPane.showMessageDialog(frame, "<html><h3>About program</h3>"
                + "<font size=-2>"
                + "Author: JHamberg<br>"
                + "Version: Release Candidate 1 (RC1)<br>"
                + "Contact: JHamberg@Outlook.com<br><br>"
                + "Copyright (C) 2013"
                + "</font></html>");
    }
    
    /** Uudelleenkäynnistää haun esim. eri sanakirjalla */
    public void uudelleenKaynnista() throws FileNotFoundException, IOException, FileEmptyException{
        try{
            sampo.asenna();
            sampo.kaynnista(ruudukko.getKirjaimet());
            this.getFrame().setVisible(false);
            this.getFrame().dispose();}
        
        catch(Exception x){
            JOptionPane.showMessageDialog(frame, "Error: Selected dictionary is invalid!"
            + "\nPlease try again.");
            vaihdaSanakirjaa();
            valittu.kirjoita(edellinen);}
    }
    
    /** Aloittaa uuden kierroksen */
    public void kaynnista(){
        try{sampo.asenna();
            sampo.kaynnista();
            this.getFrame().setVisible(false);}
        
        catch(Exception x){
             JOptionPane.showMessageDialog(frame, "Error: Dictionary not found!"
             + "\nPlease use another dictionary.");}
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
    
    /** Avaa tiedostonäkymän ja antaa käyttäjälle mahdollisuuden vaihtaa sanakirjaa.
     * Kirjoittaa käytetyn sanakirjan nimen dictionary-tiedostoon */
    public void vaihdaSanakirjaa() throws FileNotFoundException, UnsupportedEncodingException, IOException, FileEmptyException{
        tiedostoValitsija = new TiedostoChooser(frame);
        if(tiedostoValitsija.kysy()){
            valittu.kirjoita(tiedostoValitsija.getValitunNimi());
            uudelleenKaynnista();
        }
        
    }
    
    /** Näyttää virheilmoituksen jos PDF-tiedostoa ei pystytä avaamaan */
    public void helpVirhe(){
        JOptionPane.showMessageDialog(frame, "<html><h3>Error!</h3>"
                + "File could not be opened. Instructions can be found "
                + "<br>from the installation location of this program.<br><br>"
                + "To open the instructions, you need a program which <br> "
                + "can read PDF-files. </html>");
    }
    
    /** Palauttaa Sanakirjana käytetyn tiedoston nimen ilman tiedostopäätettä */
    public String getKieli() throws IOException, FileEmptyException {
        return valittu.lueListaan().get(0).replaceFirst("[.][^.]+$", "");
    }
    
    public void naytaUiError(){
         JOptionPane.showMessageDialog(frame, "Failed to create the user interface!\n"
                 + "Program will now terminate.");
         exit();
    }
    
    public JList getList(){
        return list;
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public String getKirjaimet(){
        return ruudukko.getKirjaimet();
    }
    
    public int getListanKoko(){
        return osumat.size();
    }

   
    
    
}
