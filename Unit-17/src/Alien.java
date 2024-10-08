//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {

  private int speed;
  private Image image;

  public Alien() {
    this(0, 0, 30, 30, 0);
  }

  public Alien(int x, int y) {
    this(x, y, 30, 30, 0);
  }

  public Alien(int x, int y, int s) {
    this(x, y, 30, 30, s);
  }

  public Alien(int x, int y, int w, int h, int s) {
    super(x, y, w, h);
    speed = s;
    try {
      URL url = getClass().getResource("alien.jpg");
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void setSpeed(int s) {
    speed = s;
  }

  public int getSpeed() {
    return speed;
  }

  public void move(String direction) {
    switch (direction) {
      case "down":
        setY(getY() + 40);
        break;
      case "side":
        setX(getX() + speed);
        break;
    }
  }

  public void draw(Graphics window) {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }

  public String toString() {
    return "";
  }
}
