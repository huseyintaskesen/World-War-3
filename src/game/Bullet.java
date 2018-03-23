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
public class Bullet extends GameElement{
	private Image bullet=new Image("res/bullet.png");
	
	private int speed;	
	private int damage;
//	private int x;
//	private int y;
	
	public Bullet(int x,int y, int damage) throws SlickException{
		super(x, y);
		speed = 8;
		this.damage = damage;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void updateLocation() {
		x=x+speed/2;
	}
	
	public void damageRobot(Casual robot,AttackerHuman shooter) {
		robot.takeDamage(damage);
		shooter.getBullets().remove(this);
	}
	
	public void draw() {
		bullet.draw(x, y, 25, 10);
	}
}
