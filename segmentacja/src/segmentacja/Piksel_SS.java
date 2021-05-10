
package segmentacja;

import java.awt.Color;

/**
 * Klasa opisująca piksel w skali szarości posiadająca parametr liczbowy od 0 do 255 dziedzicząca po klasie Piksel
 * 
 * 
 */
public class Piksel_SS extends Piksel
{
    protected int SS;
    protected Color ssRGB;
    

    
    //GETTER
    /**
     * Getter wartości skali szarości
     * 
     * @return wartość skali szarości
     */
    public int getSS(){return SS;}
    /**
     * Getter wartości skali szarości w zapisie sRGB
     * 
     * @return wartośc skali szarości
     */
    public int getssRGB(){return ssRGB.getRGB();}

    
    //SETTERY
    /**
     * Setter skali szarości 
     * @param s wartość skali szarości od 0 do 255
     */
    public void setSS(int s) {SS=s;}
    /**
     * Setter skali szarości
     * @param r składowa czerwona
     * @param g składowa zielona
     * @param b składowa niebieska
     */
    public void setssRGB(int r, int g, int b)
    {
               Color newColor = new Color(r,g,b);
               ssRGB=newColor;
    }

    
    //Konstruktor
    /**
     * Konstruktor piksela
     * 
     * @param r składowa czerwona
     * @param g składowa zielona
     * @param b skłądowa niebieska
     */
    public Piksel_SS(int r, int g, int b)
    {

     SS = (int)(0.299*r + 0.587*g + 0.114*b);
     ssRGB = new Color(r,g,b);
               int red = (int)(ssRGB.getRed() * 0.299);
               int green = (int)(ssRGB.getGreen() * 0.587);
               int blue = (int)(ssRGB.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);

               ssRGB=newColor;
     czy_sprawdzone = false;
     czy_dolaczyc = false;
     czy_zrodlo = false;
     //System.out.println(SS);
    }
    /**
     * Konstruktor przyjmujący 3-elementować tablicę RGB
     * 
     * @param x 3 elementowa tablica RGB
     */
    public Piksel_SS(int[] x)
    {
     SS = (int)(0.299*x[0] + 0.587*x[1] + 0.114*x[2]);
     ssRGB = new Color(x[0],x[1],x[2]);
               int red = (int)(ssRGB.getRed() * 0.299);
               int green = (int)(ssRGB.getGreen() * 0.587);
               int blue = (int)(ssRGB.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);

               ssRGB=newColor;
     czy_sprawdzone = false;
     czy_dolaczyc = false;
     czy_zrodlo = false;
     //System.out.println(SS);
    }
    
    
}
