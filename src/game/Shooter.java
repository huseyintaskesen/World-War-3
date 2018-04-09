/**
 * 
 */
package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Shooter extends AttackerHuman{
	private final int startHealth=20;
	
	public Shooter(int x,int y) throws SlickException {
		super(x, y);
		setImg(new Image("res/Robots & Humans/doubleShooter.png"));
		
		setDamage(2);
		setHealth(startHealth);
		setRange(400);//TODO to be changed
	}


}
