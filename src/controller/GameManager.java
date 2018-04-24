/**
 * 
 */
package controller;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import model.RangedAttacker;
import model.Casual;
import model.FastRobot;
import model.Freezer;
import model.HumanSide;
import model.LandMine;
import model.MeleeAttacker;
import model.Miner;
import model.Obstacle;
import model.Passive;
import model.RobotSide;
import model.Shooter;
import model.Swordsman;
import model.TankRobot;
import model.User;
import view.MapManager;

/**
 * @author ibrahim
 *
 */
public class GameManager {

	private static GameManager singleton = new GameManager();
	private User user;

	// declare lists of game objects
	// private static ArrayList<RangedAttacker> humans;
	private static ArrayList<HumanSide> humans;
	private static ArrayList<RobotSide> robots;

	private MapManager mapManager;
	private int score = 0;

	private boolean[][] slotArray;

	int firstRow = 0, secondRow = 0, thirdRow = 0, fourthRow = 0, fifthRow = 0, sixthRow = 0;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private GameManager() {
		mapManager = MapManager.getInstance();

		// lists of game objects are initialized
		// humans = new ArrayList<RangedAttacker>();
		humans = new ArrayList<HumanSide>();
		robots = new ArrayList<RobotSide>();
		slotArray = new boolean[4][12];
	}

	/* Static 'instance' method */
	public static GameManager getInstance() {
		return singleton;
	}

	public void defineUser(User u) {
		user = u;
	}

