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
public abstract class GameElement {
	private float x;
	private float y;
	private boolean toBeRemoved;
	private Image img;
	
	public GameElement(float x,float y) throws SlickException{
		this.x=x;
		this.y=y;
		toBeRemoved=false;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	public void draw() {
		img.draw(getX()+40, getY()+40, 70, 70);
	}
	
	public boolean isToBeRemoved() {
		return toBeRemoved;
	}
	public void setToBeRemoved() {
		toBeRemoved=true;
	}
}
