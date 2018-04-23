package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FastRobot extends Robot{
	private final int startHealth=3;
	private final int speed = 4;
	
	
	public FastRobot(int x,int y) throws SlickException{
		super(x, y);
		img=new Image("res/Robots & Humans/basicRobot.png");
		damage = 3;
		health=3;
		curSpeed=4;
	}

	public void run() {
		super.run();
		curSpeed = speed;
	}
	

	
}