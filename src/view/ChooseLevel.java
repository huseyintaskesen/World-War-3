//package view;
//
//import org.lwjgl.input.Mouse;
//import org.newdawn.slick.Color;
//import org.newdawn.slick.GameContainer;
//import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Image;
//import org.newdawn.slick.Input;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.state.BasicGameState;
//import org.newdawn.slick.state.StateBasedGame;
//
//public class ChooseLevel extends BasicGameState{
//	
//	Image view;
//	public String mouse= "No input yet";
//	
//	public ChooseLevel(int state) {
//		
//	}
//	
//	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
//		view = new Image("res/chooseLevel.png");
//	}
//	
//	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
//		view.draw(0, 0);
//		
//		g.setColor(Color.white);
//		g.fillRect(300, 300, 150, 30);
//		g.setColor(Color.black);
//		g.drawString(mouse, 300, 300);
//	}
//	
//	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
//		Input input = gc.getInput();
//		int xpos = Mouse.getX();
//		int ypos = 720 - Mouse.getY();
//		mouse = "x : "+xpos+" y : "+ypos;
//		
//		//survival mode is loaded
//		if ((500 < xpos && xpos < 800) && (400 < ypos && ypos < 450)){
//			if (input.isMouseButtonDown(0)) {
//				view = new Image("res/Play-Survival2.png");
//				sbg.enterState(1);
//			}
//		}
//		
//		//Level mode is loaded
//		else {
//			if ((540 < xpos && xpos < 590) && (250 < ypos && ypos < 300)){
//				if (input.isMouseButtonDown(0)) {
//					view = new Image("res/Play-Level1.png");
//					sbg.enterState(1);
//				}
//			}
//			
//			if ((625 < xpos && xpos < 675) && (250 < ypos && ypos < 300)){
//				if (input.isMouseButtonDown(0)) {
//					view = new Image("res/Play-Level2.png");
//					sbg.enterState(1);
//				}
//			}
//			
//			if ((710 < xpos && xpos < 760) && (250 < ypos && ypos < 300)){
//				if (input.isMouseButtonDown(0)) {
//					view = new Image("res/Play-Level3.png");
//					sbg.enterState(1);
//				}
//			}
//		}
//	}
//	
//	public int getID() {
//		// TODO Auto-generated method stub
//		return 6;
//	}
//
//}