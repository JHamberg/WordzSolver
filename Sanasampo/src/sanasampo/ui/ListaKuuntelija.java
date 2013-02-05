/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sanasampo.ui;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/** Tarkistaa, onko käyttäjä valinnut sanan {@link sanasampo.ui.Kayttoliittyma#list}-elementistä
 * ja päivittää {@link sanasampo.ui.RuudukkoPanel}-näkymän. <br> Korostaa sanan muodostamiseen 
 * tarvittavat kirjaimet. 
 */
public class ListaKuuntelija implements ListSelectionListener{
    
    /** Lista hakutuloksista*/ 
    private JList list;
    
    /** Valitun elementin tekstiarvo*/
    private String selection;
    
    /** Visuaalinen ruudukkoelementti */
    private RuudukkoPanel rp;
    
    /** Lista löytyneistä sanoista polkuineen */
    TreeMap<String, ArrayList<String>> osumat;
    
    /** Konstruktori joka alustaa listan, ruudukkopanelin ja hakutuloslistan 
     *@param l Lista hakutuloksista
     *@param p Ruudukko-elementti korostusta varten
     *@param h Hakutuloslista polkuineen
     */
    public ListaKuuntelija(JList l, RuudukkoPanel p, TreeMap<String, ArrayList<String>> h){
        list = l;
        rp = p;
        osumat = h;
    }
    
    
    /** Kutsuu valinnan muutoksen seurauksena 
     * {@link sanasampo.ui.RuudukkoPanel#korostaPolku(String, TreeMap)} elementtiä
     * valitun listaelementin tekstiarvolla*/
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        
       if (!lse.getValueIsAdjusting()) {  
           selection = (String) list.getSelectedValue();  //Haetaan valittu sana
           
           if(selection != null){
             rp.korostaPolku(selection, osumat);  //Korostetaan ruudukosta
           }
       }  
    }
    
    
}
