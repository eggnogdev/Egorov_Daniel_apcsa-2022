//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class BlinkyBall extends Ball {

  //constructors
  public BlinkyBall() {
    super();
  }

  public BlinkyBall(int x, int y) {
    super(x, y);
  }

  public BlinkyBall(int x, int y, Color col) {
    super(x, y, col);
  }

  public BlinkyBall(int x, int y, int wid, int ht) {
    super(x, y, wid, ht);
  }

  public BlinkyBall(int x, int y, int wid, int ht, int xSpd, int ySpd) {
    super(x, y, wid, ht, xSpd, ySpd);
  }

  public BlinkyBall(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd) {
    super(x, y, wid, ht, col, xSpd, ySpd);
  }

  public Color randomColor() {
    // use Math.random()
    int r = (int) (Math.random() * 256);
    int g = (int) (Math.random() * 256);
    int b = (int) (Math.random() * 256);
    return new Color(r, g, b);
  }

  public void moveAndDraw(Graphics window) {
    //draw a ball that is moving
    super.setColor(randomColor());
    super.moveAndDraw(window);
  }
}
