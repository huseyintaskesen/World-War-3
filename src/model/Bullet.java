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
public abstract class Bullet extends GameElement{
	//private Image bullet=new Image("res/bullet.png");
	
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
		if(this instanceof FreezerBullet)
			robot.slow();
		rangedAttacker.getBullets().remove(this);
	}
	
	public void draw() {
		getImg().draw(getX()+30, getY()+12, 25, 10);
	}
}
