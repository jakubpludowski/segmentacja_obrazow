package segmentacja;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException; 
import static java.lang.Math.pow;
import java.util.Random;
import javax.imageio.ImageIO;
import java.util.Scanner;
import javax.swing.*;

import org.dcm4che2.io.*;
import org.dcm4che2.data.*;
import org.dcm4che2.util.*;

/**
 * Klasa implementująca GUI w postaci zestawu metod, modelu danych, widoku użytkownika oraz odpowiednich kontrolerów
 * 
 * 
 * @author pludy
 */
public class Segmentacja extends JFrame implements ActionListener
{   
    private Obraz obraz_wejscie=wczytajObraz("java.jpg");
    private Obraz_SS obraz_skala_szarosci = zmienNaSkaleSzarosci(obraz_wejscie);
    private Obraz_SS obraz_skala_szarosci_kopia = zmienNaSkaleSzarosci(obraz_wejscie);
    private Obraz_SS obraz_przetworzony = null;
    ObrazPanel obraz1 = new ObrazPanel(obraz_wejscie);
    ObrazPanel obraz2 = new ObrazPanel(obraz_skala_szarosci);
    public JPanel leftDownPanel;
    public JPanel downPanel;
    
    private int tolerancja;
    private int[] przedzial_tolerancji = new int[2];
    private int[] wspolrzedne = new int[2];
    private JTextField	wspolrzednaX=null;
    private JTextField	wspolrzednaY=null;
    private JTextField	tolerancjaPole=null;
    private JButton 	Guzik1=null;
    private JButton 	Guzik2=null;
    private JButton 	Guzik3=null;
    private JButton 	Guzik4=null;
    private JButton 	Guzik5=null;
    private JButton 	Guzik6=null;
    private JButton 	Guzik7=null;
    
    private JLabel      wspolrzedne_label = null;
    
    public String Sciezka = null;
    
    /**
     * Klasa main wykonująca program
     * 
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {        
        Segmentacja app = new Segmentacja();
            app.setVisible(true);
    }
    /**
     * Konstruktor programu tworzący GUI
     * 
     * 
     */
    public Segmentacja()
    {
      this.doGUI();
    }
    /**
     * Metoda mająca za zadanie stworzyć działający interfejs graficzny oraz przyporządkować od niego działanie kontrolerów
     * 
     * 
     */   
    public void doGUI()
    {
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //this.setSize(1920, 1080);
        this.setLocation(0, 0);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        this.getContentPane().add(mainPanel);
        
        JPanel upPanel = new JPanel();
        upPanel.setLayout(new FlowLayout());
        upPanel.setBorder(BorderFactory.createTitledBorder("Ustawienia"));
        JPanel leftPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout());
        JPanel rightPanel = new JPanel();
        upPanel.add(leftPanel);
        upPanel.add(middlePanel);
        upPanel.add(rightPanel);
        
        downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout());
        downPanel.setBorder(BorderFactory.createTitledBorder("Obrazy"));
        downPanel.setVisible(false);
        leftDownPanel = new JPanel();
        JPanel middleDownPanel = new JPanel();
        middleDownPanel.setBorder(BorderFactory.createTitledBorder("Przekształcenia"));
        middleDownPanel.setLayout(new GridLayout(2,1));
        JPanel rightDownPanel = new JPanel();
        downPanel.add(leftDownPanel);
        downPanel.add(middleDownPanel);
        downPanel.add(rightDownPanel);
        mainPanel.add(upPanel);
        mainPanel.add(downPanel);
        leftDownPanel.add(obraz1);
        //rightDownPanel.add(obraz2);
        this.pack();
        
        JPanel leftpanel = new JPanel();
        
//wczytywanie obrazu


        this.Guzik6 = new JButton("Wczytaj Obraz"); 
        this.Guzik6.setBounds(10, 80, 80, 25);       
        leftPanel.add(this.Guzik6);
        this.Guzik6.addActionListener(this); 
        Guzik6.setVisible(true);
        
