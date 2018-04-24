/**
 * 
 */
package view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Credits extends BasicGameState {

	Image view;
	public String mouse= "No input yet";
	
	public Credits(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/credits.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		
//		g.fillOval(75, 100, 100, 100);
//		g.drawString("Play", 80, 70);
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 720 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;
		if ((16 < xpos && xpos < 244) && (23 < ypos && ypos < 71)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(0);
			}
		}
	}
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
