package Main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Level {
  public Boolean isNotFinished=true;
  protected int level;
  
  //overloading methods for the classes that extends this class.
  public void buildLevel(JPanel p){}
  public void paintLevel(Graphics2D comp2D ,JPanel p){}
  public int getLevel(){return level;}
  public void keyPressed (KeyEvent event){}
}