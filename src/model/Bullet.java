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
public class Bullet extends GameElement{
	private Image bullet=new Image("res/bullet.png");
	
	private int speed;	
	private int damage;
	
	public Bullet(float x,float y, int damage) throws SlickException{
		super(x, y);
		speed = 4;
		this.damage = damage;
	}

	public void updateLocation() {
		setX(getX()+speed);
	}
	
	public void damageRobot(RobotSide robot,RangedAttacker rangedAttacker) {
		robot.takeDamage(damage);
		if(rangedAttacker instanceof Freezer)
			robot.slow();
		rangedAttacker.getBullets().remove(this);
	}
	
	public void draw() {
		bullet.draw(getX()+25, getY()+25, 25, 10);
	}
}