	public void gameUpdate(int delta) {
		// update reload time
		for (int i = 0; i < humans.size(); i++) {
			if (humans.get(i) instanceof RangedAttacker) {
				RangedAttacker tempHuman = (RangedAttacker) humans.get(i);
				tempHuman.setReloadTime(tempHuman.getReloadTime() + delta);

				// update bullet location
				for (int j = 0; j < tempHuman.getBullets().size(); j++) {
					tempHuman.getBullets().get(j).updateLocation();
				}
			}
		}
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).setAttackTime(robots.get(i).getAttackTime() + delta);
		}

		// update the map
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).updateLocation();
		}
		// for (int i = 0; i < humans.size(); i++) {
		// for (int j = 0; j < humans.get(i).getBullets().size(); j++) {
		// humans.get(i).getBullets().get(j).updateLocation();
		// }
		// }
	}

	public boolean handleCollisions() throws SlickException {
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);
			RangedAttacker rangedAttacker = null;
			Passive passive = null;
			MeleeAttacker meleeAttacker = null;
			if (tempHuman instanceof RangedAttacker)
				rangedAttacker = (RangedAttacker) tempHuman;
			if (tempHuman instanceof MeleeAttacker)
				meleeAttacker = (MeleeAttacker) tempHuman;
			if (tempHuman instanceof Passive)
				passive = (Passive) tempHuman;

			for (int j = 0; j < robots.size(); j++) {
				RobotSide tempRobot = robots.get(j);

				// gameover when one robot reaches basement
				if (tempRobot.getX() <= 100) {

					return false;
				}

				if (rangedAttacker != null) {

					// fire a bullet
					if ((rangedAttacker.getX() + rangedAttacker.getRange()) > tempRobot.getX()
							&& rangedAttacker.getX() + 20 < tempRobot.getX()
							&& (Math.abs(rangedAttacker.getY() - tempRobot.getY()) < 20)
							&& rangedAttacker.getReloadTime() >= 1000) {
						rangedAttacker.attackToRobot(tempRobot);
						rangedAttacker.setReloadTime(0);

					}

					// damage robot as bullet
					for (int k = 0; k < rangedAttacker.getBullets().size(); k++) {
						if ((rangedAttacker.getBullets().get(k).getX() + 25 >= tempRobot.getX())
								&& rangedAttacker.getY() == tempRobot.getY()
								&& rangedAttacker.getBullets().get(k).getX() - 10 <= tempRobot.getX()) {
							rangedAttacker.getBullets().get(k).damageRobot(tempRobot, rangedAttacker);
							// if (tempRobot.getHealth() <= 0) {
							// // robots.remove(tempRobot);
							// tempRobot.setToBeRemoved();
							// }

						}

					}
				}

				handleRobotRemovals();
				// damage human as robot
				if (((tempHuman.getX() + 60) > tempRobot.getX()) && tempHuman.getY() == tempRobot.getY()
						&& tempHuman.getX() - 10 <= tempRobot.getX() && tempRobot.getAttackTime() >= 1000) {

					tempRobot.stop();
					tempRobot.attackToHuman(tempHuman);

					tempRobot.setAttackTime(0);
				}

				handleHumanRemovals();
			}
		}
		return true;
	}

	public void handleHumanRemovals() {
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);
			if (tempHuman.isToBeRemoved()) {
				for (int j = 0; j < robots.size(); j++) {
					RobotSide tempRobot = robots.get(j);

					// make the stopped robots move
					if (((tempHuman.getX() + 60) > tempRobot.getX()) && tempHuman.getY() == tempRobot.getY()
							&& tempHuman.getX() - 10 <= tempRobot.getX()) {
						tempRobot.run();
					}
				}

				// delete marked humans
				humans.remove(i);
				i--;
			}

		}

	}

	public void handleRobotRemovals() {
		for (int i = 0; i < robots.size(); i++) {
			RobotSide tempRobot = robots.get(i);

			// delete marked robots
			if (tempRobot.isToBeRemoved()) {
				robots.remove(i);
				i--;
			}
		}
	}

	public void addHuman(int humanCode, int xpos, int ypos) throws SlickException {
		int fixedX = (xpos - xpos % 125);
		int fixedY = (ypos - ypos % 125);

		if (checkBalance(humanCode) && checkSlot(fixedX, fixedY)) {
			int cost = 0;
			// if( (ypos - ypos % 100) >= 100 && (ypos - ypos % 100) <= 200);
			// firstRow++;
			// if( (ypos - ypos % 100) >= 200 && (ypos - ypos % 100) <= 300);
			// secondRow++;
			// if( (ypos - ypos % 100) >= 300 && (ypos - ypos % 100) <= 400);
			// thirdRow++;
			// if( (ypos - ypos % 100) >= 400 && (ypos - ypos % 100) <= 500);
			// fourthRow++;
			// if( (ypos - ypos % 100) >= 500 && (ypos - ypos % 100) <= 600);
			// fifthRow++;
			// if( (ypos - ypos % 100) >= 600 && (ypos - ypos % 100) <= 700);
			// sixthRow++;

			if (fixedY == 100)// bu deðerleri fixlediðimiz için 100 ile 200 arasýnda olamaz zaten 100,200 gibi
								// tam katlar olacak hep
				firstRow++;
			////// Böyle devam edebiliriz ama bence 6 farklý variable ile tutmak çok
			////// mantýklý deðil
			////// bir array yapabiliriz ya da ben boolean arrayi düþünüyorum slot kontrol
			////// etmek için
			////// direk o arrayden çekebiliriz deðerleri

			switch (humanCode) {
			case 1:
				humans.add(new Miner(fixedX, fixedY));
				cost = Miner.getCost();
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 2:
				humans.add(new Swordsman(fixedX, fixedY));
				cost = Swordsman.getCost();
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 3:
				humans.add(new Freezer(fixedX, fixedY));
				cost = Freezer.getCost();
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 4:
				humans.add(new Shooter(fixedX, fixedY));
				cost = Shooter.getCost();
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 5:
				humans.add(new Obstacle(fixedX, fixedY));
				cost = Obstacle.getCost();
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 6:
				humans.add(new LandMine(fixedX, fixedY));
				cost = LandMine.getCost();
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;

			default:
				// slotArray[fixedY/125][fixedX/125]=false;
				break;
			}
			updateBalance(-cost);
		}
	}

	public void updateBalance(int change) {
		user.setBalance(user.getBalance() + change);
	}

	// public void addRobot(RobotSide robot) {
	// robots.add(robot);
	// }

	public void addRobot(int robotCode, int xpos, int ypos) throws SlickException {
		int fixedX = (xpos - xpos % 125);
		int fixedY = (ypos - ypos % 125);

		switch (robotCode) {
		case 1:
			robots.add(new Casual(fixedX, fixedY));
			break;
		case 2:
			robots.add(new FastRobot(fixedX, fixedY));
			break;
		case 3:
			robots.add(new TankRobot(fixedX, fixedY));
			break;

		default:
			break;
		}
	}

	public boolean checkBalance(int humanCode) {
		int cost = 0;
		switch (humanCode) {
		case 1:
			cost = Miner.getCost();
			break;
		case 2:
			cost = Swordsman.getCost();
			break;
		case 3:
			cost = Freezer.getCost();
			break;
		case 4:
			cost = Shooter.getCost();
			break;
		case 5:
			cost = Obstacle.getCost();
			break;
		case 6:
			cost = LandMine.getCost();
			break;

		default:
			break;
		}

		if (user.getBalance() < cost) {
			System.out.println("not enough diamonds!");
			return false;
		} else
			return true;
	}

	public boolean checkSlot(int x, int y) {
		if (slotArray[y / 125 - 1][x / 125 - 2] == true)
			return false;
		else
			return true;
	}

	public int humansInARow(int row) {
		int count = 0;
		for (int i = 0; i < 12; i++) {
			if (slotArray[row - 1][i] == false)
				count++;
		}
		return count;
	}

	public void resetMap() throws SlickException {
		score = 0;
		humans.clear();
		// humans.add(new Shooter(100, 100));
		robots.clear();
		// robots.add(new Casual(600, 100));
	}

	// to draw game objects
	public void draw() {
		mapManager.drawHumans(humans);
		mapManager.drawRobots(robots);
	}

}
