package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CGame extends JFrame implements ActionListener{
	static CGame2 gui;

	//calls image panel class for background image
	static ImagePanel background = new ImagePanel(new ImageIcon("Background.jpg").getImage());
	Container contentPane = getContentPane();

	//makes buttons
	JButton music = new JButton ("Music");
	JButton start = new JButton ("Start Game");
	JButton rules = new JButton ("Rules");
	
	//creates label for title
	JLabel title = new JLabel ("Super Mario Game");

	public static int level;

	public CGame ()
	{
		//sets basic structure of frame
		super("Super Mario");
		setSize( 640,480 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setLocationRelativeTo(null);
		background.setLayout(null);

		//music is not supposed to be playing i just kept there because three buttons look better than 2
		music.setIcon(new ImageIcon("Music.jpg"));
		music.setBounds(400,280,60,40); 
		music.setActionCommand("music");
		
		//sets button structures
		start.setIcon(new ImageIcon("Play Button.jpg"));
		start.setBounds(200,280,60,40);
		start.setActionCommand("start");

		rules.setIcon(new ImageIcon("Instructions Button.jpg"));
		rules.setBounds(295,280,70,40); 
		rules.setActionCommand("rules");

		//adds action listener to buttons
		music.addActionListener(this);
		start.addActionListener(this);
		rules.addActionListener(this);

		//adds everything on background and then the frame
		background.add(title);
		background.add(start);
		background.add(music);
		background.add(rules);
		contentPane.add(background);
		pack();
		setVisible(true);
		}
	public void actionPerformed(ActionEvent event) 
	{
		String eventName = event.getActionCommand(); 
		if( eventName.equals("music")) 
		{

		}
		if( eventName.equals("rules")) 
		{
			JOptionPane.showMessageDialog(background,"You play as mario and you have to reach to the end of the map without hitting any of the monsters on the way.\n"+
					"On your way, there will be blocks in mid air and you have to jump on them to collect coins which will get added up at the end.\n"+
					"The controls to move mario is the arrow keys(right and left) to move and up to jump. GOODLUCK!","Instructions",
					JOptionPane.PLAIN_MESSAGE);
		}
		if( eventName.equals("start")) 
		{
			music.setVisible(false);
			rules.setVisible(false);
			start.setVisible(false);
			title.setVisible(false);

			gui =new CGame2();
			gui.setFocusable(true);
			gui.setBounds(0,0,640,480);
			background.add(gui);
			gui.setNext();
			gui.requestFocus();
		}
	}
	public static void main(String[] args) 
	{
		CGame giu = new CGame ();
	}

}

