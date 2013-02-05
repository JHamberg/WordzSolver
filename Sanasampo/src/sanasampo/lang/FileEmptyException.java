/** Poikkeusten käsittely ja vertailuoliot */
package sanasampo.lang;


/** Poikkeusten käsittely tyhjiä tiedostoja varten */
public class FileEmptyException extends Exception {

    /** Tulostaa parametrina saadun virheilmoituksen*/
    public FileEmptyException(String message) {
        super(message);
    }
}
