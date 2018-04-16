/**
 * 
 */
package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;



public class MainMenu extends BasicGameState {

	Image menu;
	public String mouse= "No input yet";
	private TextField text1;
	private UnicodeFont font = getNewFont("Arial" , 30);
	private String userName;


	public MainMenu(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		menu = new Image("res/main menu.png");
		font.loadGlyphs();
		text1 = new TextField(gc, font, 932,257,195,30);


	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Humans vs. Robots", 100, 50);
		menu.draw(0, 0);
		text1.render(gc, g);


//		playNow.draw(100,100);
//		quit.draw(100,200);
		
//		g.fillOval(75, 100, 100, 100);
//		g.drawString("Play", 80, 70);
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
		g.setColor(Color.white);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 720 - Mouse.getY();
		mouse = "x : "+xpos+" y : "+ypos;


		if ((907 < xpos && xpos < 1154) && (300 < ypos && ypos < 353)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		else if ((661 < xpos && xpos < 858) && (480 < ypos && ypos < 520)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
			}
		}
		else if ((661 < xpos && xpos < 858) && (530 < ypos && ypos < 568)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(3);
			}
		}
		else if ((661 < xpos && xpos < 858) && (583 < ypos && ypos < 620)) {
			if (input.isMouseButtonDown(0)) {
				gc.exit();
			}
		}
		if ((932 < xpos && xpos < 1132) && (256 < ypos && ypos < 292)) {
			if (input.isMouseButtonDown(0)) {
				text1.setTextColor(Color.orange);

			}
		}

	}

	public int getID() {
		return 0;
	}
	public UnicodeFont getNewFont(String fontName , int fontSize){
		UnicodeFont returnFont = new UnicodeFont(new Font(fontName , Font.BOLD , fontSize));
		returnFont.addAsciiGlyphs();
		returnFont.getEffects().add(new ColorEffect(java.awt.Color.red));
		return (returnFont);
	}


}
