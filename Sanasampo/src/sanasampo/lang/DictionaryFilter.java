package sanasampo.lang;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/** Hoitaa tiedostonäkymän suodatuksen hyväksyen vain
 * .dic-tyyppiset tiedostot */
public class DictionaryFilter extends FileFilter{

    /** Hyväksyy *.dic tiedostot */
    @Override
    public boolean accept(File file) {
       if (file.isDirectory()) return true; //Hyväksytään kansiot
       if(file.getName().toLowerCase().endsWith(".dic")) return true;
       return false;
    }

    /** Näkyy valintaikkunassa */
    @Override
    public String getDescription() {
        return "*.dic";
    }
    
}
