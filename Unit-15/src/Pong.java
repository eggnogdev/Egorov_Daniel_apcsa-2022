//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import static java.lang.Character.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Pong extends Canvas implements KeyListener, Runnable {

  private Ball ball;
  private Paddle leftPaddle;
  private Paddle rightPaddle;
  private boolean[] keys;
  private BufferedImage back;
  private Wall rightWall;
  private Wall leftWall;
  private Wall topWall;
  private Wall bottomWall;
  private int playerOneScore;
  private int playerTwoScore;

  public Pong() {
    //set up all variables related to the game
    this.ball = new Ball(200, 200, Color.orange);
    this.leftPaddle = new Paddle(10, 300, 10, 60, Color.blue, 5);
    this.rightPaddle = new Paddle(780, 300, 10, 60, Color.blue, 5);
    this.rightWall = new Wall(800, 0, 10, 600);
    this.leftWall = new Wall(0, 0, 10, 600);
    this.topWall = new Wall(0, 0, 800, 10);
    this.bottomWall = new Wall(0, 580, 800, 10);
    this.playerOneScore = 0;
    this.playerTwoScore = 0;
    keys = new boolean[4];

    setBackground(Color.WHITE);
    setVisible(true);

    new Thread(this).start();
    addKeyListener(this); //starts the key thread to log key strokes
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

    ball.moveAndDraw(graphToBack);
    leftPaddle.draw(graphToBack);
    rightPaddle.draw(graphToBack);

    //see if ball hits left wall or right wall
    if (ball.didCollideLeft(this.leftWall) || ball.didCollideRight(this.rightWall)) {
      if (ball.didCollideLeft(this.leftWall)) playerTwoScore++; else playerOneScore++;
      ball.draw(graphToBack, Color.WHITE);
      ball.setPos(200, 200);
      ball.setXSpeed(-ball.getXSpeed());
      leftPaddle.draw(graphToBack, Color.WHITE);
      rightPaddle.draw(graphToBack, Color.WHITE);
      leftPaddle.setPos(10, 300);
      rightPaddle.setPos(780, 300);
    }

    //see if the ball hits the top or bottom wall
    if (ball.didCollideTop(this.topWall) || ball.didCollideBottom(this.bottomWall)) {
      ball.setYSpeed(-ball.getYSpeed());
    }

    //see if the ball hits the left paddle
    if (ball.didCollideLeft(leftPaddle)) {
      ball.setXSpeed(-ball.getXSpeed());
    }
    //see if the ball hits the right paddle
    if (ball.didCollideRight(rightPaddle)) {
      ball.setXSpeed(-ball.getXSpeed());
    }

    //see if the paddles need to be moved
    if (keys[0] == true) leftPaddle.moveUpAndDraw(graphToBack);
    if (keys[1] == true) leftPaddle.moveDownAndDraw(graphToBack);
    if (keys[2] == true) rightPaddle.moveUpAndDraw(graphToBack);
    if (keys[3] == true) rightPaddle.moveDownAndDraw(graphToBack);

    twoDGraph.drawImage(back, null, 0, 0);
    twoDGraph.drawString("player one: " + playerOneScore, 400, 430);
    twoDGraph.drawString("player two: " + playerTwoScore, 400, 450);
  }

  public void keyPressed(KeyEvent e) {
    switch (toUpperCase(e.getKeyChar())) {
      case 'W':
        keys[0] = true;
        break;
      case 'Z':
        keys[1] = true;
        break;
      case 'I':
        keys[2] = true;
        break;
      case 'M':
        keys[3] = true;
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    switch (toUpperCase(e.getKeyChar())) {
      case 'W':
        keys[0] = false;
        break;
      case 'Z':
        keys[1] = false;
        break;
      case 'I':
        keys[2] = false;
        break;
      case 'M':
        keys[3] = false;
        break;
    }
  }

  public void keyTyped(KeyEvent e) {}

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(8);
        repaint();
      }
    } catch (Exception e) {}
  }
}
