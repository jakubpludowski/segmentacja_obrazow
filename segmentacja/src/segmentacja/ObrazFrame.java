package segmentacja;
import javax.swing.JButton;
import javax.swing.JFrame;
 import javax.swing.JPanel;

 /**
  * Klasa wyświetlająca nowe okno typu JFrame zawierające obraz
  * 
  * @author pludy
  */
 public class ObrazFrame extends JFrame 
 {
     /**
      * Konstruktor wyświetlający obraz zapisany na dysku
      * 
      * @param nazwa_pliku ścieżka zpisanego obrazu wraz z rozszerzeniem
      */
     public ObrazFrame(String nazwa_pliku) 
        {
         super("Program obrazkowy");
 
         JPanel obrazPanel = new ObrazPanel(nazwa_pliku);
                // na przyklad ObrazPanel("java.jpg");
         add(obrazPanel);
 

         pack();
         setVisible(true);
     }
        /**
         * Konstruktor wyświetlający obraz przechowywany jako obiekt typu Obraz
         * 
         * 
         * @param x obraz
         */
        public ObrazFrame(Obraz x)
        {
            super("Program obrazkowy");
            JPanel obraz_do_wyswietlenia = new ObrazPanel(x);
            add(obraz_do_wyswietlenia);
 

            pack();
            setVisible(true);
        }
        /**
         * Konstruktor wyświetlający obraz przechowywany jako obiekt typu ObrazPanel
         * 
         * 
         * @param x element typu obrazpanel
         */
        public ObrazFrame(ObrazPanel x)
        {
            super("Program obrazkowy");
            JPanel obraz_do_wyswietlenia = x;
            add(obraz_do_wyswietlenia);
 

            pack();
            setVisible(true);
        }
        /**
         * Konstruktor wyświetlający obraz przechowywany jako obiekt typu Obraz_SS
         * 
         * 
         * @param x obiekt typu Obraz_SS
         */
        public ObrazFrame(Obraz_SS x)
        {
            super("Program obrazkowy");
            JPanel obraz_do_wyswietlenia = new ObrazPanel(x);
            add(obraz_do_wyswietlenia);
            
 

            pack();
            setVisible(true);
        }
                /**
         * Konstruktor wyświetlający obraz przechowywany jako obiekt typu Obraz_SS
         * 
         * 
         * @param x obiekt typu Obraz_SS
         * @param def String definujący użycie tego konstruktora
         */
        public ObrazFrame(Obraz_SS x, String def)
        {
            super("Program obrazkowy");
            JPanel obraz_do_wyswietlenia = new ObrazPanel(x, def);
            add(obraz_do_wyswietlenia);
            

          
            pack();
            setVisible(true);
        }
 }