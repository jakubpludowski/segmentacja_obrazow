
package segmentacja;

import java.awt.Color;
/**
 * Klasa dziedzicząca po klasie Obraz implementująca obraz w skali szarości
 * 
 * 
 * @author pludy
 */
public class Obraz_SS extends Obraz{
    
    
    private Piksel_SS[][] piksel;
    static private int ilosc_obrazow_SS;
 
    //GETTERY
    public Piksel_SS getPiksel_SS(int w, int h) 
        {
        if(w>=0 && w<width && h>=0 && h<height)
            {return piksel[w][h];}
        else
        {throw new IllegalArgumentException("poza obszarem");}
        }
    //SETTERY
    public void setPiksel_SS(int w, int h, Piksel_SS x) {piksel[w][h] = x;}
    
    
    //Konstuktor
    /**
     * Konstruktor domyślny
     * 
     */
    public Obraz_SS()
    {
        this.width = 0;
        this.height = 0;
        piksel = new Piksel_SS[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {piksel[i][j].setRGB(0,0,0);}
        ilosc_obrazow_SS++;   
    }
    /**
     * Konstruktor pobierający wymiary obrazu
     * 
     * @param w szerokosc
     * @param h wysokosc
     */
    public Obraz_SS(int w, int h)
    {
        this.width = w;
        this.height = h;
        piksel = new Piksel_SS[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {piksel[i][j].setRGB(0,0,0);}
        ilosc_obrazow_SS++;   
    }
    /**
     * Konstruktor pobierający wymiary obrazu oraz macierz pikseli
     * 
     * @param w szerokość
     * @param h wysokość
     * @param piksele macierz elementów typu Piksel_SS
     */
    public Obraz_SS(int w, int h, Piksel_SS[][]piksele)
    {
        this.width = w;
        this.height = h;
        piksel = new Piksel_SS[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {piksel[i][j] = piksele[i][j];}
        ilosc_obrazow_SS++;   
    }
    /**
     * Metoda sprawdzajaca czy podane wspolrzedne dwóch pikseli mieszczą się w obrazie
     * 
     * @param w1 współrzędna X pierwszego piksela
     * @param h1 współrzędna Y pierwszego piksela
     * @param w2 współrzędna X drugiego piksela
     * @param h2 współrzędna Y drugiego piksela
     * @return wartość logiczna
     */
    public boolean czyDwaPikseleSaWObrazie (int w1,int h1,int w2,int h2)
    {return (w1>=0 && w2>=0 && w1<width && w2<width && h1>=0 && h2>=0 && h1<height && h2<height);}
    
    /**
     * Metoda porównująca wartość skali szarości dwóch pikseli i sprawdzająca czy ich różnica mieści się w zadanej tolerancji
     * 
     * Jeśli różnica jest mniejsza od tolerancji to piksel zostaje oznaczony jako sprawdzony i zostaje oznaczony jako piksel ktroy ma zostac dolaczony do nowego obrazu. Jeśli różnica jest za duża piksel zostaje oznaczony jako sprawdzony.
     * 
     * 
     * @param w1 współrzędna X pierwszego piksela
     * @param h1 współrzędna Y pierwszego piksela
     * @param w2 współrzędna X drugiego piksela
     * @param h2 współrzędna Y drugiego piksela
     * @param tolerancja zadana tolerancja porównania
     */
    public void porownajDwaPiksele(int w1,int h1,int w2,int h2, int[] tolerancja)
    {
        if(czyDwaPikseleSaWObrazie(w1, h1, w2, h2))
        {
            if (piksel[w2][h2].getSS()>=tolerancja[0]&&piksel[w2][h2].getSS()<=tolerancja[1])
            //if (piksel[w2][h2].getSS()==(piksel[w1][h1].getSS()))
            {
                piksel[w2][h2].setCzyDolaczyc(true);
                piksel[w2][h2].setCzySprawdzone(true);   
                //System.out.println(piksel[w2][h2].getssRGB());
            }
            else {piksel[w2][h2].setCzySprawdzone(true);}
        }
        else
        { System.out.println("poza obszarem");}
        
    }
}