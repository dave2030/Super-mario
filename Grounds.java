package Sprites;

import java.awt.Rectangle;

import Main.Easy;

public class Grounds  {

	public static int g1X=0,g1Y=448; //for ground 1
	public static int g2X=1158,g2Y=448; //for ground 2
	public static int g3X=2858,g3Y=448; //for ground 3
	public static int g4X=5394,g4Y=448; //for ground 4
	public static int g5X=6302,g5Y=448; //for ground 5
	public static int g6X=6880,g6Y=448; //for ground 6
	
	public Rectangle getGround1(){
		return new Rectangle(g1X,g1Y,1086,32);
	}
	public Rectangle getGround2(){
		return new Rectangle(g2X,g2Y,1594,32);
	}
	public Rectangle getGround3(){
		return new Rectangle(g3X,g3Y,2464,32);
	}
	public Rectangle getGround4(){
		return new Rectangle(g4X,g4Y,834,32);
	}
	public Rectangle getGround5(){
		return new Rectangle(g5X,g5Y,506,32);
	}
	public Rectangle getGround6(){
		return new Rectangle(g6X,g6Y,1470,32);
	}


}
