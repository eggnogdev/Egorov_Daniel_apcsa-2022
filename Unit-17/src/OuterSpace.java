//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import static java.lang.Character.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

  private Ship ship;

  private Bullets shots;
  private AlienHorde horde;

  private boolean[] keys;
  private BufferedImage back;

  private int score;
  private int alienCount;
  private int lives;

  private Ship powerUp;

  public OuterSpace() {
    setBackground(Color.black);

    keys = new boolean[5];
    score = 0;
    lives = 1;

    //instantiate other instance variables
    //Ship, Alien
    ship = new Ship(400, 500, 50, 50, 1, "ship.jpg");
    powerUp = new Ship((int) (Math.random() * 600 + 50), (int) (Math.random() * 400 + 50), 50, 50, 0, "pu.jpg");
    shots = new Bullets();
    horde = new AlienHorde(34);
    alienCount = horde.getList().size();

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window) {
    paint(window);
  }

  public void paint(Graphics window) {
    //set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D) window;

    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if (back == null) back = (BufferedImage) (createImage(getWidth(), getHeight()));

    //create a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();

    graphToBack.setColor(Color.BLUE);
    graphToBack.drawString("StarFighter ", 25, 50);
    graphToBack.setColor(Color.BLACK);
    graphToBack.fillRect(0, 0, 800, 600);

    powerUp.draw(graphToBack);
    shots.drawEmAll(graphToBack);
    shots.moveEmAll();
    horde.drawEmAll(graphToBack);
    horde.moveEmAll();
    horde.removeDeadOnes(shots.getList());
    graphToBack.drawString("Score: " + horde.getScore(), 700, 50);
    ship.draw(graphToBack);

    // for some reason the -1 has to be there
    // dont know why.... the size is +1 the inputted size...
    if (horde.getList().size() - 1 == 0) {
      ship.setSpeed(0);
      graphToBack.setColor(Color.green);
      graphToBack.fillRect(0, 0, 800, 600);
      graphToBack.setColor(Color.white);
      graphToBack.setFont(new Font("Times New Roman", Font.PLAIN, 48));
      graphToBack.drawString("You Win!", 300, 300);
      graphToBack.drawString("Score: " + horde.getScore(), 300, 400);

      // prevent shots from being fired to affect the score post game
      keys[4] = false;
    }

    if (lives == 0) {
      horde.removeEmAll();
      ship.setSpeed(0);
      graphToBack.setColor(Color.red);
      graphToBack.fillRect(0, 0, 800, 600);
      graphToBack.setColor(Color.white);
      graphToBack.setFont(new Font("Times New Roman", Font.PLAIN, 48));
      graphToBack.drawString("Game Over", 300, 300);
      graphToBack.drawString("Score: " + horde.getScore(), 300, 400);
      // prevent shots from being fired to affect the score post game
      keys[4] = false;
    }

    if (keys[0] && ship.getX() + ship.getSpeed() > 0) {
      ship.move("left");
    }
    if (keys[1] && ship.getX() + ship.getSpeed() + ship.getWidth() < 800) {
      ship.move("right");
    }
    if (keys[2] && ship.getY() + ship.getSpeed() > 0) {
      ship.move("up");
    }
    if (keys[3] && ship.getY() + ship.getSpeed() + (ship.getHeight() * 2) < 600) {
      ship.move("down");
    }
    if (keys[4]) {
      shots.add(new Ammo(ship.getX() + (ship.getWidth() / 2), ship.getY() + (ship.getHeight() / 2), 5));
      keys[4] = false;
      horde.setScore(horde.getScore() - 1);
    }

    // check if the ship collides with powerUp
    if (
      ship.getX() + ship.getWidth() > powerUp.getX() &&
      ship.getX() < powerUp.getX() + powerUp.getWidth() &&
      ship.getY() + ship.getHeight() > powerUp.getY() &&
      ship.getY() < powerUp.getY() + powerUp.getHeight()
    ) {
      powerUp.setX((int) (Math.random() * 600 + 50));
      powerUp.setY((int) (Math.random() * 400 + 50));
      ship = new Ship(ship.getX(), ship.getY(), 50, 50, ship.getSpeed(), "ship.jpgWithShield.jpg");
      lives = 2;
    }

    if (horde.collision(ship)) {
      lives--;
      ship = new Ship(ship.getX(), ship.getY(), 50, 50, ship.getSpeed(), "ship.jpg");
      ship.setPos(400, 500);
    }


    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[4] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[4] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e) {}

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5);
        repaint();
      }
    } catch (Exception e) {}
  }
}
