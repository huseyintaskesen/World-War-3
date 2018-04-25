/**
 * 
 */
package view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Gameover extends BasicGameState {

	Image view;
	Sound gameOverSound;
	public String mouse= "No input yet";
	
	public Gameover(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/gameover.png");
		gameOverSound = new Sound("res/game-over-sound.wav");
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
		return 4;
	}

}
