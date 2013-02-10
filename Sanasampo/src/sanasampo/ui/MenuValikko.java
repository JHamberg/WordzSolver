package sanasampo.ui;

import java.io.IOException;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import sanasampo.Sampo;
import sanasampo.lang.FileEmptyException;
import sanasampo.logic.Helper;

/** Ylävalikko, josta pääsee käsiksi perustoiminnallisuuksiin kuten 
 * uudelleenajoon, sanakirjojen ja hakemiston muokkaamiseen jne. 
 */
public final class MenuValikko extends JMenuBar{
    
    /** Valikon elementit: File, Edit ja View. */
    private JMenu fileMenu, editMenu, viewMenu;
    
    /** Apuluokka tiedostonimen muotoiluun */
    Helper h;
    
    /** Käyttöliittymä kuuntelijoiden lisäykseen */
    private Kayttoliittyma gui;
        
    /** Luo valikkoelementit sekä niille valikot ja lisää päänäkymään
     * @param sa Yläluokka valikkoelementin kuuntelijalle, joka käsittelee uudelleenajoa
     * @param u Käyttöliittymän perustoiminnallisuudet kuuntelijalle 
     */
     public MenuValikko(Sampo sa, Kayttoliittyma u) throws IOException, FileEmptyException{
         gui = u;
         h = new Helper();
         
         fileMenu = new JMenu("File");
         editMenu = new JMenu("Edit");
         viewMenu = new JMenu("View");
         lisaaValikot();
         lisaaValinnat();
         lisaaMaara();
    }
     
     /** Lisää konstruktorissa alustetut valikot JMenuBar-elementtiin (this)*/
     private void lisaaValikot(){
          this.add(fileMenu);
          this.add(editMenu);
          this.add(viewMenu);
     }
     
    
     /** Lisää sanojen määrän valikkopalkin oikeaan laitaan */
     public void lisaaMaara() throws IOException, FileEmptyException{
         this.add(Box.createHorizontalGlue());
         this.add(new JLabel("Language: "+ h.capitalize(gui.getKieli())+"    |    "));
         this.add(new JLabel("Words: "+gui.getListanKoko()+ "   "));
     }
     
     /** Lisää valikkoelementille pudotusvalikossa näkyvän valinnan 
      * ja sille kuuntelijan käyttäjän syötteen perusteella
      * @param menu Valikkoelementti, jolle valinta lisätään
      * @param valinta Valintaa kuvaava teksti
      */
     private void lisaaValinta(JMenu menu, String valinta){
         menu.add(valinta).addActionListener(new ValikkoKuuntelija(gui));
     }
     
     
     /** Lisää valinnat ylävalikon elementeille */
     private void lisaaValinnat(){
         
         //File
         lisaaValinta(fileMenu, "New grid..");
         lisaaValinta(fileMenu, "Exit");
         
         //Edit
         lisaaValinta(editMenu, "Change dictionary..");
         
         //View
         lisaaValinta(viewMenu, "About");
         lisaaValinta(viewMenu, "Help");
     }
     
}
