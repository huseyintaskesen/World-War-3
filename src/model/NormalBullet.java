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
public class NormalBullet extends Bullet {

	/**
	 * @param x
	 * @param y
	 * @param damage
	 * @throws SlickException
	 */
	public NormalBullet(float x, float y, int damage) throws SlickException {
		super(x, y, damage);
		setImg(new Image("res/bullet.png"));
	}

}
