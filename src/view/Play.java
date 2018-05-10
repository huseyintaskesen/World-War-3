/**
 *
 */
package view;

import java.awt.Font;
import java.util.Random;

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
	int[] humanCount;
	int first;
	int second;
	int firstRow;
	int secondRow;
	int robotCode;
	int minRobotCode;
	int maxRobotCode;
	Random rnd;
	int gameUpdateTime;

	Image view; // background image
	Image pause; // pause menu

	private GameManager gameManager;
	// private User user;

	private int selectedElement = -1;

	private boolean pauseFlag = false; // to determine whether the game is in pause menu

	private boolean demoFlag;

	public Play(int state) {
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		myFont = new TrueTypeFont(new Font("Pixeled Regular", Font.PLAIN, 30), true);

		// background and pause menu images
		// view = new Image("res/playgame.png");
		//view = new Image("res/Play-Survival.png");
		view = new Image("res/Play-Survival2.png");
		pause = new Image("res/pause.png");

		gameManager = GameManager.getInstance();
		// user = new User("Dummy");
		// gameManager.defineUser(user);

		gameManager.resetMap();
		// human numbers in a row
		humanCount = new int[4];
		rnd = new Random();
		minRobotCode = 1;
		maxRobotCode = 3;
		gameUpdateTime = 10000;

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		view.draw(0, 0); // background image drawn

		// temporary gray background
		// g.setColor(Color.lightGray);
		// g.fillRect(100, 100, 1180, 620);

		// game objects are drawn
		gameManager.draw(g);

		// pause menu is drawn when flag is up
		if (pauseFlag)
			pause.draw(500, 200);

		// display mouse coordinates
//		g.setColor(Color.white);
//		g.fillRect(300, 300, 150, 30);
		g.setColor(Color.black);
//		g.drawString(mouse, 300, 300);

		g.setFont(myFont);
		g.drawString("" + gameManager.getBalance(), 380, 30);
		g.drawString("Score: " + gameManager.getScore() / 1000, 600, 30);
		g.drawString("High Score: " + gameManager.getHighScore() / 1000, 600, 70);

		// Selected element rectangle
		if (selectedElement > 0) {
			g.setLineWidth(4);
			g.setColor(Color.orange);
			g.resetLineWidth();
			g.drawRect(17, 100 * selectedElement, 118, 100);
		} else if (selectedElement == 0) {// trash bin
			g.setLineWidth(3);
			g.setColor(Color.red);
			g.resetLineWidth();
			g.drawRect(135, 650, 50, 50);
		}

		// fast forward rectangle
		if (gameManager.isFastForward()) {
			g.setLineWidth(3);
			g.setColor(Color.blue);
			g.resetLineWidth();
			g.drawRect(1095, 20, 50, 50);
		}

		// sound rectangle
		if (!gc.isMusicOn() && !gc.isSoundOn()) {
			g.setLineWidth(3);
			g.setColor(Color.red);
			g.drawRect(1155, 20, 50, 50);
			g.drawLine(1200, 20, 1155, 70);
			g.resetLineWidth();
		}

		if (demoFlag) {
			g.setColor(Color.orange);
			g.drawString("Demo activated", 850, 70);
		}

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (gameManager.isFastForward())
			delta = delta * 2;

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
			if (timePassed2 > gameUpdateTime && !demoFlag) {
				// gameManager.gameUpdate(timePassed2);

				first = second = 9999;

				for (int i = 1; i < 5; i++) {
					humanCount[i - 1] = gameManager.humansInARow(i);
				}
				for (int j = 0; j < humanCount.length; j++) {
					if (humanCount[j] <= first) {
						first = humanCount[j];
						firstRow = j + 1;
					}
				}

				for (int k = 0; k < humanCount.length; k++) {
					if (humanCount[k] <= second && first < second) {
						second = humanCount[k];
						secondRow = k + 1;
					}
				}

				// for (int j = 1; j < humanCount.length + 1; j++) {
				// if (humanCount[j - 1] <= first) {
				// secondRow = j;
				// firstRow = j;
				// second = first;
				// first = humanCount[j - 1];
				// } else if (humanCount[j - 1] <= second ) {
				// secondRow = j;
				// second = humanCount[j - 1];
				// }
				//
				// }
				robotCode = rnd.nextInt((maxRobotCode - minRobotCode) + 1) + minRobotCode;
				gameManager.addRobot(robotCode, (1240), (125 * firstRow));// for least human counted row

				robotCode = rnd.nextInt((maxRobotCode - minRobotCode) + 1) + minRobotCode;
				gameManager.addRobot(robotCode, (1240), (125 * secondRow));// for second least human counted row

				// gameManager.addRobot(1,(1240), (188));//for first row
				// gameManager.addHuman(1,((1240 - 1240%100)), (190-190%100));// for second row
				// gameManager.addHuman(1,((1240 - 1240%100)), (310-310%100));// for third row
				// gameManager.addHuman(1,((1240 - 1240%100)), (410-410%100));// for fourth row
				// gameManager.addHuman(1,((1240 - 1240%100)), (510-510%100));// for fifth row

				timePassed2 = 0;
				gameUpdateTime = gameUpdateTime - 100;

			}

			if (timePassed > 16) {

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
						} else if (selectedElement == 0) {
							gameManager.removeHuman(xpos, ypos);
						} else
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
			if ((1040 < xpos && xpos < 1085) && (20 < ypos && ypos < 70)) {
				if (input.isMouseButtonDown(0)) {
					pauseFlag = true;
				}
			}

			// Sound button
			if ((1095 < xpos && xpos < 1145) && (20 < ypos && ypos < 70)) {
				if (input.isMousePressed(0)) {
					gameManager.setFastForward(!gameManager.isFastForward());

				}
			}

			// Sound button
			if ((1155 < xpos && xpos < 1200) && (20 < ypos && ypos < 70)) {
				if (input.isMousePressed(0)) {
					if (!gc.isMusicOn() && !gc.isSoundOn()) {
						gc.setMusicOn(true);
						gc.setSoundOn(true);
					} else {
						gc.setMusicOn(false);
						gc.setSoundOn(false);
					}
				}
			}

			// Quit button
			if ((1210 < xpos && xpos < 1260) && (20 < ypos && ypos < 70)) {
				if (input.isMouseButtonDown(0)) {
					gameManager.resetMap();
					sbg.enterState(0);
				}
			}

			if ((320 < xpos && xpos < 370) && (25 < ypos && ypos < 70)) {
				if (input.isMousePressed(0)) {
					if (!demoFlag) {
						demoFlag = true;
						gameManager.setBalance(99999);
					} else {
						gameManager.setBalance(250);
						demoFlag = false;
					}

				}

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