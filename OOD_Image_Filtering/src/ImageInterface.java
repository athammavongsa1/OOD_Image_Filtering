/**
 * It is the interface that represents an image. The methods in the interface are operations that
 * can be preformed on the image. The operations are blurring a given image, sharpening a given
 * image, applying grey scale and sepia tone to a given image, generating a horizontal and vertical
 * rainbow stripes, generating a checkerboard, creating a flag for France, Switzerland, and Greece.
 */

public interface ImageInterface {
  /**
   * This method applies a blur kernel to this image. It calls the applyKernel method which iterates
   * through every pixel in this image, takes the convolution of the kernel and that pixel, and
   * updates the value of that pixel in this image. It then calls the clamp method to clamp all
   * values of the pixel to be between 0 and 255.
   */
  void blurImage();

  /**
   * This method applies a sharpened kernel to this image, calls applyKernel method which iterates
   * through every pixel in this image, takes the convolution of the kernel and that pixel, and
   * updates the value of that pixel in this image. It then calls the clamp method to clamp all
   * values of the pixel to be between 0 and 255.
   */
  void sharpenImage();

  /**
   * This method converts this image into a grey scale image. It transforms the color by multiplying
   * the RGB values by a standard grey scale formula. It resets the RGB values for every pixel with
   * the newly calculated RGB values.
   */
  void grayScale();

  /**
   * This method converts this image into a sepia tone. It transforms the color by multiplying the
   * RGB values by a standard sepia tone matrix.  It resets the RGB values for every pixel with the
   * newly calculated RGB values.
   */

  void sepiaTone();

  /**
   * This method sets this image to a user specified width and height. It then puts horizontal
   * stripes with ROYGBIV RGB values respectively. Each stripe is of the same height, and the last
   * stripe is up to seven pixels thinner.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */

  void rainbowHorizontal(int height, int width);

  /**
   * This method sets this image to a user specified width and height. It then puts vertical stripes
   * with ROYGBIV RGB values respectively. Each stripe is of the same width, and the last stripe is
   * up to seven pixels thinner.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */


  void rainbowVertical(int height, int width);

  /**
   * This method sets this image to a checkerboard of user specified square sizes. It then places
   * white squares in the same pattern as that of a checkerboard.
   *
   * @param squareSize user specified square size.
   */

  void checkerBoard(int squareSize);

  /**
   * This method specifies this image to be a flag of user specified size. This flag represents a
   * flag of France in particular. This image changes the pixel RBG to resemble that pattern.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */

  void flagFrance(int height, int width);

  /**
   * This method specifies this image to be a flag of user specified size. This flag represents the
   * national flag of  Switzerland in particular. This image changes the pixel RBG to resemble that
   * pattern.
   *
   * @param size user specified width or height of the flag.
   */

  void flagSwitzerland(int size);


  /**
   * This method specifies this image to be a flag of user specified size. This flag represents a
   * flag of Greece in particular. This image changes the pixel RBG to resemble that pattern.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */

  void flagGreek(int height, int width);

  /**
   * This method converts this image into a dithered image. It sets the image to gray scale and
   * breaks the image down into dots utilizing the Floyd-Steinberg algorithm.
   */
  void dither();

  /**
   * This method converts this image into a mosaic image. The user specifies the number of random
   * seeds within the image. The method then assigns each pixel of this image to the closest seed
   * and changes the rgb values to the average of the rgb values of pixels in the cluster.
   */
  void mosaic(int seeds);

  /**
   * This is a getter method that gets the int 3D array representing this image.
   *
   * @return int 3D array that represents this image.
   */
  int[][][] getMyImage();

  /**
   * This is a getter method that gets this image's width.
   *
   * @return int width, the width of this image.
   */
  int getMyImageWidth();

  /**
   * This is a getter method  that gets this image's height.
   *
   * @return int height, the height of the image.
   */
  int getMyImageHeight();

}
