/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Music;


public class Play extends BasicGameState {

	public String mouse = "No input yet"; // To show mouse coordinates, for testing purposes
	
	int timePassed=0;	// time passed is calculated so that we can take actions at certain times
	int timeCount=0;	// another variable time, used for creating bullets with a specified delay
	
	Image view;			// background image
	Image pause;		// pause menu
	//Casual casual;		// sample robot
	//Shooter shooter;	// sample human
	private Music music;
	

	
	ArrayList<AttackerHuman> humans;
	AttackerHuman shooter;
	
	ArrayList<Robot> robots;
	Robot casual;

	private boolean pauseFlag = false;	// to determine whether the game is in pause menu

	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		view = new Image("res/playgame.png");
		pause = new Image("res/pause.png");
		
		humans=new ArrayList<AttackerHuman>();
		robots = new ArrayList<Robot>();
		
		
		humans.add(new Shooter(100, 100) );
		humans.add(new Shooter(100, 200) );
		shooter=humans.get(0);
		
		robots.add(new Casual(600, 100));
		robots.add(new Casual(600, 200));
		casual=robots.get(0);
		
		//casual = new Casual(600, 100);
		//shooter = new Shooter(100, 100);
		music = new Music("res/Jamie and Selda.aif");
		music.loop();
		music.setVolume(0.6f);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0);	// background image drawn
		
		// game objects are drawn
		//casual.draw();
		//shooter.draw();
		
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).draw();
		}
		
		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).draw();
		}
		for (int i = 0; i < shooter.getBullets().size(); i++) {
			shooter.getBullets().get(i).draw();
		}
		
		// pause menu is drawn when flag is up
		if (pauseFlag)
			pause.draw(250, 200);

		// to show mouse coordinates
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//get mouse coordinates
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 720 - Mouse.getY();
		
		//calculate time passed
		timePassed+=delta;
		timeCount+=delta;
		
		//reset the timer when 0.02 seconds has passed
		// update the map every 0.02 seconds(50 FPS)
		if(timePassed>20) {
			// update the map
			casual.updateLocation();
			for (int i = 0; i < shooter.getBullets().size(); i++) {
				shooter.getBullets().get(i).updateLocation();
			}
			
			
			/////////////
			//collision detection logic
			/////////////
			// fire a bullet
			if((shooter.getX()+shooter.getRange())>casual.getX()&&(Math.abs(shooter.getY()-casual.getY())<20)&&timeCount>=1000) {
				shooter.attackToRobot(casual);
				timeCount=0;
				
			}
			// damage human as robot
			if(((shooter.getX()+60)>casual.getX())&&(Math.abs(shooter.getY()-casual.getY())<20)) {
				casual.stop();
				casual.attackToHuman(shooter);
				if(shooter.getHealth()<=0) {
					humans.remove(shooter);
					//shooter=new Shooter(100, 200);
					casual.run();
				}
			}
			// damage robots as bullet
			for (int i = 0; i < shooter.getBullets().size(); i++) {
				if((shooter.getBullets().get(i).getX()+25>=casual.getX())&&(Math.abs(shooter.getY()-casual.getY())<20)) {
					shooter.getBullets().get(i).damageRobot(casual, shooter);
					if(casual.getHealth()<=0) {
						casual = new Casual(600, 100);
					}
				}
			}
						
			// to display mouse coordiantes
			mouse = "x : " + xpos + " y : " + ypos;
			
			// reset the timer
			timePassed=0;
		}

		
		// Old back button
		// if ((50 < xpos && xpos < 150) && (50 < ypos && ypos < 100)) {
		// if (input.isMouseButtonDown(0)) {
		// sbg.enterState(0);
		// }
		// }

		// Pause button
		if ((1031 < xpos && xpos < 1095) && (15 < ypos && ypos < 79)) {
			if (input.isMouseButtonDown(0)) {
				pauseFlag = true;
			}
		}

		// Quit button
		if ((1203 < xpos && xpos < 1267) && (15 < ypos && ypos < 79)) {
			if (input.isMouseButtonDown(0) && !pauseFlag) {
				sbg.enterState(0);
			}
		}

		// Resume in pause menu
		if ((333 < xpos && xpos < 455) && (244 < ypos && ypos < 284)) {
			if (input.isMouseButtonDown(0)) {
				pauseFlag = false;
			}
		}

		// Quit in pause menu
		if ((333 < xpos && xpos < 455) && (291 < ypos && ypos < 332)) {
			if (input.isMouseButtonDown(0)) {
				pauseFlag = false;
				sbg.enterState(0);
			}
		}
	}

	public int getID() {
		return 1;
	}
}
