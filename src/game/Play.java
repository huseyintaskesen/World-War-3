/**
 * 
 */
package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	public String mouse= "No input yet";
	Image view;
	Image pause;
	
	private boolean pauseFlag=false;
	
	public Play (int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/play.png");
		pause = new Image("res/pause.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		if(pauseFlag)
			pause.draw(250, 200);
		//g.drawString("Play State", 200, 200);
//		g.drawRect(50, 50, 100, 50);
//		g.drawString("Back", 60, 67);

		
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
	}
	public void update(GameContainer gc, StateBasedGame sbg,int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 600 - Mouse.getY();
		
		
		mouse = "x : "+xpos+" y : "+ypos+"delta:"+delta;
		// Old back button
//		if ((50 < xpos && xpos < 150) && (50 < ypos && ypos < 100)) {
//			if (input.isMouseButtonDown(0)) {
//				sbg.enterState(0);
//			}
//		}
		
		// Pause button
		if ((560 < xpos && xpos < 600) && (19 < ypos && ypos < 72)) {
			if (input.isMouseButtonDown(0)) {
				pauseFlag = true;
			}
		}
		
		//Quit button
		if ((750 < xpos && xpos < 790) && (19 < ypos && ypos < 72)) {
			if (input.isMouseButtonDown(0)&&!pauseFlag) {
				sbg.enterState(0);
			}
		}
		
		// Resume in pause menu
		if ((333 < xpos && xpos < 455) && (244 < ypos && ypos < 284)) {
			if (input.isMouseButtonDown(0)) {
				pauseFlag=false;
			}
		}
		
		// Quit in pause menu
		if ((333 < xpos && xpos < 455) && (291 < ypos && ypos < 332)) {
			if (input.isMouseButtonDown(0)) {
				pauseFlag=false;
				sbg.enterState(0);
			}
		}
	}
	public int getID() {
		return 1;
	}
}
