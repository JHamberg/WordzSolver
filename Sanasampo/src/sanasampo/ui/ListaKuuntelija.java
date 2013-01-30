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

/**
 *
 * @author Jonatan
 */
public class ListaKuuntelija implements ListSelectionListener{
    private JList list;
    private String selection;
    private RuudukkoPanel rp;
    TreeMap<String, ArrayList<String>> hitlist;
    
    public ListaKuuntelija(JList l, RuudukkoPanel p, TreeMap<String, ArrayList<String>> h){
        list = l;
        rp = p;
        hitlist = h;
    }
    
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        
       if (!lse.getValueIsAdjusting()) {  
           selection = (String) list.getSelectedValue();  //Haetaan valittu sana
           
           if(selection != null){
             rp.highlight(selection, hitlist);  //Korostetaan ruudukosta
           }
       }  
    }
    
    
}
