package sanasampo.logic;

import java.awt.List;
import java.util.ArrayList;
import sanasampo.data.Ruudukko;

public class Syvahaku {

    //Kesken
    private String[][] grid;
    private int N;
    private String s;
    private ArrayList<String> osumat;
    private ArrayList<String> polku;

    public Syvahaku(Ruudukko ruudukko) {
        grid = ruudukko.getRuudukko();
        N = ruudukko.getKoko();
        osumat = new ArrayList<String>();
    }

    public boolean suorita(String s) {
        this.s = s;
        ArrayList<String> edelliset = new ArrayList<String>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j].equals(Character.toString(s.charAt(0)))) {
                    if (ttN(i, j, 0, edelliset)) { //tN = tarkistaNaapurit
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public ArrayList<String> palautaOsumat() {
        return osumat;
    }

    public static ArrayList<String> cloneList(ArrayList<String> list) {
        ArrayList<String> clonedList = new ArrayList<String>();
        for (String s : list) {
            clonedList.add(s);
        }
        return clonedList;
    }
 
     private boolean ttN(int x, int y, int sijainti, ArrayList<String> edelliset) {
        if(sijainti >= s.length()){
            polku = cloneList(edelliset);
            return true;
        }
        if (x >= N || y >= N || x < 0 || y < 0) return false; 
        if (gridEquals(x, y, sijainti) && notAccessed(x,y,edelliset)){
           ArrayList<String> ed = cloneList(edelliset);
           ed.add(yhdista(x, y));
            
        if(ttN(x+1, y+1, sijainti+1, ed)||
        ttN(x+1, y, sijainti+1, ed)||
        ttN(x+1, y-1, sijainti+1, ed)||
        ttN(x-1, y+1, sijainti+1, ed)||
        ttN(x-1, y, sijainti+1, ed)||
        ttN(x-1, y-1, sijainti+1, ed)||
        ttN(x, y+1, sijainti+1, ed)||
        ttN(x, y-1, sijainti+1, ed)){return true;}
        
        }
        return false;
    } 
   
    private boolean gridEquals(int x, int y, int sijainti){
        if(grid[x][y].equals(Character.toString(this.s.charAt(sijainti)))){
            return true;
        }
        else {return false;}
    }
    
    private boolean notAccessed(int x, int y, ArrayList<String> e){
        for(String k : e){
            if(k.equals(yhdista(x,y))){
                return false;
            }
        }
        return true;
    }
    
    private String yhdista(int x, int y){
        return Integer.toString(x).concat(Integer.toString(y));
    }
    
    public ArrayList<String> getPolku(){
        return polku;
    }
    
}
