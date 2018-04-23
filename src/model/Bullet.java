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
	
	public Bullet(int x,int y, int damage) throws SlickException{
		super(x, y);
		speed = 8;
		this.damage = damage;
	}

	public void updateLocation() {
		setX(getX()+speed/2);
	}
	
	public void damageRobot(RobotSide casual,RangedAttacker shooter) {
		casual.takeDamage(damage);
		shooter.getBullets().remove(this);
	}
	
	public void draw() {
		bullet.draw(getX()+25, getY()+25, 25, 10);
	}
}
