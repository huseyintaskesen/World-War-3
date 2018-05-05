/**
 *
 */
package view;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import controller.GameManager;
import model.User;

public class Play extends BasicGameState {

	TrueTypeFont myFont;//

	public String mouse = "No input yet"; // To show mouse coordinates, for testing purposes

	int timePassed = 0; // time passed is calculated so that we can take actions at certain times
	int timePassed2 = 0;
	int timeCount = 0; // another variable time, used for creating bullets with a specified delay
	int score = 0;
	int rowNum=1;
	int humanCount;
	// int score = 0;

	Image view; // background image
	Image pause; // pause menu
	Image land;
	private Music music;

	// declare lists of game objects
	// ArrayList<AttackerHuman> humans;
	// ArrayList<Robot> robots;

	private GameManager gameManager;
	private User user;

	private int selectedElement = -1;

	private boolean pauseFlag = false; // to determine whether the game is in pause menu

	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		myFont = new TrueTypeFont(new Font("Pixeled Regular", Font.PLAIN, 30), true);

		// background and pause menu images
		// view = new Image("res/playgame.png");
		view = new Image("res/Play-Survival2.png");
		pause = new Image("res/pause.png");

		// // lists of game objects are initialized
		// humans = new ArrayList<AttackerHuman>();
		// robots = new ArrayList<Robot>();

		gameManager = GameManager.getInstance();
		user = new User("Dummy");// TODO name
		gameManager.defineUser(user);

		land = new Image("res/Land.png");

		gameManager.resetMap();

