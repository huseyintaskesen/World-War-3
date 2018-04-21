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
	
	public void damageRobot(Robot casual,AttackerHuman shooter) {
		casual.takeDamage(damage);
		shooter.getBullets().remove(this);
	}
	
	public void draw() {
		bullet.draw(getX(), getY(), 25, 10);
	}
}
