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

public class PaddleTestTwo extends Canvas implements KeyListener, Runnable {

  private Ball ball;
  private Paddle leftPaddle;
  private Paddle rightPaddle;
  private boolean[] keys; //keeps track of what keys are pressed

  public PaddleTestTwo() {
    //set up all game variables

    //instantiate a Ball
    ball = new Ball(400, 300, Color.red);
    //instantiate a left Paddle
    leftPaddle = new Paddle(10, 300, 10, 60, Color.blue);
    //instantiate a right Paddle
    rightPaddle = new Paddle(550, 300, 10, 60, Color.blue);
    keys = new boolean[5];

    //set up the Canvas
    setBackground(Color.WHITE);
    setVisible(true);

    this.addKeyListener(this);
    new Thread(this).start();
  }

  public void update(Graphics window) {
    paint(window);
  }

  public void paint(Graphics window) {
    ball.moveAndDraw(window);
    leftPaddle.draw(window);
    rightPaddle.draw(window);

    if (!(ball.getX() >= 10 && ball.getX() <= 550)) {
      ball.setXSpeed(-ball.getXSpeed());
    }

    if (!(ball.getY() >= 10 && ball.getY() <= 450)) {
      ball.setYSpeed(-ball.getYSpeed());
    }

    if (
      ball.getX() <= leftPaddle.getX() + leftPaddle.getWidth() + Math.abs(ball.getXSpeed()) &&
      (
        ball.getY() >= leftPaddle.getY() &&
        ball.getY() <= leftPaddle.getY() + leftPaddle.getHeight() ||
        ball.getY() + ball.getHeight() >= leftPaddle.getY() &&
        ball.getY() + ball.getHeight() < leftPaddle.getY() + leftPaddle.getHeight()
      )
    ) {
      if (ball.getX() <= leftPaddle.getX() + leftPaddle.getWidth() - Math.abs(ball.getXSpeed())) ball.setYSpeed(
        -ball.getYSpeed()
      ); else ball.setXSpeed(-ball.getXSpeed());
    }

    if (
      ball.getX() >= rightPaddle.getX() - rightPaddle.getWidth() - Math.abs(ball.getXSpeed()) &&
      (
        ball.getY() >= rightPaddle.getY() &&
        ball.getY() <= rightPaddle.getY() + rightPaddle.getHeight() ||
        ball.getY() + ball.getHeight() >= rightPaddle.getY() &&
        ball.getY() + ball.getHeight() < rightPaddle.getY() + rightPaddle.getHeight()
      )
    ) {
      if (ball.getX() >= rightPaddle.getX() - rightPaddle.getWidth() + Math.abs(ball.getXSpeed())) ball.setYSpeed(
        -ball.getYSpeed()
      ); else ball.setXSpeed(-ball.getXSpeed());
    }

    if (keys[0] == true) {
      //move left paddle up and draw it on the window
      if (leftPaddle.getY() - leftPaddle.getSpeed() > 0) leftPaddle.moveUpAndDraw(window);
    }
    if (keys[1] == true) {
      //move left paddle down and draw it on the window
      if (leftPaddle.getY() + leftPaddle.getSpeed() + leftPaddle.getHeight() < 450) leftPaddle.moveDownAndDraw(window);
    }
    if (keys[2] == true) {
      if (rightPaddle.getY() - rightPaddle.getSpeed() > 0) rightPaddle.moveUpAndDraw(window);
    }
    if (keys[3] == true) {
      if (rightPaddle.getY() + rightPaddle.getSpeed() + rightPaddle.getHeight() < 450) rightPaddle.moveDownAndDraw(
        window
      );
    }
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
