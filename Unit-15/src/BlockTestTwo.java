//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Daniel Egorov

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class BlockTestTwo extends Canvas {

  public BlockTestTwo() {
    setBackground(Color.WHITE);
  }

  public void paint(Graphics window) {
    Block one = new Block();
    one.draw(window);

    Block two = new Block(50, 50, 30, 30);
    two.draw(window);

    Block three = new Block(350, 350, 15, 15, Color.RED);
    three.draw(window);

    two.draw(window, Color.white);

    Block four = new Block(450, 50, 20, 60, Color.GREEN);
    four.draw(window);

    //add more test cases
    Block five = new Block(550, 50, 30, 30, Color.PINK);
    five.draw(window);
  }
}
