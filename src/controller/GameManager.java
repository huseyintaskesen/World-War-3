/**
 * 
 */
package controller;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.RangedAttacker;
import model.Casual;
import model.FastRobot;
import model.Freezer;
import model.HumanSide;
import model.LandMine;
import model.Laser;
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
	private static Laser[] lasers;

	private MapManager mapManager;
	private int score = 0;
	private int highScore = 0;

	private boolean[][] slotArray;
	private boolean fastForward;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private GameManager() {
		mapManager = MapManager.getInstance();

		// lists of game objects are initialized
		// humans = new ArrayList<RangedAttacker>();
		humans = new ArrayList<HumanSide>();
		robots = new ArrayList<RobotSide>();
		lasers = new Laser[4];
		slotArray = new boolean[4][12];
	}

	/* Static 'instance' method */
	public static GameManager getInstance() {
		return singleton;
	}

	public void defineUser(User u) {
		user = u;
	}

	public boolean gameUpdate(int delta) {
		score = score + delta;
		// update reload time
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);

			// update reload time
			if (tempHuman instanceof RangedAttacker) {
				RangedAttacker rangedAttacker = (RangedAttacker) tempHuman;
				rangedAttacker.setReloadTime(rangedAttacker.getReloadTime() + delta);

				// update bullet location
				for (int j = 0; j < rangedAttacker.getBullets().size(); j++) {
					rangedAttacker.getBullets().get(j).updateLocation();
				}
			} else if (tempHuman instanceof Miner) {
				Miner miner = (Miner) tempHuman;
				miner.setMineTimer(miner.getMineTimer() + delta);
			} else if (tempHuman instanceof LandMine) {
				LandMine landMine = (LandMine) tempHuman;
				landMine.setBombTimer(landMine.getBombTimer() + delta);
			} else if (tempHuman instanceof Swordsman) {
				Swordsman swordsman = (Swordsman) tempHuman;
				swordsman.setChargeTime(swordsman.getChargeTime() + delta);
			}

		}

		// update reload time of robots
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).setAttackTime(robots.get(i).getAttackTime() + delta);
		}

		// update the map
		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).updateLocation();
			float x = robots.get(i).getX();
			float y = robots.get(i).getY();
			if (x <= 230) {
				int laserPos = (int) (y / 125) - 1;

				if (lasers[laserPos] != null) {

					lasers[laserPos].damageRobots(robots);
					lasers[laserPos] = null;
					handleRobotRemovals();
				}
				// robots.get(i).takeDamage(9999);

				// lasers.set((int)(y/125)-1, null);
			}
			if (x <= 135) {
				if (score > highScore)
					highScore = score;
				return false;
			}
		}

		return true;
	}

	public void handleCollisions() throws SlickException {
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

				// ranged attacker collisions
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
						}

					}
				}

				// human robot collision
				if (((tempHuman.getX() + 60) > tempRobot.getX()) && tempHuman.getY() == tempRobot.getY()
						&& tempHuman.getX() - 10 <= tempRobot.getX() && tempRobot.getAttackTime() >= 1000) {

					if (!(meleeAttacker instanceof LandMine && !(((LandMine) meleeAttacker).isBombReady())))
						tempRobot.stop();

					// melee attacker collisions
					if (meleeAttacker != null) {
						if (meleeAttacker instanceof Swordsman) {
							// do something
							if (((Swordsman) meleeAttacker).getChargeTime() > 1000) {
								meleeAttacker.attackToRobot(tempRobot);
								((Swordsman) meleeAttacker).setChargeTime(0);
							}
						} else if (meleeAttacker instanceof LandMine)
							meleeAttacker.attackToRobot(tempRobot);
					}

					// damage human as robot
					// tempRobot.stop();
					tempRobot.attackToHuman(tempHuman);

					tempRobot.setAttackTime(0);
				}

				handleRobotRemovals();
				handleHumanRemovals();
			}
		}
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
				slotArray[(int) (tempHuman.getY() / 125) - 1][(int) (tempHuman.getX() / 125) - 2] = false;
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

			switch (humanCode) {
			case 1:
				humans.add(new Miner(fixedX, fixedY));
				cost = Miner.cost;
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 2:
				humans.add(new Swordsman(fixedX, fixedY));
				cost = Swordsman.cost;
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 3:
				humans.add(new Freezer(fixedX, fixedY));
				cost = Freezer.cost;
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 4:
				humans.add(new Shooter(fixedX, fixedY));
				cost = Shooter.cost;
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 5:
				humans.add(new Obstacle(fixedX, fixedY));
				cost = Obstacle.cost;
				slotArray[fixedY / 125 - 1][fixedX / 125 - 2] = true;
				break;
			case 6:
				humans.add(new LandMine(fixedX, fixedY));
				cost = LandMine.cost;
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
			cost = Miner.cost;
			break;
		case 2:
			cost = Swordsman.cost;
			break;
		case 3:
			cost = Freezer.cost;
			break;
		case 4:
			cost = Shooter.cost;
			break;
		case 5:
			cost = Obstacle.cost;
			break;
		case 6:
			cost = LandMine.cost;
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
			if (slotArray[row - 1][i] == true)
				count++;
		}
		return count;
	}

	public void collectMine(int x, int y) {
		for (int i = 0; i < humans.size(); i++) {
			if (humans.get(i) instanceof Miner) {
				Miner miner = (Miner) humans.get(i);
				if ((miner.getX() == (x - x % 125)) && (miner.isMineReady()) && ((miner.getY() == (y - y % 125)))) {
					updateBalance(50);
					miner.resetTimer();
				}
			}
		}

	}

	public void removeHuman(int x, int y) {
		for (int i = 0; i < humans.size(); i++) {
			HumanSide tempHuman = humans.get(i);
			if ((tempHuman.getX() == (x - x % 125)) && ((tempHuman.getY() == (y - y % 125)))) {
				tempHuman.setToBeRemoved();
			}
		}
		handleHumanRemovals();
	}

	public void resetMap() throws SlickException {
		user.reset();
		fastForward= false;
		score = 0;
		humans.clear();
		// humans.add(new Shooter(100, 100));
		robots.clear();
		// robots.add(new Casual(600, 100));

		// initialize 4 lasers
		lasers[0] = new Laser(125, 125);
		lasers[1] = new Laser(125, 250);
		lasers[2] = new Laser(125, 375);
		lasers[3] = new Laser(125, 500);

		// reset the slot array
		for (int i = 0; i < slotArray.length; i++) {
			for (int j = 0; j < slotArray[i].length; j++) {
				slotArray[i][j] = false;
			}
		}

	}

	// to draw game objects
	public void draw(Graphics g) {
		mapManager.drawHumans(humans, g);
		mapManager.drawRobots(robots, g);
		mapManager.drawLasers(lasers, g);
	}

	public int getScore() {
		return score;
	}

	public int getHighScore() {
		return highScore;
	}

	/**
	 * @return
	 */
	public int getBalance() {
		return user.getBalance();
	}

	/**
	 * @param i
	 */
	public void setBalance(int i) {
		user.setBalance(i);

	}

	/**
	 * @return
	 */
	public String getName() {

		return user.getName();
	}

	/**
	 * @return
	 */
	public boolean isFastForward() {
		return fastForward;
	}

	/**
	 * @param b
	 */
	public void setFastForward(boolean fastForward) {
		this.fastForward = fastForward;

	}

}
