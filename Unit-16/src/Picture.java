import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {

  private static String pathPrefix = "/Users/eggnog/Developer/APCSA/Unit-16/src/images/";

  ///////////////////// constructors //////////////////////////////////

  /**
   * Constructor that takes no arguments
   */
  public Picture() {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor
     */
    super();
  }

  /**
   * Constructor that takes a file name and creates the picture
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName) {
    // let the parent class handle this fileName
    super(fileName);
  }

  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width) {
    // let the parent class handle this width and height
    super(width, height);
  }

  /**
   * Constructor that takes a picture and creates a
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture) {
    // let the parent class do the copy
    super(copyPicture);
  }

  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image) {
    super(image);
  }

  ////////////////////// methods ///////////////////////////////////////

  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString() {
    String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
    return output;
  }

  /** Method to set the blue to 0 */
  public void zeroBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setBlue(0);
      }
    }
  }

  /** Method that mirrors the picture around a
   * vertical mirror in the center of the picture
   * from left to right */
  public void mirrorVertical() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < width / 2; col++) {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }

  /** Mirror just part of a picture of a temple */
  public void mirrorTemple() {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();

    // loop through the rows
    for (int row = 27; row < 97; row++) {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++) {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }

  /** copy from the passed fromPic to the
   * specified startRow and startCol in the
   * current picture
   * @param fromPic the picture to copy from
   * @param startRow the start row to copy to
   * @param startCol the start col to copy to
   */
  public void copy(Picture fromPic, int startRow, int startCol) {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (
      int fromRow = 0, toRow = startRow;
      fromRow < fromPixels.length && toRow < toPixels.length;
      fromRow++, toRow++
    ) {
      for (
        int fromCol = 0, toCol = startCol;
        fromCol < fromPixels[0].length && toCol < toPixels[0].length;
        fromCol++, toCol++
      ) {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }

  public void copy(
    Picture fromPic,
    int fromStartRow,
    int fromStartCol,
    int fromEndRow,
    int fromEndCol,
    int toStartRow,
    int toStartCol
  ) {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (
      int fromRow = fromStartRow, toRow = toStartRow;
      fromRow < fromEndRow && toRow < toPixels.length;
      fromRow++, toRow++
    ) {
      for (
        int fromCol = fromStartCol, toCol = toStartCol;
        fromCol < fromEndCol && toCol < toPixels[0].length;
        fromCol++, toCol++
      ) {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }

  /** Method to create a collage of several pictures */
  public void createCollage() {
    Picture flower1 = new Picture(pathPrefix + "flower1.jpg");
    Picture flower2 = new Picture(pathPrefix + "flower2.jpg");
    this.copy(flower1, 0, 0);
    this.copy(flower2, 100, 0);
    this.copy(flower1, 200, 0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue, 300, 0);
    this.copy(flower1, 400, 0);
    this.copy(flower2, 500, 0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  public void myCollage() {
    Picture beach = new Picture(pathPrefix + "beach.jpg");
    beach.mirrorVerticalRightToLeft();
    this.copy(beach, 0, 0);
    Picture motorcycle = new Picture(pathPrefix + "redMotorcycle.jpg");
    motorcycle.negate();
    this.copy(motorcycle, 71, 43, 420, 540, 115, 130);
    Picture seagull = new Picture(pathPrefix + "seagull.jpg");
    seagull.negate();
    this.copy(seagull, 235, 238, 320, 344, 150, 475);
    seagull.negate();
    seagull.mirrorGull();
    this.copy(seagull, 235, 238, 320, 475, 375, 20);
    Picture flower1 = new Picture(pathPrefix + "flower1.jpg");
    flower1.mirrorHorizontal();
    this.copy(flower1, 0, 0, 99, 88, 5, 285);
    flower1.explore();
  }

  /** Method to show large changes in color
   * @param edgeDist the distance for finding edges
   */
  public void edgeDetection(int edgeDist) {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length - 1; col++) {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col + 1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > edgeDist) leftPixel.setColor(Color.BLACK); else leftPixel.setColor(
          Color.WHITE
        );
      }
    }
  }

  public void keepOnlyBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }

  public void keepOnlyRed() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setBlue(0);
        pixelObj.setGreen(0);
      }
    }
  }

  public void keepOnlyGreen() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setBlue(0);
        pixelObj.setRed(0);
      }
    }
  }

  public void negate() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }

  public void grayscale() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        int avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }

  public void fixUnderwater() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        if (pixel.getRed() * 4 <= 255) {
          pixel.setRed(pixel.getRed() * 4);
        } else {
          pixel.setRed(255);
        }
        pixel.setGreen((int) (pixel.getGreen() / 1.15));
        pixel.setBlue((int) (pixel.getBlue() / 1.15));
      }
    }
  }

  public void mirrorArms() {
    // left arm
    int rowLeft = 155;
    int colTop = 105;
    int rowRight = 190;
    int colBottom = 170;
    Color testPixel = new Pixel(this, 179, 163).getColor();

    Pixel[][] pixels = this.getPixels2D();
    for (int row = rowLeft; row < rowRight; row++) {
      for (int col = colTop; col < colBottom; col++) {
        if (pixels[row][col].colorDistance(testPixel) > 50) {
          pixels[rowRight + (rowRight - row)][col].setColor(pixels[row][col].getColor());
        }
      }
    }

    // right arm
    rowLeft = 169;
    colTop = 239;
    rowRight = 195;
    colBottom = 293;
    int flipRow = 190;

    for (int row = rowLeft; row < rowRight; row++) {
      for (int col = colTop; col < colBottom; col++) {
        if (pixels[row][col].colorDistance(testPixel) > 50) {
          pixels[flipRow + (flipRow - row)][col].setColor(pixels[row][col].getColor());
        }
      }
    }
  }

  public void mirrorVerticalRightToLeft() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < width / 2; col++) {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }

  public void mirrorHorizontal() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }

  public void mirrorHorizontalBotToTop() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    }
  }

  public void mirrorGull() {
    Pixel[][] pixels = this.getPixels2D();
    int mirrorPoint = 355;
    Pixel leftPixel = null;
    Pixel rightPixel = null;

    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        if (col <= mirrorPoint) {
          int rightPixelCol = mirrorPoint - col + mirrorPoint;
          if (!(rightPixelCol > pixels[0].length - 1)) {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][rightPixelCol];
            rightPixel.setColor(leftPixel.getColor());
          }
        }
      }
    }
  }

  public void mirrorDiagonal() {
    Pixel pixels[][] = this.getPixels2D();
    Pixel bottomPixel = null;
    Pixel topPixel = null;
    int length = Math.min(pixels.length, pixels[0].length);
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < i + 1; j++) {
        bottomPixel = pixels[i][j];
        topPixel = pixels[j][i];
        topPixel.setColor(bottomPixel.getColor());
      }
    }
  }

  public void encodeAndDecode() {}

  public void getCountRedOverValue(int val) {}

  public void setRedToHalfValue(int val) {}

  public void getAverageForColumn(int col) {}

  public void edgeDetection2() {}

  public void chromakey() {}

  public void setRedToHalfValueInTopHalf() {}

  public void clearBlueOverValue(int val) {
    Pixel pixels[][] = this.getPixels2D();
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        if (pixel.getBlue() > val) {
          pixel.setAlpha(255);
        }
      }
    }
  }

  public void encode(Picture messagePicture) {
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] messagePixels = messagePicture.getPixels2D();
    int currentMod = 2;
    for (int i = 0; i < pixels.length && i < messagePixels.length; i++) {
      for (int j = 0; j < pixels[i].length && j < messagePixels[i].length; j++) {
        Pixel pixel = pixels[i][j];
        Pixel messagePixel = messagePixels[i][j];
        if (messagePixel.colorDistance(Color.black) < 50) {
          // System.out.println("if");
          while (pixel.getRed() % currentMod != 0) {
            if (currentMod == 2 && pixel.getRed() == 255) {
              pixel.setRed(254);
            } else {
              pixel.setRed(pixel.getRed() + 1);
            }
          }
          while (pixel.getGreen() % currentMod != 0) {
            if (currentMod == 2 && pixel.getGreen() == 255) {
              pixel.setGreen(254);
            } else {
              pixel.setGreen(pixel.getGreen() + 1);
            }
          }
          while (pixel.getBlue() % currentMod != 0) {
            if (currentMod == 2 && pixel.getBlue() == 255) {
              pixel.setBlue(254);
            } else {
              pixel.setBlue(pixel.getBlue() + 1);
            }
          }
        } else {
          // System.out.println("else");
          while (pixel.getRed() % currentMod == 0) {
            if (currentMod == 3 && pixel.getRed() == 255) {
              pixel.setRed(253);
            } else {
              pixel.setRed(pixel.getRed() + 1);
            }
          }
          while (pixel.getGreen() % currentMod == 0) {
            if (currentMod == 3 && pixel.getGreen() == 255) {
              pixel.setGreen(253);
            } else {
              pixel.setGreen(pixel.getGreen() + 1);
            }
          }
          while (pixel.getBlue() % currentMod == 0) {
            if (currentMod == 3 && pixel.getBlue() == 255) {
              pixel.setBlue(253);
            } else {
              pixel.setBlue(pixel.getBlue() + 1);
            }
          }
        }
        // System.out.println("done: " + i + " " + j);
        // System.out.println(currentMod);
        if (currentMod % 2 == 0) {
          currentMod++;
        } else {
          currentMod--;
        }
      }
    }
  }

  public void decode() {
    Pixel[][] pixels = this.getPixels2D();
    int currentMod = 2;
    for (Pixel[] row : pixels) {
      for (Pixel pixel : row) {
        if (
          pixel.getRed() % currentMod == 0 &&
          pixel.getBlue() % currentMod == 0 &&
          pixel.getGreen() % currentMod == 0
        ) {
          pixel.setColor(new Color(0, 0, 0, 0));
        } else {
          pixel.setColor(new Color(255, 255, 255, 0));
        }
        if (currentMod % 2 == 0) {
          currentMod++;
        } else {
          currentMod--;
        }
      }
    }
  }

  /* Main method for testing - each class in Java can have a main
   * method
   */
  public static void main(String[] args) {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
} // this } is the end of class Picture, put all new methods before this
