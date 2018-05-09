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
public class FreezerBullet extends Bullet {

	/**
	 * @param x
	 * @param y
	 * @param damage
	 * @throws SlickException
	 */
	public FreezerBullet(float x, float y, int damage) throws SlickException {
		super(x, y, damage);
		setImg(new Image("res/laser.png"));
	}

}