		// music
		music = new Music("res/soundtrack.aiff");
		music.loop();
		music.setVolume(0.0f);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0); // background image drawn

		// temporary gray background
		// g.setColor(Color.lightGray);
		// g.fillRect(100, 100, 1180, 620);

		// land.draw(100,100,1180,620);

		// game objects are drawn
		gameManager.draw(g);

		// pause menu is drawn when flag is up
		if (pauseFlag)
			pause.draw(500, 200);

		// display mouse coordinates
		g.setColor(Color.white);
		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
		g.drawString(mouse, 300, 300);

		g.setFont(myFont);
		g.drawString("" + user.getBalance(), 380, 30);
		g.drawString("Score: " + gameManager.getScore() / 1000, 600, 30);
		g.drawString("High Score: " + gameManager.getHighScore() / 1000, 600, 70);

		// Selected element rectangle
		if (selectedElement > 0) {
			g.setLineWidth(4);
			g.setColor(Color.orange);
			g.resetLineWidth();
			g.drawRect(17, 100 * selectedElement, 118, 100);
		}
		else if (selectedElement == 0) {
			g.setLineWidth(3);
			g.setColor(Color.red);
			g.resetLineWidth();
			g.drawRect(135, 650, 50, 50);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// get mouse coordinates
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = 720 - Mouse.getY();
		// to display mouse coordinates
		mouse = "x : " + xpos + " y : " + ypos;

		// this if is taken when the pause menu is present
		// if statement will only take care of these buttons
		// All the update related to game will be in else part, i. e. when flag is down
		if (pauseFlag) {
			// Resume in pause menu
			if ((576 < xpos && xpos < 776) && (285 < ypos && ypos < 335)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = false;
				}
			}

			// Main Menu in pause menu
			if ((576 < xpos && xpos < 776) && (365 < ypos && ypos < 415)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = false;
					gameManager.resetMap();
					sbg.enterState(0);
				}
			}

			// Quit in pause menu
			if ((576 < xpos && xpos < 776) && (445 < ypos && ypos < 495)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = false;
					gc.exit();
				}
			}

		} else {// Pause flag is down, game is running

			// calculate time passed
			timePassed += delta;
			timePassed2 += delta;
			// for (int i = 0; i < humans.size(); i++) {
			// humans.get(i).setReloadTime(humans.get(i).getReloadTime()+delta);
			// }
			// timeCount += delta;
			// score += delta;

			// reset the timer when 0.02 seconds has passed
			// update the map every 0.02 seconds(50 FPS)
			if(timePassed2>3000) {
				//gameManager.gameUpdate(timePassed2);

				if (rowNum == 5) {
					rowNum = 1;
				} else
					humanCount = gameManager.humansInARow(rowNum);

				for (int i = 0; i < humanCount + 1; i++) {
					if (rowNum == 1) {
						gameManager.addRobot(1, (1240), (188));//for first row
					}
					if (rowNum == 2) {
						gameManager.addRobot(1, (1240), (310));//for second row

				}
			}

					//gameManager.addRobot(1,(1240), (188));//for first row
//					gameManager.addHuman(1,((1240 - 1240%100)), (190-190%100));// for second row
//					gameManager.addHuman(1,((1240 - 1240%100)), (310-310%100));// for third row
//					gameManager.addHuman(1,((1240 - 1240%100)), (410-410%100));// for fourth row
//					gameManager.addHuman(1,((1240 - 1240%100)), (510-510%100));// for fifth row
					
					timePassed2=0;
					rowNum++;
				}

			if (timePassed2 > 3000) {
				// gameManager.gameUpdate(timePassed2);

				// gameManager.addRobot(new Casual((1220 - 1220%100), (110 - 110%100)));// for
				// first row
				// gameManager.addRobot(new Casual((1220 - 1220%100), (190-190%100)));// for
				// second row
				// gameManager.addRobot(new Casual((1220 - 1220%100), (290-290%100)));// for
				// third row
				// gameManager.addRobot(new Casual((1220 - 1220%100), (410-410%100)));// for
				// fourth row
				// gameManager.addRobot(new Casual((1220 - 1220%100), (510-510%100)));// for
				// fifth row

				timePassed2 = 0;
			}
			if (timePassed > 20) {

				if (!gameManager.gameUpdate(timePassed))
					gameover(sbg);
				// add human or robot simply by clicking the corresponding mouse button
				// added for first iteration demo, testing purposes
				if ((250 < xpos && xpos < 1250) && (125 < ypos && ypos < 625)) {
					if (input.isMousePressed(0)) {
						// gameManager.addAttackerHuman(new Shooter((xpos - xpos % 100), (ypos - ypos %
						// 100)));
						if (selectedElement == -1) {
							gameManager.collectMine(xpos, ypos);
						}
						else if (selectedElement == 0) {
							gameManager.removeHuman(xpos,ypos);
						}
						else
							gameManager.addHuman(selectedElement, xpos, ypos);
						// humans.add(new Shooter((xpos - xpos % 100), (ypos - ypos % 100)));
					} else if (input.isMousePressed(1)) {

						gameManager.addRobot(1, xpos, ypos);
						// robots.add(new Casual((xpos - xpos % 100), (ypos - ypos % 100)));
					}
				}

				/////////////
				// collision detection logic
				/////////////

				gameManager.handleCollisions();

				// reset the timer
				timePassed = 0;
			}
			// timePassed2=0;

			// Pause button
			if ((1031 < xpos && xpos < 1095) && (15 < ypos && ypos < 79)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = true;
				}
			}

			// Quit button
			if ((1203 < xpos && xpos < 1267) && (15 < ypos && ypos < 79)) {
				if (input.isMouseButtonDown(0)) {
					gameManager.resetMap();
					sbg.enterState(0);
				}
			}

			if ((320 < xpos && xpos < 370) && (25 < ypos && ypos < 70)) {
				if (input.isMouseButtonDown(0))
					user.setBalance(99999);
			}
			/////////////
			/// Human Selection
			///////////
			if ((17 < xpos && xpos < 135) && (100 < ypos && ypos < 200)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 1;
				}
			}

			else if ((17 < xpos && xpos < 135) && (200 < ypos && ypos < 300)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 2;
				}
			}

			else if ((17 < xpos && xpos < 135) && (300 < ypos && ypos < 400)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 3;
				}
			}

			else if ((17 < xpos && xpos < 135) && (400 < ypos && ypos < 500)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 4;
				}
			}

			else if ((17 < xpos && xpos < 135) && (500 < ypos && ypos < 600)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 5;
				}
			}

			else if ((17 < xpos && xpos < 135) && (600 < ypos && ypos < 700)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 6;
				}
			}

			else if ((135 < xpos && xpos < 185) && (650 < ypos && ypos < 700)) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = 0;
				}
			}
			// If clicked outside of the list, selection disappears
			else if (!((250 < xpos && xpos < 1250) && (125 < ypos && ypos < 625))) {
				if (input.isMouseButtonDown(0)) {
					selectedElement = -1;
				}
			}
		} // end of else of pause menu
	}

	/**
	 * @throws SlickException
	 *
	 */
	private void gameover(StateBasedGame sbg) throws SlickException {
		sbg.enterState(4);
	}

	public int getID() {
		return 1;
	}
}