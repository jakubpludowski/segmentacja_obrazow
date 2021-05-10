package segmentacja;
import java.awt.*;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.IOException;
 import javax.imageio.ImageIO;
 import javax.swing.JPanel;
 import java.awt.Color;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;

 /**
  * Klasa mająca za zadanie stworzenie obiektu typu JPanel z biblioteki Swing zawierającego obraz
  * 
  * @author pludy
  */
 public class ObrazPanel extends JPanel implements MouseListener {
 
 	public BufferedImage image;
        public int wybrana_szerokosc,wybrana_wysokosc;
        /**
         * Konstruktor panelu wczytujący obraz zapisany na dysku
         * @param nazwa ścieżka do wyświetlanego obrazu 
         */
 	public ObrazPanel(String nazwa) 
        {           
 		File imageFile = new File(nazwa);
 		try 
                {
 			image = ImageIO.read(imageFile);
 		}
                catch (IOException e) 
                {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
 		}
 		Dimension dimension = new Dimension(image.getWidth()/5, image.getHeight()/5);
 		setPreferredSize(dimension);                
 	}
        /**
         * Konstruktor panelu wczytujący obraz przechowywany jako obiekt typu Obraz
         * 
         * @param x obiekt typu Obraz
         */
        public ObrazPanel(Obraz x) 
        {                       
            image = new BufferedImage(x.getWidth(),x.getHeight(),BufferedImage.TYPE_INT_RGB);
            for (int i =0; i<x.getWidth(); i++)
                for(int j=0;j<x.getHeight(); j++)
                   {                       
                       Piksel nowy_piksel = x.getPiksel(i,j);
                       image.setRGB(i, j, (int)nowy_piksel.sRGB.getRGB());
                   }
            Dimension dimension = new Dimension(x.getWidth(), x.getHeight());
            setPreferredSize(dimension);
 	}
         /**
         * Konstruktor panelu wczytujący obraz przechowywany jako obiekt typu Obraz_SS
         * 
         * @param x obiekt typu Obraz_SS
         */
        public ObrazPanel(Obraz_SS x) 
        {                    
            image = new BufferedImage(x.getWidth(),x.getHeight(),BufferedImage.TYPE_INT_RGB);
            for (int i =0; i<x.getWidth(); i++)
                for(int j=0;j<x.getHeight(); j++)
                   {                       
                       Piksel_SS nowy_piksel = x.getPiksel_SS(i,j);
                       image.setRGB(i, j, (int)nowy_piksel.ssRGB.getRGB());
                   }
            Dimension dimension = new Dimension(x.getWidth()+10, x.getHeight()+10);
            setPreferredSize(dimension);                       
 	}
        /**
         * Konstruktor panelu wczytujący obraz przechowywany jako obiekt typu Obraz_SS pozwalający na wybranie współrzędnych obrazu za pomocą myszki
         * 
         * @param x obiekt typu Obraz_SS
         * @param def niewpływający na działanie konstruktora ciąg znaków mający zdefiniować wczytywanie myszką
         */
        public ObrazPanel(Obraz_SS x, String def) 
        {                     
            image = new BufferedImage(x.getWidth(),x.getHeight(),BufferedImage.TYPE_INT_RGB);
            for (int i =0; i<x.getWidth(); i++)
                for(int j=0;j<x.getHeight(); j++)
                   {                       
                       Piksel_SS nowy_piksel = x.getPiksel_SS(i,j);
                       image.setRGB(i, j, (int)nowy_piksel.ssRGB.getRGB());
                   }
            addMouseListener(this);
            Dimension dimension = new Dimension(x.getWidth(), x.getHeight());
            setPreferredSize(dimension);
 	}
 
 	@Override
 	public void paintComponent(Graphics g) {
 		Graphics2D g2d = (Graphics2D) g;
 		g2d.drawImage(image, 0, 0, this);
 	}
        /**
         * ActionListener kontrolujący naciśnięcie myszką w piksel
         * 
         * 
         * @param e obiekt typu event
         */
        @Override
        public void mouseClicked(MouseEvent e) {
                   wybrana_szerokosc = e.getX();
                   wybrana_wysokosc = e.getY();
                   wybrana_szerokosc--; wybrana_wysokosc--;
                   System.out.println("Kliknięto w piksel o współrzędnych "+wybrana_szerokosc+" oraz "+wybrana_wysokosc);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
 }