// wybierz piksel       
        this.Guzik3 = new JButton("Określ Wspolrzedne Myszką"); 
        this.Guzik3.setBounds(10, 80, 80, 25);       
        leftPanel.add(this.Guzik3);
        this.Guzik3.addActionListener(this); 
        Guzik3.setVisible(true);


   // wpisywanie wspolrzednych     
        JLabel labelX = new JLabel("Wspolrzedna X");
        labelX.setBounds(10, 25, 80, 25);
        middlePanel.add(labelX);
        
        this.wspolrzednaX=new JTextField();
        wspolrzednaX.setColumns(5);       
        middlePanel.add(this.wspolrzednaX);
        
        JLabel labelY = new JLabel("Wspolrzedna Y");
        labelY.setBounds(10, 25, 80, 25);
        middlePanel.add(labelY);
        
        this.wspolrzednaY=new JTextField();
        wspolrzednaY.setColumns(5);        
        middlePanel.add(this.wspolrzednaY);

        
        this.Guzik1 = new JButton("Zapisz wspolrzedne"); 
        this.Guzik1.setBounds(10, 80, 80, 25);       
        middlePanel.add(this.Guzik1);
        this.Guzik1.addActionListener(this);   
                
        this.Guzik1 = new JButton("ROZROST\n REGIONÓW"); 
        //this.Guzik1.setBounds(10, 80, 80, 25);  
        Guzik1.setPreferredSize(new Dimension(170, 80));
        middleDownPanel.add(this.Guzik1);
        this.Guzik1.addActionListener(this); 
        
        this.Guzik1 = new JButton("KOLOROWY SPLIT"); 
        //this.Guzik1.setBounds(10, 80, 80, 25);  
        Guzik1.setPreferredSize(new Dimension(170, 80));
        middleDownPanel.add(this.Guzik1);
        this.Guzik1.addActionListener(this); 
        
        this.Guzik1 = new JButton("SPLIT & MERGE"); 
        //this.Guzik1.setBounds(10, 80, 80, 25);  
        Guzik1.setPreferredSize(new Dimension(170, 80));
        middleDownPanel.add(this.Guzik1);
        this.Guzik1.addActionListener(this); 
        
        this.Guzik1 = new JButton("ZAPISZ OBRAZ"); 
        //this.Guzik1.setBounds(10, 80, 80, 25);  
        Guzik1.setPreferredSize(new Dimension(170, 80));
        middleDownPanel.add(this.Guzik1);
        this.Guzik1.addActionListener(this);
        
        JLabel labeltol = new JLabel("Określ Tolerancję");
        labeltol.setBounds(10, 25, 80, 25);
        rightPanel.add(labeltol);
        
        this.tolerancjaPole=new JTextField();
        tolerancjaPole.setColumns(5);       
        rightPanel.add(this.tolerancjaPole);
        
        this.Guzik2 = new JButton("Zapisz Tolerancję"); 
        this.Guzik2.setBounds(10, 80, 80, 25);       
        rightPanel.add(this.Guzik2);
        this.Guzik2.addActionListener(this);
        

        
         
         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    /**
     * Metoda wyświetlająca obiekt typu obraz w nowym obiekcie typu JFrame
     * 
     * @param x obraz w skali szarości
     */
    public static void wyswietlObraz(Obraz_SS x)
    {
               SwingUtilities.invokeLater(new Runnable() {
       		@Override
    		public void run() 
                {
    			new ObrazFrame(x);
    		}
    	});
        
    }
    /**
     * Kontroler obsługujący wydarzenia spowodowane naciśnięciem konkretnych guzików
     * 
     * 
     * @param e obiekt typu event
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getActionCommand().equals("Zapisz wspolrzedne"))
    	{
    		this.wspolrzedne[0]= Integer.parseInt(wspolrzednaX.getText());
                this.wspolrzedne[1]= Integer.parseInt(wspolrzednaY.getText());
                System.out.println(wspolrzedne[0] + " " + wspolrzedne[1]);
    	}
        else if (e.getActionCommand().equals("Wczytaj Obraz"))
    	{
            JFileChooser filechoser = new JFileChooser();
            if (filechoser.showOpenDialog(null)!=JFileChooser.CANCEL_OPTION)
            {
                this.Sciezka = filechoser.getSelectedFile().getPath();
            }

            System.out.println(this.Sciezka);


            obraz_wejscie = wczytajObraz(Sciezka);
            obraz_skala_szarosci = zmienNaSkaleSzarosci(obraz_wejscie);
            
            leftDownPanel.removeAll();
            obraz1 = new ObrazPanel(obraz_wejscie);
            obraz2 = new ObrazPanel(obraz_skala_szarosci);
            leftDownPanel.add(obraz1);
            obraz1.setVisible(true);
            leftDownPanel.setVisible(true);
            this.repaint();
            

            if(downPanel.isVisible()==false)    {downPanel.setVisible(true);}
            Guzik6.setVisible(false);

                    
    	}
        else if (e.getActionCommand().equals("ROZROST\n REGIONÓW"))
    	{
            obraz_skala_szarosci=zmienNaSkaleSzarosci(obraz_wejscie);
            wybierzPikselPoczatkowyRecznie(this.obraz_skala_szarosci, wspolrzedne[0], wspolrzedne[1]);
            przedzial_tolerancji=OkreslTolerancje(obraz_skala_szarosci, tolerancja);
            rozrostRegionow(obraz_skala_szarosci, przedzial_tolerancji);
            regionNegatyw(obraz_skala_szarosci);
            obraz2 = new ObrazPanel(obraz_skala_szarosci);
            
            
            repaint();
            
            new ObrazFrame(obraz_skala_szarosci, "s");
        }
        else if (e.getActionCommand().equals("SPLIT & MERGE"))
    	{
            int w = this.obraz_skala_szarosci.getWidth();
            int h = this.obraz_skala_szarosci.getHeight();
            splitmerge(this.obraz_skala_szarosci,0,0,w/2-1,h/2-1,1,tolerancja); // sprawdzenie LG
            splitmerge(this.obraz_skala_szarosci,w/2,0,w-1,h/2-1,1,tolerancja); // sprawdzenie PG
            splitmerge(this.obraz_skala_szarosci,0,h/2,w/2-1,h-1,1,tolerancja); // sprawdzenie LD
            splitmerge(this.obraz_skala_szarosci,w/2,h/2,w-1,h-1,1,tolerancja); // sprawdzenie PD
 
            
            leftDownPanel.removeAll();
            obraz2 = new ObrazPanel(obraz_skala_szarosci);
            ObrazFrame fram = new ObrazFrame(obraz2);
            fram.setVisible(false);
            obraz2.setVisible(true);
            
            obraz1.setVisible(true);
            leftDownPanel.add(obraz2);
            leftDownPanel.setVisible(true);
            this.repaint();
            

                
                
           
        
             
                    
    	} 
        else if (e.getActionCommand().equals("KOLOROWY SPLIT"))
    	{
            int w = this.obraz_skala_szarosci.getWidth();
            int h = this.obraz_skala_szarosci.getHeight();
            split(this.obraz_skala_szarosci,0,0,w/2-1,h/2-1,1,tolerancja); // sprawdzenie LG
            split(this.obraz_skala_szarosci,w/2,0,w-1,h/2-1,1,tolerancja); // sprawdzenie PG
            split(this.obraz_skala_szarosci,0,h/2,w/2-1,h-1,1,tolerancja); // sprawdzenie LD
            split(this.obraz_skala_szarosci,w/2,h/2,w-1,h-1,1,tolerancja); // sprawdzenie PD
            wyswietlObraz(this.obraz_skala_szarosci);
            
            obraz2 = new ObrazPanel(obraz_skala_szarosci);
            
            repaint();
        
             
                    
    	} 
        else if (e.getActionCommand().equals("ZAPISZ OBRAZ"))
    	{
            zapiszObraz(obraz_skala_szarosci);
        
                               
    	}
        
        else if (e.getActionCommand().equals("Zapisz Tolerancję"))
    	{
            this.tolerancja= Integer.parseInt(tolerancjaPole.getText());           
    	} 
        else if (e.getActionCommand().equals("Określ Wspolrzedne Myszką"))
    	{
            
            ObrazPanel podglad = new ObrazPanel(zmienNaSkaleSzarosci(obraz_wejscie), "def");
            new ObrazFrame(podglad);
            this.wspolrzedne[0]= podglad.wybrana_szerokosc;
            this.wspolrzedne[1]= podglad.wybrana_wysokosc;
            System.out.println("wybrano " + podglad.wybrana_szerokosc + " " + podglad.wybrana_wysokosc);
            System.out.println("zapisano " + wspolrzedne[0] + " " + wspolrzedne[1]);
            this.wspolrzedne_label = new JLabel("Wspolrzedne: " + wspolrzedne[0] + ", " + wspolrzedne[1]); 
            this.add(wspolrzedne_label);
            this.repaint();
    	}

    }
    
    /**
     * Metoda wczytująca obraz zapisany na dysku i przekształcajaca go w obiekt typu Obraz
     * 
     * 
     * @param nazwa ścieżka do zapisanego obrazu
     * @return nowy obiekt typu Obraz 
     */
    public static Obraz wczytajObraz(String nazwa)
    {
    ObrazPanel obrazek = new ObrazPanel(nazwa);
    int width = obrazek.image.getWidth();
    int height = obrazek.image.getHeight();
    Piksel piksele[][] = new Piksel[width][height];
    for(int i=0; i<width; i++)
         for(int j=0; j<height; j++)
         {
         Color k = new Color(obrazek.image.getRGB(i, j));
         piksele[i][j] = new Piksel(k.getRed(), k.getGreen(), k.getBlue());
         }
    
    Obraz nowy_obraz = new Obraz(width, height, piksele);
    return nowy_obraz;
    }
    
    /**
     * Metoda zmieniająca obraz kolorowy na obraz w skali szarości
     * 
     * 
     * @param x obiekt typu Obraz
     * @return obiekt typu Obraz_SS
     */
    public static Obraz_SS zmienNaSkaleSzarosci(Obraz x)
    {
    int w,h;
    w = x.width;
    h = x.height;
    Piksel_SS[][] piksele = new Piksel_SS[w][h];
    for(int i =0; i<w; i++)
        for(int j=0; j<h; j++)
        {
           Piksel_SS nowy_piksel_SS = new Piksel_SS(x.piksel[i][j].getRGB()); 
           piksele[i][j] = nowy_piksel_SS;       
        }
    
    Obraz_SS nowy_obraz_SS = new Obraz_SS(w,h,piksele);
    return nowy_obraz_SS;
    }
   
    /**
     * Metoda zapisująca obraz kolorowy na dysku jako plik o nazwie "zapisany_obraz.jpg"
     * 
     * 
     * @param x obiekt typu Obraz
     */
    public static void zapiszObraz(Obraz x)
    {
    ObrazPanel nowy_obraz = new ObrazPanel(x);
    File obrazek = new File("zapisany_obraz.jpg");
               try
                {
                ImageIO.write(nowy_obraz.image, "jpg", obrazek);
                } 
                catch (IOException e) 
                    {
                    System.err.println("Blad zapisu obrazka");
                    e.printStackTrace();
                    }
    
    }
    
    /**
     * Metoda zapisująca obraz w skali szarości na dysku jako plik o nazwie "zapisany_obraz.jpg"
     * 
     * 
     * @param x obiekt typu Obraz_SS
     */
    public static void zapiszObraz(Obraz_SS x)
    {
    ObrazPanel nowy_obraz = new ObrazPanel(x);
    File obrazek = new File("zapisany_obraz.jpg");
               try
                {
                ImageIO.write(nowy_obraz.image, "jpg", obrazek);
                } 
                catch (IOException e) 
                    {
                    System.err.println("Blad zapisu obrazka");
                    e.printStackTrace();
                    }
    
    }
     
    /**
     * Nieużywana metoda wybierająca jeden piksel z obrazu i zwracająca jego współrzędne
     * 
     * 
     * @param x obraz w skali szarości
     * @return tablica współrzędnych
     */
    public static int[] wczytajWsporzedne(Obraz_SS x)
    {
    int[] wspolrzedne = new int[2];
        System.out.println("przed");
    ObrazPanel xy = new ObrazPanel(x);
    System.out.println("po");
    new ObrazFrame(xy);
    Scanner scan = new Scanner(System.in);
    System.out.println("Czy już wybrałeś wspolrzedne?   y/n");
    String k = scan.nextLine();
    wspolrzedne[0] =  xy.wybrana_szerokosc;
    System.out.println(wspolrzedne[0]);
    wspolrzedne[1] =  xy.wybrana_wysokosc;
    System.out.println(wspolrzedne[1]);
    return wspolrzedne;
    }
    
    /**
     * Nieużywana metoda wykorzystywana w rozroście regionów, która określa pierwszy piksel z którego ma się rozpocząć segmentacja
     * 
     * @param x obraz
     * @return wartość logiczna
     */
    public static boolean wybierzPikselPoczatkowy(Obraz_SS x)
    {
        int[] wspolrzedne = new int[2];
        wspolrzedne = wczytajWsporzedne(x);
        if(wspolrzedne[0]>=0 && wspolrzedne[0]<x.getWidth() && wspolrzedne[1]>=0 && wspolrzedne[1]<x.getHeight())
            {
            x.getPiksel_SS(wspolrzedne[0],wspolrzedne[1]).setCzyDolaczyc(true);
            x.getPiksel_SS(wspolrzedne[0],wspolrzedne[1]).setCzySprawdzone(true);
            return true;
            }
        else
        {   return false;}
    }
    /**
     * Metoda wykorzystywana w rozroście regionów, która określa pierwszy piksel z którego ma się rozpocząć segmentacja
     * 
     * 
     * @param x analizowany obraz
     * @param szer współrzędna X początkowego piksela
     * @param wys współrzędna Y początkowego piksela
     * @return  wartośc logiczna
     */
    public static boolean wybierzPikselPoczatkowyRecznie(Obraz_SS x, int szer, int wys)
    {
        int[] wspolrzedne = new int[2]; wspolrzedne[0] = szer; wspolrzedne[1] = wys;
        if(wspolrzedne[0]>=0 && wspolrzedne[0]<x.getWidth() && wspolrzedne[1]>=0 && wspolrzedne[1]<x.getHeight())
            {
            x.getPiksel_SS(wspolrzedne[0],wspolrzedne[1]).setCzyDolaczyc(true);
            x.getPiksel_SS(wspolrzedne[0],wspolrzedne[1]).setCzySprawdzone(true);
            return true;
            }
        else
        {   return false;}
    }
    /**
     * Metoda określająca tolerancję segmentacji metodą rozrostu regionów
     * 
     * 
     * @param x obraz
     * @param tolerancja wartość tolerancji
     * @return przedział wartości skali szarości mieszczących się w zakresie tolerancji
     */
    public static int[] OkreslTolerancje(Obraz_SS x, int tolerancja)
    {
        int kolor = 0;
        int[] przedzial = new int[2];
        for(int i=0; i<x.getWidth(); i++)
              {
                  for (int j=0;j<x.getHeight(); j++)
                  {
                      if(x.getPiksel_SS(i, j).czy_dolaczyc == true&&x.getPiksel_SS(i, j).czy_sprawdzone == true &&x.getPiksel_SS(i, j).czy_zrodlo == false)
                            { kolor = x.getPiksel_SS(i, j).getSS();}
                  }
              }
        przedzial[0]= kolor -tolerancja; if (przedzial[0] <0) {przedzial[0] = 0;} if (przedzial[0] >255) {przedzial[0] = 255;}
        przedzial[1]= kolor +tolerancja; if (przedzial[1] <0) {przedzial[1] = 0;} if (przedzial[1] >255) {przedzial[1] = 255;}
        return przedzial;
    }
    
    /**
     * Algorytm powodujący rozrost regionów
     * 
     * 
     * @param x analizowany Obraz
     * @param przedzial przedział tolerancji zwracany przez metodę OkreslTolerancje
     */
    public static void rozrostRegionow(Obraz_SS x, int[] przedzial)
    {
            //x.getHeigh x.getWidth
          //int szerokosc_aktualna=szerokosc_startowa, wysokosc_aktualna=wysokosc_startowa;
          boolean wlacznik = true;
          
          int licznik;
          int bezpiecznik = 0;
          int limit = x.getWidth()*x.getHeight();
          //System.out.println("Limit = " + limit);        
          while(wlacznik==true)
          {
              //System.out.println("While dziala");
              licznik = 0;             
              //System.out.println(bezpiecznik);
              if (bezpiecznik > limit) {wlacznik = false; System.out.println("Wywalilo bezpieczniki");}
              bezpiecznik++;
              for(int i=0; i<x.getWidth(); i++)
              {
                  for (int j=0;j<x.getHeight(); j++)
                  {
                        if (i>=0 && i<x.getWidth() && j>=0 && j<x.getHeight())
                        {
                            if( (x.getPiksel_SS(i, j).czy_sprawdzone == true && x.getPiksel_SS(i, j).czy_dolaczyc== false && x.getPiksel_SS(i, j).czy_zrodlo == false) || (x.getPiksel_SS(i, j).czy_dolaczyc== true &&x.getPiksel_SS(i, j).czy_sprawdzone == true && x.getPiksel_SS(i, j).czy_zrodlo == true)||(x.getPiksel_SS(i, j).czy_sprawdzone == false && x.getPiksel_SS(i, j).czy_dolaczyc== false && x.getPiksel_SS(i, j).czy_zrodlo == false))
                            {
                                licznik++;
                                //System.out.println("Licznik = " +licznik);
                            }


                            if(x.getPiksel_SS(i, j).czy_dolaczyc == true&&x.getPiksel_SS(i, j).czy_sprawdzone == true &&x.getPiksel_SS(i, j).czy_zrodlo == false)
                            {
                                x.getPiksel_SS(i, j).czy_zrodlo = true;
                                //1
                                if(x.czyDwaPikseleSaWObrazie(i, j, i+1, j))
                                {
                                    if(x.getPiksel_SS(i+1, j).czy_sprawdzone==false)
                                    {
                                        x.porownajDwaPiksele(i, j, i+1, j,przedzial);
                                    }}

                                //2
                                if(x.czyDwaPikseleSaWObrazie(i, j, i, j+1))
                                    {
                                    if(x.getPiksel_SS(i, j+1).czy_sprawdzone==false)
                                    {
                                        x.porownajDwaPiksele(i, j, i, j+1,przedzial);
                                    }
                                    }

                                //3
                                if(x.czyDwaPikseleSaWObrazie(i, j, i-1, j))
                                {
                                    if(x.getPiksel_SS(i-1, j).czy_sprawdzone==false)
                                    {
                                        x.porownajDwaPiksele(i, j, i-1, j,przedzial);
                                    }
                                }

                                //4
                                if(x.czyDwaPikseleSaWObrazie(i, j, i-1, j-1))
                                {
                                if(x.getPiksel_SS(i-1, j-1).czy_sprawdzone==false)
                                {
                                    x.porownajDwaPiksele(i, j, i-1, j-1,przedzial);
                                }}

                                //5
                                if(x.czyDwaPikseleSaWObrazie(i, j, i, j-1))
                                {
                                    if(x.getPiksel_SS(i, j-1).czy_sprawdzone==false)
                                    {
                                        x.porownajDwaPiksele(i, j, i, j-1,przedzial);
                                    }}

                                //6
                                if(x.czyDwaPikseleSaWObrazie(i, j, i+1, j+1))
                                {
                                    if(x.getPiksel_SS(i+1, j+1).czy_sprawdzone==false)
                                    {
                                        x.porownajDwaPiksele(i, j, i+1, j+1,przedzial);
                                    }}

                                //7
                                if(x.czyDwaPikseleSaWObrazie(i, j, i+1, j-1))
                                {
                                if(x.getPiksel_SS(i+1, j-1).czy_sprawdzone==false)
                                {
                                    x.porownajDwaPiksele(i, j, i+1, j-1,przedzial);
                                }}

                                //8
                                if(x.czyDwaPikseleSaWObrazie(i, j, i-1, j+1))
                                {
                                    if(x.getPiksel_SS(i-1, j+1).czy_sprawdzone==false)
                                    {
                                        x.porownajDwaPiksele(i, j, i-1, j+1,przedzial);
                                    }}

                            }
                        }
                      
                      
                      
                  }
              if(licznik == limit) 
                {
                    wlacznik = false; System.out.println("segemntacja zakonczona");
                }
              } 
          }       
    }
    
    /**
     * Metoda robiąca negatyw z obrazu będącego wyjściem algorytmu rozrostu regionów
     * 
     * 
     * @param x przekształcany obraz
     * @return  przekształcony obraz
     */
    public static Obraz_SS regionNegatyw(Obraz_SS x)
    {
        System.out.println("width:"+ x.getWidth());
        System.out.println("height:"+ x.getHeight());
        for(int i=0; i<x.getWidth(); i++)
              {
                  for (int j=0;j<x.getHeight(); j++)
                  {
                      //System.out.println("Wspolrzedne w:" + i + " /n wspolrzedna h: "+ j); 
                      if (x.getPiksel_SS(i, j).czy_dolaczyc == true)
                        {
                            x.getPiksel_SS(i, j).setssRGB(0, 0, 0);
                        }
                      else if (x.getPiksel_SS(i, j).czy_dolaczyc == false)
                        {
                            x.getPiksel_SS(i, j).setssRGB(255, 255, 255);
                        }
                  }
              }
        System.out.println("obraz przeksztalcony na negatyw");
    return x;   
    }
    
    /**
     * Algorytm wykonujący podział metodą Split
     * 
     * 
     * @param x przetwarzany Obraz
     * @param x1 współrzędna X lewego górnego rogu obrazu
     * @param y1 współrzędna Y lewego górnego rogu obrazu
     * @param x2 współrzędna X prawego dolnego rogu obrazu
     * @param y2 współrzędna Y prawego dolnego rogu obrazu
     * @param p poziom wykonywanego splitu (zazwyczaj 1)
     * @param tolerancja    określona tolerancja
     * @return wartość logiczna wykorzytywana w rekurencji algorytmu
     */
    public static boolean split(Obraz_SS x,int x1,int y1,int x2,int y2,int p, int tolerancja)
    {
        boolean skonczone=false;
        int zmienna;
        int min=0;
        int max=0;
        int w=x.getWidth();
        int h=x.getHeight();

        for(int i=x1;i<=x2;i++)
           {
               if (skonczone == true) {break;}
               int a=0;
               for(int j=y1; j<=y2;j++)
               {
                   if (skonczone == true) {break;}
                   zmienna = x.getPiksel_SS(i,j).getSS();
                   if (min==0) {min=zmienna;}
                   if (max==0) {max=zmienna;}
                   if (zmienna-min<0)
                   {
                       min=zmienna;
                   }
                   if (zmienna-max>0)
                   {
                       max=zmienna;
                   }
                     if (w/((int)pow(2,p))>=2 && h/((int)pow(2,p))>=2)
                   {
                        if(max-min>tolerancja)
                        {   
                            p=p+1;
                            skonczone = split(x,x1,y1,x1+w/((int)pow(2,p))-1,y1+h/((int)pow(2,p))-1,p,tolerancja);                            
                            skonczone = split(x,x1+w/((int)pow(2,p)),y1,x2,y1+h/((int)pow(2,p))-1,p,tolerancja);                              
                            skonczone = split(x,x1,y1+h/((int)pow(2,p)),x1+w/((int)pow(2,p))-1,y2,p,tolerancja);                            
                            skonczone = split(x,x1+w/((int)pow(2,p)),y1+h/((int)pow(2,p)),x2,y2,p,tolerancja);                           
                            skonczone=true;
                            p=p-1;
                        }
                   }
                   if (i==x2 && j==y2)
                   {
                       
                       Random rand = new Random();
                       int nowy_SS1= rand.nextInt(256);
                       int nowy_SS2= rand.nextInt(256);
                       int nowy_SS3= rand.nextInt(256);
                       for(int k=x1;k<=x2;k++)
                       {
                         for(int l=y1; l<=y2;l++)
                        {                                            
                          x.getPiksel_SS(k,l).setssRGB(nowy_SS1, nowy_SS2, nowy_SS3);
                        }
                       }
                   }
                }           
           }
        return skonczone;
    }
    
    /**
     * Algorytm wykonujący podział obrazu metodą split, a następnie segmentację metodą Merge
     * 
     * @param x przetwarzany Obraz
     * @param x1 współrzędna X lewego górnego rogu obrazu
     * @param y1 współrzędna Y lewego górnego rogu obrazu
     * @param x2 współrzędna X prawego dolnego rogu obrazu
     * @param y2 współrzędna Y prawego dolnego rogu obrazu
     * @param p poziom wykonywanego splitu (zazwyczaj 1)
     * @param tolerancja    określona tolerancja
     * @return wartość logiczna wykorzytywana w rekurencji algorytmu 
     */
    public static boolean splitmerge(Obraz_SS x,int x1,int y1,int x2,int y2,int p, int tolerancja)
    {
        boolean skonczone=false;
        int zmienna;
        int min=0;
        int max=0;
        int w=x.getWidth();
        int h=x.getHeight();

        for(int i=x1;i<=x2;i++)
           {
               if (skonczone == true) {break;}
               for(int j=y1; j<=y2;j++)
               {
                   if (skonczone == true) {break;}
                   zmienna = x.getPiksel_SS(i,j).getSS();
                   if (min==0) {min=zmienna;}
                   if (max==0) {max=zmienna;}
                   if (zmienna-min<0)
                   {
                       min=zmienna;
                   }
                   if (zmienna-max>0)
                   {
                       max=zmienna;
                   }
                     if (w/((int)pow(2,p))>=2 && h/((int)pow(2,p))>=2)
                   {
                        if(max-min>tolerancja)
                        {   
                            p=p+1;
                            skonczone = splitmerge(x,x1,y1,x1+w/((int)pow(2,p))-1,y1+h/((int)pow(2,p))-1,p,tolerancja);
                                if (skonczone==true) {skonczone=false;}
                            skonczone = splitmerge(x,x1+w/((int)pow(2,p)),y1,x2,y1+h/((int)pow(2,p))-1,p,tolerancja);  
                                if (skonczone==true) {skonczone=false;}
                            skonczone = splitmerge(x,x1,y1+h/((int)pow(2,p)),x1+w/((int)pow(2,p))-1,y2,p,tolerancja);
                                if (skonczone==true) {skonczone=false;}
                            skonczone = splitmerge(x,x1+w/((int)pow(2,p)),y1+h/((int)pow(2,p)),x2,y2,p,tolerancja);
                                if (skonczone==true) {skonczone=false;}  
                            skonczone=true;
                            p=p-1;
                        }
                   }
                   if (i==x2 && j==y2)
                   {               
//                       Random rand = new Random();
//                       int nowy_SS1= rand.nextInt(256);
//                       int nowy_SS2= rand.nextInt(256);
//                       int nowy_SS3= rand.nextInt(256);
//                       for(int k=x1;k<=x2;k++)
//                       {
//                         for(int l=y1; l<=y2;l++)
//                        {                                            
//                          x.getPiksel_SS(k,l).setssRGB(nowy_SS1, nowy_SS2, nowy_SS3);
//                        }
//                       }
                       for(int k=x1; k<=x2;k++)
                            {
                              x.getPiksel_SS(k,y2).setssRGB(255,255,255);
                            }
                       for(int l=y1; l<=y2;l++)
                            {
                                x.getPiksel_SS(x2,l).setssRGB(255,255,255);
                            }
                       if (y2-y1>=2 && x2-x1>=2)
                            {
                              for(int m=x1;m<=x2-1;m++)
                              {
                                  for (int n=y1;n<=y2-1;n++)
                                  {
                                     x.getPiksel_SS(m,n).setssRGB(0,0,0);
                                  }
                              }
                            }
                   }
                }           
           }
        return skonczone;
    }
    
    /**
     * Używana w testach metoda tworząca mały testowy obraz 10x10px  
     * 
     * @return obraz testowy
     */    
    public static Obraz_SS stworzObrazTestowy()
    {
        Piksel_SS[][] macierz = new Piksel_SS[10][10];
        for (int i=0; i<10; i++)
            for (int j=0;j<10;j++)
            {
                Piksel_SS nowy = new Piksel_SS(0,0,0);
                nowy.setSS(5);
                macierz[i][j]= nowy;
            }
        macierz[1][2].setssRGB(255, 255, 255);
        macierz[1][2].setSS(15);
        macierz[1][3].setssRGB(255, 255, 255);
        macierz[1][3].setSS(15);
        macierz[1][4].setssRGB(255, 255, 255);
        macierz[1][4].setSS(15);
        macierz[2][1].setssRGB(255, 255, 255);
        macierz[2][1].setSS(15);
        macierz[2][2].setssRGB(255, 255, 255);
        macierz[2][2].setSS(15);
        macierz[2][3].setssRGB(255, 255, 255);
        macierz[2][3].setSS(15);
        macierz[3][2].setssRGB(255, 255, 255);
        macierz[2][2].setSS(15);
        Obraz_SS obrazek = new Obraz_SS(10,10,macierz);
        return obrazek;
    }
    
  
}



