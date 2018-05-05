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


public class Hints extends BasicGameState {

	Image view;
	public String mouse= "No input yet";
	boolean flag = true;
	
	public Hints(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/Hints screens/Hints-Miner.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);
		
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
		
		if ((25 < xpos && xpos < 250) && (30 < ypos && ypos < 78)){
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		
		if ((120 < xpos && xpos < 185) && (102 < ypos && ypos < 130)){
			if (input.isMouseButtonDown(0)) {
				flag = true;
				view = new Image("res/Hints screens/Hints-Miner.png");
			}
		}
		
		if ((187 < xpos && xpos < 250) && (102 < ypos && ypos < 130)){
			if (input.isMouseButtonDown(0)) {
				flag = false;
				view = new Image("res/Hints screens/Hints-CasualRobot.png");
			}
		}
		
		//information about humans is displayed.
		if(flag) {
			
			if((126 < xpos && xpos < 246) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-Miner.png");
				}
			}
			else if((276 < xpos && xpos < 396) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-Swordsman.png");
				}
			}
			else if((426 < xpos && xpos < 546) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-Freezer.png");
				}
			}
			else if((576 < xpos && xpos < 696) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-Shooter.png");
				}
			}
			else if((726 < xpos && xpos < 846) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-Obstacle.png");
				}
			}
			else if((876 < xpos && xpos < 996) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-LandMine.png");
				}
			}
		}
		
		//information about robots is displayed.
		else {
			
			if((126 < xpos && xpos < 246) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-CasualRobot.png");
				}
			}
			else if((276 < xpos && xpos < 396) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-FastRobot.png");
				}
			}
			else if((426 < xpos && xpos < 546) && (447 < ypos && ypos < 595)){
				if (input.isMouseButtonDown(0)) {
					view = new Image("res/Hints screens/Hints-TankRobot.png");
				}
			}
		}
			
		
		
	}
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

}
