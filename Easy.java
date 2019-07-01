package Main;
import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class Easy extends Level implements Runnable  {
	JPanel p; 
	Image Level1,backward,forward,jumpupR,jumpupL,E1,K1; //Images
	Image player; //image for changing state for mario.
	Thread runner; //new thread
	public long jumpingTime=300; //delay for jumping time of thread
	Level L; //instance for class Level
	Boolean next;
	int u=CGame.level; //variable level accessed from CGame Class
	int spriteX=50, spriteY=395; //for mario's position
	int pX=692,pY=336; //for pipe's position
	int w1X=3656,wY=336; //for wall1 position
	int w2X=6770; //for wall 2 position
	int bk1X=4159,bkY=304; //for brick base 1 position
	int bk2X=5280; //for brick 2 base position
	int k1X=2810,k1Y=277; //for koopa position
	int VELOCITY = 15; //speed which player travels at
	int totalpoints=0; //total points collected by coins
	boolean end=false; // boolean to check if player reached the finish line or not

	//making the image array for goomba and setting locations for them
	Image[] goomba=new Image[10];
	int [] eX={2107,1397,3267,4000,4380,4783,5836,7242,7669,7452};
	int eY=422;

	//initializing the x values for al the grounds
	int [] gX={0,1158,2858,5394,6302,6880};
	int gY=448;

	//drawing out the collision detection boxes for all the goombas
	Rectangle G1=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G2=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G3=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G4=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G5=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G6=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G7=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G8=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G9=new Rectangle(eX[0]+2,eY,17,23);
	Rectangle G10=new Rectangle(eX[0]+2,eY,17,23);

	//collision detection box for koopa
	Rectangle Ku1=new Rectangle(k1X,k1Y,24,30);

	//create image array for the coins and set their locations
	Image coin []=new Image[10];
	int []x={718,1737,3666,4236,5352,5855,6782,7417,7513,7319};
	int []y={302,366,302,267,267,341,302,416,416,416};

	int bx=0,by=0; //for background image
	private final double gravity=15; //speed which mario falls to the ground
	boolean onGround=false,up,l,r,jumping=false; //to check if mario is jumping, on ground,facing left or right.
	boolean gb1,k1; //if goomba or koopa reach certain X value...they turn to the opposite side.

	public Easy() { 
		//getting all the images from file.
		Toolkit kit = Toolkit.getDefaultToolkit();
		Level1 = kit.getImage("Back1.png");
		backward= kit.getImage("Left.gif");
		forward= kit.getImage("Right.gif");
		jumpupR=kit.getImage("MarioJumpR.png");
		jumpupL=kit.getImage("MarioJumpL.png");
		E1=kit.getImage("En1.png");
		K1=kit.getImage("K1.png");

		for(int i=0;i<goomba.length;i++){
			goomba[i]=kit.getImage("E1R.png");
		}

		for(int i=0;i<coin.length;i++){
			coin[i]=kit.getImage("coin.png");
		}

		//initiallize the thread and start it to make mario jump.
		runner= new Thread(this);
		runner.start();
		player=forward; //set the initial state of mario facing forward.
		level=1;

	}
	public void buildLevel(JPanel p) 
	{	Toolkit mob = Toolkit.getDefaultToolkit();

	//to check if the player has reached maximum position to switch sides
	if(eX[0]<gX[1]||eX[1]<gX[1]){
		gb1=true;
	}else if(eX[0]>gX[1]+1585||eX[1]>gX[1]+1585){
		gb1=false;
	}

	//same thing with koopa as above
	if(k1X<gX[2]-579){
		k1=true;
		K1=mob.getImage("K1R.png");
	}else if(k1X>gX[2]+400){
		k1=false;
		K1=mob.getImage("K1L.png");
	}
	//if mario intersects with any of the ememies, the game is over
	if(!end){
	if (spriteY>480||getMarioR().intersects(G1)||getMarioR().intersects(Ku1)||getMarioL().intersects(G1)||getMarioL().intersects(Ku1)||getMarioU().intersects(Ku1)||getMarioR().intersects(G2)||getMarioL().intersects(G2)||getMarioR().intersects(G3)||getMarioL().intersects(G3)||getMarioR().intersects(G4)||getMarioL().intersects(G4)||getMarioR().intersects(G5)||getMarioL().intersects(G5)||getMarioR().intersects(G6)||getMarioL().intersects(G6)||getMarioR().intersects(G7)||getMarioL().intersects(G7)||getMarioR().intersects(G8)||getMarioL().intersects(G8)||getMarioR().intersects(G9)||getMarioL().intersects(G9)||getMarioR().intersects(G10)||getMarioL().intersects(G10))
	{
		player=null;
		JOptionPane.showMessageDialog(p,"YOU DIED with "+totalpoints+" coins","GAME OVER",
				JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
	}
	}

	//methods for the keylistener. same thing for comment for key listener for right key.
	public void keyPressed (KeyEvent event){ 	
		if(event.getKeyCode() == KeyEvent.VK_LEFT )
		{ 
			l=true;
			r=false;

			if(!end)
				spriteX-=VELOCITY;


			if(spriteX<0)
			{spriteX=0;}

			totalpoints+=(pointcount(spriteX,spriteY,x, y, coin));

			if(spriteX<200 && bx<0){
				spriteX=200;
				bx+=VELOCITY;
				pX+=VELOCITY;
				for(int i=0;i<gX.length;i++){
					gX[i]+=VELOCITY;
				}
				for(int i=0;i<eX.length;i++){
					eX[i]+=VELOCITY;
				}
				k1X+=VELOCITY;
				w1X+=VELOCITY;
				w2X+=VELOCITY;
				bk1X+=VELOCITY;
				bk2X+=VELOCITY;

				for(int i=0;i<coin.length;i++){
					x[i]+=VELOCITY;
				}
			}
			if(getMarioL().intersects(getPipe1())){
				spriteX+=VELOCITY;
			}
			else if(getMarioL().intersects(getWall1())){
				spriteX+=VELOCITY;
			}
			else if(getMarioL().intersects(getWall2())){
				spriteX+=VELOCITY;
			}
			else if(getMarioL().intersects(getBrick1())){
				spriteX+=VELOCITY;
			}
			else if(getMarioL().intersects(getBrick2())){
				spriteX+=VELOCITY;
			}

		}
		else if (event.getKeyCode() == KeyEvent.VK_RIGHT )
		{ 
			r=true;
			l=false;

			if(!end)
				spriteX+=VELOCITY;

			if(spriteX>590)
			{
				spriteX=590;
				end=true;
				
				player=null;
				JOptionPane.showMessageDialog(p,"YOU WON! HURRAY! with total of "+totalpoints+" coins","GAME WIN",
						JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			} 

			totalpoints+=(pointcount(spriteX,spriteY,x, y, coin));

			//for scrolling background(if mario reaches certain position on screen, the background moves along with him in the oppoite direction.
			if(spriteX>310 && bx>-7690){
				spriteX=310;
				bx-=VELOCITY;
				pX-=VELOCITY;
				for(int i=0;i<gX.length;i++){
					gX[i]-=VELOCITY;
				}
				for(int i=0;i<eX.length;i++){
					eX[i]-=VELOCITY;
				}
				k1X-=VELOCITY;
				w1X-=VELOCITY;
				w2X-=VELOCITY;
				bk1X-=VELOCITY;
				bk2X-=VELOCITY;

				for(int i=0;i<coin.length;i++){
					x[i]-=VELOCITY;
				}
			}
			//if mario hits any of these, he is pushed back
			if(getMarioR().intersects(getPipe1())){
				spriteX-=VELOCITY;
			}
			else if(getMarioR().intersects(getWall1())){
				spriteX-=VELOCITY;
			}
			else if(getMarioR().intersects(getWall2())){
				spriteX-=VELOCITY;
			}
			else if(getMarioR().intersects(getBrick1())){
				spriteX-=VELOCITY;
			}
			else if(getMarioR().intersects(getBrick2())){
				spriteX-=VELOCITY;
			}
		}
		else if (event.getKeyCode() == KeyEvent.VK_UP ){ 
			//if mario still didnt reach the finish area
			if(!end)
				if(onGround){
					jumping=true;
					new Thread(new thread()).start();
				}
				else if(getMarioU().intersects(getBrick1())){
					spriteY+=gravity;
				}
				else if(getMarioU().intersects(getBrick2())){
					spriteY+=gravity;
				}
		}
	}

	public void keyReleased (KeyEvent event){ 
		if(event.getKeyCode() == KeyEvent.VK_LEFT )
		{ 
			spriteX-=0;
		}
		else if (event.getKeyCode() == KeyEvent.VK_RIGHT )
		{ 
			spriteX+=0;
		}
		else if (event.getKeyCode() == KeyEvent.VK_UP ){ 
		}
	}    
	//method to paint the level every frame.
	public void paintLevel(Graphics2D g ,JPanel p) {
		//draws out the images to a certain location.
		g.drawImage(Level1, bx, by, p); 
		g.drawImage(player,spriteX,spriteY, p);
		g.drawImage(K1,k1X,k1Y,p);
		g.drawString("Points:"+totalpoints, 320,50);

		for(int i=0;i<goomba.length;i++){
			g.drawImage(goomba[i],eX[i],eY,p);
		}
		//sets new collision rectangles at every goomba there is.
		G1=new Rectangle(eX[0]+2,eY,17,23);
		G2=new Rectangle(eX[1]+2,eY,17,23);
		G3=new Rectangle(eX[2]+2,eY,17,23);
		G4=new Rectangle(eX[3]+2,eY,17,23);
		G5=new Rectangle(eX[4]+2,eY,17,23);
		G6=new Rectangle(eX[5]+2,eY,17,23);
		G7=new Rectangle(eX[6]+2,eY,17,23);
		G8=new Rectangle(eX[7]+2,eY,17,23);
		G9=new Rectangle(eX[8]+2,eY,17,23);
		G10=new Rectangle(eX[9]+2,eY,17,23);

		//also one at koopa and coin
		Ku1=new Rectangle(k1X+2,k1Y,24,30);

		for(int i=0;i<coin.length;i++){
			g.drawImage(coin[i],x[i],y[i],p);
		}

		//if the first two goombas are turning to the opposite side. so does all the goombas because they follow each other.
		if(gb1){
			eX[0]+=5;
			eX[1]+=5;
			eX[2]+=5;
			eX[3]+=5;
			eX[4]+=5;
			eX[5]+=5;
			eX[6]+=5;
			eX[7]+=5;
			eX[8]+=5;
			eX[9]+=5;
		}else if(!gb1){
			eX[0]-=5;
			eX[1]-=5;
			eX[2]-=5;
			eX[3]-=5;
			eX[4]-=5;
			eX[5]-=5;
			eX[6]-=5;
			eX[7]-=5;
			eX[8]-=5;
			eX[9]-=5;
		}
		if(k1){
			k1X+=5;
		}else if(!k1){
			k1X-=5;
		}
		//if the player is facing right or left, this changes its state to make him face the other way
		if(l==true){
			player=backward;
		}
		else if(r==true){
			player=forward;
		}
		//if mario is not on the ground, mario state switches to jumping mario image
		if(!onGround&&l){
			player=jumpupL;
		}else if(!onGround&&r){
			player=jumpupR;
		}

		//if collision detection works between mario and all these platforms, it tells the program that mario is on ground therefore he is allowed to jump.
		if(getMarioD().intersects(getGround1())){
			onGround=true;
		}
		else if(getMarioD().intersects(getPipe1())){
			onGround=true;
		}
		else if(getMarioD().intersects(getGround2())){
			onGround=true;
		}
		else if(getMarioD().intersects(getGround3())){
			onGround=true;
		}
		else if(getMarioD().intersects(getGround4())){
			onGround=true;
		}
		else if(getMarioD().intersects(getGround5())){
			onGround=true;
		}
		else if(getMarioD().intersects(getGround6())){
			onGround=true;
		}
		else if(getMarioD().intersects(getWall1())){
			onGround=true;
		}
		else if(getMarioD().intersects(getWall2())){
			onGround=true;
		}
		else if(getMarioD().intersects(getBrick1())){
			onGround=true;
		}
		else if(getMarioD().intersects(getBrick2())){
			onGround=true;
		}

		else{ //if he is not on ground, he is pulled back down to ground until he touches something from bottom.
			onGround=false;
			spriteY+=gravity;
		}
		//if he is jumping then he is moved up 50 spaces
		if(jumping){
			spriteY-=45;
		}


	}
	//method to run thread
	public void run(){
		Thread thisThread=Thread.currentThread();
		while(runner == thisThread){
			while(runner == thisThread&&next&&L.isNotFinished){
				try { Thread.sleep(150); }
				catch (InterruptedException e) {}
			}
			if(u==5) 
			{
				L =(Level) new Easy();
			}
			next=true;
		}
	}

	//creating collision rectangle mathods for multiple objects.
	public Rectangle getMarioL(){
		return new Rectangle(spriteX,spriteY+5,5,35);
	}
	public Rectangle getMarioR(){
		return new Rectangle(spriteX+35,spriteY+5,5,35);
	}
	public Rectangle getMarioU(){
		return new Rectangle(spriteX+10,spriteY-5,20,5);
	}
	public Rectangle getMarioD(){
		return new Rectangle(spriteX,spriteY+50,35,4);
	}
	public Rectangle getPipe1(){
		return new Rectangle(pX,pY,64,112);
	}
	public Rectangle getGround1(){
		return new Rectangle(gX[0],gY,1086,10);
	}
	public Rectangle getGround2(){
		return new Rectangle(gX[1],gY,1594,10);
	}
	public Rectangle getGround3(){
		return new Rectangle(gX[2],gY,2464,10);
	}
	public Rectangle getGround4(){
		return new Rectangle(gX[3],gY,834,10);
	}
	public Rectangle getGround5(){
		return new Rectangle(gX[4],gY,506,10);
	}
	public Rectangle getGround6(){
		return new Rectangle(gX[5],gY,1470,10);
	}
	public Rectangle getWall1(){
		return new Rectangle (w1X,wY,38,112);
	}
	public Rectangle getWall2(){
		return new Rectangle (w2X,wY,38,112);
	}
	public Rectangle getBrick1(){
		return new Rectangle (bk1X,bkY,173,38);
	}
	public Rectangle getBrick2(){
		return new Rectangle (bk2X,bkY,173,38);
	}

	//sub class to make mario jump even more smoother with thread
	public class thread implements Runnable{

		public void run() {
			try{
				Thread.sleep(jumpingTime);
				jumping=false;
			}catch(Exception e){
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
			}
		}
	}
	//method to check if player hit a coin
	public static int pointcount (int spriteX, int spriteY, int x[], int y[], Image coin[])
	{
		int max=0;
		//compare the coordinate of sprite and images in the array 
		for (int i=0;i<coin.length;i++)
		{
			//if mario hit a coin 
			if (spriteX < x[i]+40 && spriteX > x[i]-40 && spriteY < y[i]+40 && spriteY > y[i]-40 && !(coin[i]== null) )
			{
				coin[i] = null; //remove coin
				max=3;  //return 3 points
			} 
		}
		return (max);
	}
}


