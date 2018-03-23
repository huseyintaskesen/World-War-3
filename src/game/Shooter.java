/**
 * 
 */
package game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Shooter extends AttackerHuman{// implements AggressiveHuman{
	private final int startHealth=20;

//	private int damage;
//	private int health;
	//private int range;
//	private int x;
//	private int y;
	
	
	//protected ArrayList<Bullet> bullets;
	
	
	public Shooter(int x,int y) throws SlickException {
		super(x, y);
		super.img=new Image("res/shooter.png");
		
		damage = 2;
		health= startHealth;
		range = 400;//TODO to be changed
		//bullets = new ArrayList<Bullet>();
	}


	

	
//	public void draw() {
//		img.draw(x, y, 50, 50);
//	}
	
}
