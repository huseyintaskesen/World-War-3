/**
 * 
 */
package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Hints extends BasicGameState {

	Image view;
	public String mouse= "No input yet";
	
	public Hints(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/guideBook.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		
		g.drawRect(600, 30, 70, 30);
		g.drawString("Back", 610, 35);
		
		
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 600 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;
		if ((600 < xpos && xpos < 670) && (30 < ypos && ypos < 60)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
	}
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

}
