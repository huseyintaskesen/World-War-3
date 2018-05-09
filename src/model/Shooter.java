/**
 * 
 */
package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author ibrahim
 *
 */
public class Shooter extends RangedAttacker{
<<<<<<< HEAD
	private final int startHealth = 7;
=======
	private final int startHealth=20;
	public final static int cost = 150;
>>>>>>> bacfcf9ebd143ed4905e5f284449ee26191dd496
	
	public Shooter(int x,int y) throws SlickException {
		super(x, y);
		setImg(new Image("res/Robots & Humans/doubleShooter.png"));
		
		setDamage(3);
		setHealth(startHealth);
		setRange(400);//TODO to be changed
	}

	
	
	


}
