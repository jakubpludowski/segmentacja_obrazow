
package segmentacja;


/**
 * Klasa implementująca obraz jako macierz elementów klasy piksel
 * 
 * 
 * @author pludy
 */
public class Obraz {
 
    protected int width;
    protected int height;
    protected Piksel[][] piksel;
    static private int ilosc_obrazow;
 
    /**
     * 
     * GETTERY
    */
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Piksel getPiksel(int w, int h) {return piksel[w][h];}
    /**
     * Setter
     * 
     * @param h wysokosc
     */
    public void setHeight(int h) {height = h;}
    /**
     * Setter
     * 
     * @param w szerokosc
     */
    public void setWidth(int w){ width = w;}
    /**
     * 
     * 
     * @param w wspolrzedna X
     * @param h wspołrzędna Y
     * @param x piksel
     */
    public void setPiksel(int w, int h, Piksel x) {piksel[w][h] = x;}
    
    
    //Konstuktor
    /**
     * Konstruktor domyślny
     * 
     * 
     */
    public Obraz()
    {
        this.width = 0;
        this.height = 0;
        piksel = new Piksel[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {piksel[i][j].setRGB(0,0,0);}
        ilosc_obrazow++;   
    }
    /**
     * Konstruktor pobierający wymiary obrazu
     * 
     * @param w szerokość
     * @param h wysokość
     */
    public Obraz(int w, int h)
    {
        this.width = w;
        this.height = h;
        piksel = new Piksel[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {piksel[i][j].setRGB(255,255,255);}
        ilosc_obrazow++;   
    }
    /**
     * Konstruktor pobierający wymiary obrazu oraz macierz pikseli
     * 
     * @param w szerokość
     * @param h wysokosć
     * @param piksele macierz pikseli
     */
    public Obraz(int w, int h, Piksel[][]piksele)
    {
        this.width = w;
        this.height = h;
        piksel = new Piksel[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {piksel[i][j] = piksele[i][j];}
        ilosc_obrazow++;   
    }
}
