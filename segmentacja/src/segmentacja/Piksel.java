/**
 * Klasa piksel zawierająca 3 wartości składowych RGB, pole klasy Color oraz 3 wartości typu boolean wykorzystywane w algorytmie rozrostu regionów
 *
 * @author Jakub i Bartosz
 */
package segmentacja;

 import java.awt.Color;

public class Piksel
{
    protected int red;
    protected int green; 
    protected int blue;  
    protected int[] RGB; 
    protected Color sRGB;
    protected boolean czy_sprawdzone;
    protected boolean czy_dolaczyc;
    protected boolean czy_zrodlo;
    
    
    //GETTER
    public int[] getRGB(){return RGB;}
    public boolean getCzySprawdzone() {return czy_sprawdzone;}
    public boolean getCzyDolaczyc() {return czy_dolaczyc;}
    public boolean getCzyZrodlo() {return czy_zrodlo;}
    
    //SETTERY
    public void setRGB(int[] x) 
        {
            {red=x[0]; green=x[1]; blue = x[2];}
        }
    public void setRGB(int r,int g,int b) 
        {
            {red=r; green=g; blue = b;}
        }
    public void setCzySprawdzone(boolean b) {czy_sprawdzone = b;}
    public void setCzyDolaczyc(boolean b) {czy_dolaczyc = b;}
    public void setCzyZrodlo(boolean b) {czy_zrodlo = b;}
    
    //Konstruktor
    /**
     * konstruktor domyślnny ustawiający biały piksel
     * 
     */
    public Piksel()
    {
     red=0; green=0; blue = 0;
     sRGB = new Color(red, green, blue);
     RGB = new int[3]; RGB[0]=red; RGB[1] = green; RGB[2] = blue;
     czy_sprawdzone = false;
     czy_dolaczyc = false;
     czy_zrodlo = false;
    }
    /**
     * Konstruktor przyjmujący na wejściu 3 składowe
     * 
     * @param r składowa czerwona
     * @param g składowa zielona
     * @param b składowa niebieska
     */
    public Piksel(int r, int g, int b)
    {
     red=r; green=g; blue = b;
     sRGB = new Color(red, green, blue);
     RGB = new int[3]; RGB[0]=red; RGB[1] = green; RGB[2] = blue;
     czy_sprawdzone = false;
     czy_dolaczyc = false;
     czy_zrodlo = false;
    }
    /**
     * Konstruktor przyjmujący na wejściu tablicę RGB
     * 
     * @param x 3-elementowa tablica 
     */
    public Piksel(int[] x)
    {
     red=x[0]; green=x[1]; blue = x[2];
     sRGB = new Color(red, green, blue);
     RGB = new int[3]; RGB[0]=red; RGB[1] = green; RGB[2] = blue;
     czy_sprawdzone = false;
     czy_dolaczyc = false;
     czy_zrodlo = false;
    }
    
    
}