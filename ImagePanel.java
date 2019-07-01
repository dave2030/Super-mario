package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel 
{

	//class that basically returns an image when called from a different class or method
   private Image img;

   public ImagePanel(String img) {
     this(new ImageIcon(img).getImage());
   }

   public ImagePanel(Image img) {
     this.img = img;
     Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
     setPreferredSize(size);
     setMinimumSize(size);
     setMaximumSize(size);
     setSize(1000,1000);
     setLayout(null);
   }

}

