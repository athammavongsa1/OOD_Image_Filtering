import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents an image. This functions as the model of the design. The operations in this
 * class are the features that can be called from the controller by the user. The different features
 * that can be called are the same that have been mandated by the ImageInterface.
 */
public class Image implements ImageInterface {
  /**
   * This field represents this image as a 3D array.
   */
  private int[][][] myImage;
  /**
   * This field represents the width of this image.
   */
  private int myImageWidth;
  /**
   * This field represents the height of this image.
   */
  private int myImageHeight;

  /**
   * This constructs the Image class. The data in the image class are defined as a 3D array of this
   * image, its width, and its height.
   *
   * @param myImage       a 3D int array of pixels in this image.
   * @param myImageWidth  the width of this image.
   * @param myImageHeight the height of this image.
   */

  public Image(int[][][] myImage, int myImageWidth, int myImageHeight) {
    this.myImage = myImage;
    this.myImageWidth = myImageWidth;
    this.myImageHeight = myImageHeight;
  }

  @Override
  public void blurImage() {
    //Create the kernel.
    double[][] blur = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
                       {0.0625, 0.125, 0.0625}, {0.0625, 0.125, 0.0625}};
    applyKernel(blur);
    clampImage(this.myImage);
  }

  @Override
  public void sharpenImage() {
    //Create the kernel.
    double[][] sharpen = {{-0.125, -0.125, -0.125, -0.125, -0.125},
                          {-0.125, 0.25, 0.25, 0.25, -0.125},
                          {-0.125, 0.25, 1, 0.25, -0.125},
                          {-0.125, 0.25, 0.25, 0.25, -0.125},
                          {-0.125, -0.125, -0.125, -0.125, -0.125}};
    applyKernel(sharpen);
    clampImage(this.myImage);
  }

  @Override
  public void grayScale() {
    for (int j = 0; j < myImageHeight; j++) {
      for (int k = 0; k < myImageWidth; k++) {
        double rgbPrime = 0.2126 * this.myImage[j][k][0] + 0.7152 * this.myImage[j][k][1]
                + 0.0722 * this.myImage[j][k][2];
        setRGBColors(j, k, (int) rgbPrime, (int) rgbPrime, (int) rgbPrime);
      }
    }
    clampImage(this.myImage);
  }

  @Override
  public void sepiaTone() {
    for (int j = 0; j < myImageHeight; j++) {
      for (int k = 0; k < myImageWidth; k++) {
        double rPrime = 0.393 * this.myImage[j][k][0] + 0.769 * this.myImage[j][k][1]
                + 0.189 * this.myImage[j][k][2];
        double gPrime = 0.349 * this.myImage[j][k][0] + 0.689 * this.myImage[j][k][1]
                + 0.168 * this.myImage[j][k][2];
        double bPrime = 0.272 * this.myImage[j][k][0] + 0.534 * this.myImage[j][k][1]
                + 0.131 * this.myImage[j][k][2];
        setRGBColors(j, k, (int) rPrime, (int) gPrime, (int) bPrime);
      }
    }
    clampImage(this.myImage);
  }

  @Override
  public void dither() {
    this.grayScale();
    for (int j = 0; j < myImageHeight; j++) {
      for (int k = 0; k < myImageWidth; k++) {
        int old_color = this.myImage[j][k][0];
        int new_color;
        if (Math.abs(old_color - 0) < Math.abs(old_color - 255)) {
          new_color = 0;
        } else {
          new_color = 255;
        }
        int error = old_color - new_color;

        setRGBColors(j, k, new_color, new_color, new_color);

        if (j >= 0 && j < myImageHeight && k + 1 >= 0 && k + 1 < myImageWidth) {
          incrementRGBColors(j, k + 1, (7 / 16.0) * error);
        }

        if (j + 1 >= 0 && j + 1 < myImageHeight && k - 1 >= 0 && k - 1 < myImageWidth) {
          incrementRGBColors(j + 1, k - 1, (3 / 16.0) * error);
        }

        if (j + 1 >= 0 && j + 1 < myImageHeight && k + 1 >= 0 && k < myImageWidth) {
          incrementRGBColors(j + 1, k, (5 / 16.0) * error);
        }

        if (j + 1 >= 0 && j + 1 < myImageHeight && k + 1 >= 0 && k + 1 < myImageWidth) {
          incrementRGBColors(j + 1, k + 1, (1 / 16.0) * error);
        }
      }
      clampImage(this.myImage);
    }
  }

  @Override
  public void mosaic(int seeds) {
    int[][] clusterIdList = getClusterIdList(getRandomLocations(seeds, myImageHeight),
            getRandomLocations(seeds, myImageWidth));
    List<Integer> rgbList;
    for (int j = 0; j < myImageHeight; j++) {
      for (int k = 0; k < myImageWidth; k++) {
        rgbList = rgbAverage(clusterIdList[j][k], clusterIdList);
        setRGBColors(j, k, rgbList.get(0), rgbList.get(1), rgbList.get(2));
      }
    }
    clampImage(this.myImage);
  }

  @Override
  public void rainbowHorizontal(int height, int width) {
    this.myImage = new int[height][width][3];
    this.myImageHeight = height;
    this.myImageWidth = width;
    int stripeHeight = this.myImageHeight / 7;
    int lastStripe = stripeHeight + this.myImageHeight % 7;

    fillColor(0, 0, stripeHeight, myImageWidth, this.myImage, 148, 0, 211);

    fillColor(0, stripeHeight, stripeHeight, myImageWidth, this.myImage, 75, 0, 130);

    fillColor(0, 2 * stripeHeight, stripeHeight,
            myImageWidth, this.myImage, 0, 0, 255);

    fillColor(0, 3 * stripeHeight, stripeHeight,
            myImageWidth, this.myImage, 0, 255, 0);

    fillColor(0, 4 * stripeHeight, stripeHeight,
            myImageWidth, this.myImage, 255, 255, 0);

    fillColor(0, 5 * stripeHeight, stripeHeight,
            myImageWidth, this.myImage, 255, 127, 0);

    fillColor(0, 6 * stripeHeight, lastStripe,
            myImageWidth, this.myImage, 255, 0, 0);
  }

  @Override
  public void rainbowVertical(int height, int width) {
    this.myImage = new int[height][width][3];
    this.myImageHeight = height;
    this.myImageWidth = width;
    int stripeWidth = this.myImageWidth / 7;
    int lastStripe = stripeWidth + this.myImageWidth % 7;

    fillColor(0, 0, myImageHeight, myImageWidth, this.myImage, 148, 0, 211);

    fillColor(stripeWidth, 0, height, stripeWidth, this.myImage, 75, 0, 130);

    fillColor(2 * stripeWidth, 0, height, stripeWidth, this.myImage, 0, 0, 255);

    fillColor(3 * stripeWidth, 0, height, stripeWidth, this.myImage, 0, 255, 0);

    fillColor(4 * stripeWidth, 0, height, stripeWidth, this.myImage, 255, 255, 0);

    fillColor(5 * stripeWidth, 0, height, stripeWidth, this.myImage, 255, 127, 0);

    fillColor(6 * stripeWidth, 0, height, lastStripe, this.myImage, 255, 0, 0);
  }

  @Override
  public void checkerBoard(int squareSize) {
    this.myImageWidth = 8 * squareSize;
    this.myImageHeight = 8 * squareSize;
    this.myImage = new int[this.myImageHeight][this.myImageWidth][3];

    for (int j = 0; j < 8; j += 2) {
      for (int i = 0; i < 8; i += 2) {
        fillColor(i * squareSize, j * squareSize,
                squareSize, squareSize, this.myImage, 255, 255, 255);
      }
    }

    for (int j = 1; j < 8; j += 2) {
      for (int i = 1; i < 8; i += 2) {
        fillColor(i * squareSize, j * squareSize,
                squareSize, squareSize, this.myImage, 255, 255, 255);
      }
    }
  }

  @Override
  public void flagFrance(int height, int width) {
    this.myImageWidth = width;
    this.myImageHeight = height;
    this.myImage = new int[this.myImageHeight][this.myImageWidth][3];
    int stripeWidth = this.myImageWidth / 3;
    int lastStripe = stripeWidth + this.myImageWidth % 3;

    fillColor(0, 0, height, stripeWidth, this.myImage, 0, 0, 153);
    fillColor(stripeWidth, 0, height, stripeWidth, this.myImage, 255, 255, 255);
    fillColor(2 * stripeWidth, 0, height, lastStripe, this.myImage, 255, 0, 0);
  }

  @Override
  public void flagSwitzerland(int size) {
    this.myImageWidth = size;
    this.myImageHeight = size;
    this.myImage = new int[this.myImageHeight][this.myImageWidth][3];
    int strip = this.myImageWidth / 5;

    fillColor(0, 0, size, size, this.myImage, 255, 0, 0);
    fillColor(2 * strip, strip, 3 * strip, strip, this.myImage, 255, 255, 255);
    fillColor(strip, 2 * strip, strip, 3 * strip, this.myImage, 255, 255, 255);
  }

  @Override
  public void flagGreek(int height, int width) {
    this.myImageWidth = width;
    this.myImageHeight = height;
    this.myImage = new int[this.myImageHeight][this.myImageWidth][3];
    int strip = this.myImageHeight / 9;
    int shortWidthStrip = myImageWidth - 5 * strip;

    fillColor(0, 0, myImageHeight, myImageWidth, myImage, 255, 255, 255);

    fillColor(0, 0, 5 * strip, 5 * strip, myImage, 13, 94, 175);
    fillColor(5 * strip, 0, strip, shortWidthStrip, myImage, 13, 94, 175);
    fillColor(5 * strip, 2 * strip, strip, shortWidthStrip, myImage, 13, 94, 175);
    fillColor(5 * strip, 4 * strip, strip, shortWidthStrip, myImage, 13, 94, 175);
    fillColor(0, 6 * strip, strip, myImageWidth, myImage, 13, 94, 175);
    fillColor(0, 8 * strip, strip, myImageWidth, myImage, 13, 94, 175);

    fillColor(2 * strip, 0, 5 * strip, strip, myImage, 255, 255, 255);
    fillColor(0, 2 * strip, strip, 5 * strip, myImage, 255, 255, 255);
  }

  @Override
  public int[][][] getMyImage() {
    try {
      int[][][] finalImage = new int[myImageHeight][myImageWidth][3];
      finalImage = myImage;
      return finalImage;
    }
    catch (NegativeArraySizeException e) {
      return null;
    }
  }


  @Override
  public int getMyImageWidth() {
    return myImageWidth;
  }


  @Override
  public int getMyImageHeight() {
    return myImageHeight;
  }

  /**
   * This is a helper method that fills in a rectangle of a specific location and size with the
   * specified RGB values. This helper method is utilized to create the stripes, the checkerboard,
   * and the flags. It iterates through the height and width of the image, and it changes the RGB
   * values of each pixel.
   *
   * @param x      x location of the top left corner of the rectangle.
   * @param y      y location of top left corner of the rectangle.
   * @param height the height of the rectangle.
   * @param width  the width of the rectangle.
   * @param image  the image to put the rectangle in.
   * @param r      the r chanel value of that pixel.
   * @param g      the g chanel value of that pixel.
   * @param b      the b chanel value of that pixel.
   */
  private void fillColor(int x, int y, int height, int width,
                         int[][][] image, int r, int g, int b) {
    for (int j = y; j < height + y; j++) {
      for (int k = x; k < width + x; k++) {
        image[j][k][0] = r;
        image[j][k][1] = g;
        image[j][k][2] = b;
      }
    }
  }

  /**
   * This is a helper method to clamp values of a given image represented as a 3D int array. It
   * iterates through every pixel in the image, and it ensures that pixel value is between 0 and
   * 255.
   *
   * @param image a int 3D array that represents a pre-clamped image.
   */
  private void clampImage(int[][][] image) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < myImageHeight; j++) {
        for (int k = 0; k < myImageWidth; k++) {
          if (image[j][k][i] > 255) {
            image[j][k][i] = 255;
          }
          if (image[j][k][i] < 0) {
            image[j][k][i] = 0;
          }
        }
      }
    }
  }

  /**
   * This is a helper method that applies a given kernel to this image. It iterates through every
   * pixel and convolves the kernel with the pixel to set that pixel to the new pixel value.
   *
   * @param kernel the kernel to be applied over the image.
   */
  private void applyKernel(double[][] kernel) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < myImageHeight; j++) {
        for (int k = 0; k < myImageWidth; k++) {
          // got pixel at (j,k)
          double sum = 0;
          for (int c = 0; c < kernel.length; c++) {
            for (int d = 0; d < kernel[0].length; d++) {

              int myImageX = j - 1 + c;
              int myImageY = k - 1 + d;
              if ((myImageX >= 0 && myImageX < myImage.length)
                      && (myImageY >= 0 && myImageY < myImage[0].length)) {
                sum += myImage[myImageX][myImageY][i] * kernel[c][d];
              }

            }
          }
          this.myImage[j][k][i] = (int) sum;
        }
      }
    }
  }


  /**
   * This private helper method sets the rgb colors of a given pixel when provided its location and
   * the specific rgb values.
   *
   * @param j      the height or the row value of the pixel.
   * @param k      the width or the column value of the pixel.
   * @param rPrime the r value.
   * @param gPrime the g value.
   * @param bPrime the b balue.
   */
  private void setRGBColors(int j, int k, int rPrime, int gPrime, int bPrime) {
    this.myImage[j][k][0] = rPrime;
    this.myImage[j][k][1] = gPrime;
    this.myImage[j][k][2] = bPrime;
  }

  /**
   * This private helper method increments the rgb colors given the location of the pixel, and the
   * amount the rgb values need to be increased.
   *
   * @param height   the row location of the pixel.
   * @param width    the column location of the pixel.
   * @param increase the amount each rgb value is incremented.
   */
  private void incrementRGBColors(int height, int width, double increase) {
    for (int c = 0; c < 3; c++) {
      this.myImage[height][width][c] += increase;
    }
  }

  /**
   * This private helper method calculates the average rgb values of all the pixels assigned to one
   * cluster. This is utilized as a helper in the mosaic method after every pixel is assigned to a
   * particular cluster. The method finds all matching pixels with the same ids and averages their
   * rgb values.
   *
   * @param clusterIdList the cluster id of the each pixel.
   * @param clusterIdArr  the list of assignments which contains the cluster id of each picture.
   * @return a list containing the average r, g, and b value.
   */
  private List<Integer> rgbAverage(int clusterIdList, int[][] clusterIdArr) {
    int rValues = 0;
    int gValues = 0;
    int bValues = 0;
    int count = 0;
    List<Integer> rgbList = new ArrayList<>();
    for (int j = 0; j < myImageHeight; j++) {
      for (int k = 0; k < myImageWidth; k++) {
        if (clusterIdArr[j][k] == clusterIdList) {
          rValues += myImage[j][k][0];
          gValues += myImage[j][k][1];
          bValues += myImage[j][k][2];
          count += 1;
        }
      }
    }
    rgbList.add(rValues / count);
    rgbList.add(gValues / count);
    rgbList.add(bValues / count);
    return rgbList;
  }

  /**
   * This method gets the user defined number of random pixels height and width values.
   *
   * @param k         the user defined number of seeds.
   * @param dimension the dimension, height or width, the seeds will be assigned to.
   * @return seed number of random values that will be utilized as the height and width of seeds.
   */
  private List<Integer> getRandomLocations(int k, int dimension) {
    List<Integer> randomLocations = new ArrayList<>();
    Random rand = new Random();
    int i = 0;
    while (i < k) {
      int randomPoint = rand.nextInt(dimension);
      //if(!randomLocations.contains(randomPoint)) {
      randomLocations.add(randomPoint);
      //}
      i++;
    }
    return randomLocations;
  }

  /**
   * This method gets the cluster id of every pixel given a list of random heights and widths of the
   * seeds. It returns a twoD int array of seed assignments for each pixel.
   *
   * @param randomHeight the list of the heights of the seeds.
   * @param randomWidth  the list of the widths of the seeds.
   * @return a two d array of closest seed assignment for every pixel.
   */
  private int[][] getClusterIdList(List<Integer> randomHeight, List<Integer> randomWidth) {
    int[][] newList = new int[myImageHeight][myImageWidth];
    for (int j = 0; j < myImageHeight; j++) {
      for (int k = 0; k < myImageWidth; k++) {
        double closestCentroid = 0;
        int closestCentroidPosition = 0;

        List<Double> distanceList = new ArrayList<>();
        for (int l = 0; l < randomHeight.size(); l++) {
          distanceList.add(eucDistance(j, k, randomHeight.get(l), randomWidth.get(l)));
        }
        closestCentroid = getMinValue(distanceList);
        closestCentroidPosition = distanceList.indexOf(closestCentroid);
        newList[j][k] = closestCentroidPosition;
      }
    }
    return newList;
  }

  /**
   * This method gives the euclidean distance between a pixel and another pixel that represents the
   * seed.
   *
   * @param pixelHeight The height of a pixel.
   * @param pixelWidth  The width of a pixel.
   * @param height      The height of seed.
   * @param width       The width of the seed.
   * @return The euclidean distance between a pixel and the seed.
   */
  private double eucDistance(int pixelHeight, int pixelWidth, int height, int width) {
    double distance = Math.sqrt((pixelHeight - height) * (pixelHeight -
            height)
            + (pixelWidth - width) * (pixelWidth - width));
    return distance;
  }

  /**
   * This method gets the minimum of a list of distances.
   *
   * @param distanceList The list of euclidean distances between a pixel and all the seeds.
   * @return The minimum of the distance list.
   */
  private double getMinValue(List<Double> distanceList) {
    double minValue = distanceList.get(0);
    for (Double d : distanceList) {
      if (d < minValue) {
        minValue = d;
      }
    }
    return minValue;
  }

}
