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
	private int x;
	private int y;
	private boolean toBeRemoved;
	private Image img;
	
	public GameElement(int x,int y) throws SlickException{
		this.x=x;
		this.y=y;
		toBeRemoved=false;
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
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	public void draw() {
		img.draw(getX()+25, getY()+25, 50, 50);
	}
	
	public boolean isToBeRemoved() {
		return toBeRemoved;
	}
	public void setToBeRemoved() {
		toBeRemoved=true;
	}
}
