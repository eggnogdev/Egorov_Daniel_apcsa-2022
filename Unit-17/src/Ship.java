//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {

  private int speed;
  private Image image;

  public Ship() {
    this(10, 10, 10, 10, 10, "ship.jpg");
  }

  public Ship(int x, int y, String f) {
    //add code here
    this(x, y, 10, 10, 10, f);
  }

  public Ship(int x, int y, int s, String f) {
    //add code here
    this(x, y, 10, 10, s, f);
  }

  public Ship(int x, int y, int w, int h, int s, String f) {
    super(x, y, w, h);
    speed = s;
    try {
      URL url = getClass().getResource(f);
      image = ImageIO.read(url);
    } catch (Exception e) {
      //feel free to do something here
      System.out.println(e);
    }
  }

  public void setSpeed(int s) {
    //add more code
    speed = s;
  }

  public int getSpeed() {
    return speed;
  }

  public void move(String direction) {
    //add code here
    switch (direction) {
      case "up":
        setY(getY() - speed);
        break;
      case "down":
        setY(getY() + speed);
        break;
      case "left":
        setX(getX() - speed);
        break;
      case "right":
        setX(getX() + speed);
        break;
    }
  }

  public void draw(Graphics window) {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }

  public String toString() {
    return super.toString() + getSpeed();
  }
}
