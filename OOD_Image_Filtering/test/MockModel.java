/**
 * The purpose of this class is to mock the implementation of the ImageInterface which represents
 * our model. It is designed similar to the image class and verifies that the controller calls the
 * methods within the model at the appropriate times. It is a model mock to isolate the controller
 * operations.
 */

public class MockModel implements ImageInterface {

  /**
   * This field is a log of which methods are called.
   */
  private StringBuilder log;
  /**
   * This field is a unique code to verify image heights and widths.
   */
  private final int uniqueCode;
  /**
   * This field is a unique code to verify image.
   */
  private final int[][][] uniqueArray;

  /**
   * The constructor for this mock model takes in a log to be appended at each method called, a
   * unique code to verify height and width getters and a unique int array to verify image getter.
   *
   * @param log         StringBuilder to be appended at each method used.
   * @param uniqueCode  to verify height and width getters.
   * @param uniqueArray to verify image getter.
   */
  public MockModel(StringBuilder log, int uniqueCode, int[][][] uniqueArray) {
    this.log = log;
    this.uniqueCode = uniqueCode;
    this.uniqueArray = uniqueArray;
  }

  /**
   * This method mocks the blur image method and log is appended if the controller calls this
   * method.
   */
  @Override
  public void blurImage() {
    log.append("blur done. ");
  }

  /**
   * This method mocks the sharpen image method and log is appended if the controller calls this
   * method.
   */
  @Override
  public void sharpenImage() {
    log.append("sharpen done. ");

  }

  /**
   * This method mocks the grayScale image method and log is appended if the controller calls this
   * method.
   */
  @Override
  public void grayScale() {
    log.append("gray done. ");

  }

  /**
   * This method mocks the sepiaTOne image method and log is appended if the controller calls this
   * method.
   */
  @Override
  public void sepiaTone() {
    log.append("sepiaTone done. ");

  }

  /**
   * This method mocks the horizontal rainbow method and log is appended if the controller calls
   * this method.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */
  @Override
  public void rainbowHorizontal(int height, int width) {
    log.append("rainbowhorizontal " + height + " " + width + " done. ");

  }

  /**
   * This method mocks the vertical image method and log is appended if the controller calls this
   * method.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */
  @Override
  public void rainbowVertical(int height, int width) {
    log.append("rainbowVertical " + height + " " + width + " done. ");

  }

  /**
   * This method mocks the checkerboard method and log is appended if the controller calls this
   * method.
   *
   * @param squareSize user specified square size.
   */
  @Override
  public void checkerBoard(int squareSize) {
    log.append("checker " + squareSize + " done. ");

  }

  /**
   * This method mocks the france flag method and log is appended if the controller calls this
   * mehtod.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */
  @Override
  public void flagFrance(int height, int width) {
    log.append("france " + height + " " + width + " done. ");

  }

  /**
   * This method mocks the switzerland flag method and log is appended if the controller calls this
   * method.
   *
   * @param size user specified width or height of the flag.
   */
  @Override
  public void flagSwitzerland(int size) {
    log.append("switzerland " + size + " done. ");
  }

  /**
   * This method mocks the greek flag image method and log is appended if the controller calls this
   * method.
   *
   * @param height the user specified height of the image.
   * @param width  the user specified width of the image.
   */
  @Override
  public void flagGreek(int height, int width) {
    log.append("greece " + height + " " + width + " done. ");
  }

  /**
   * This method mocks the dither image method and log is appended if the controller calls this
   * method.
   */
  @Override
  public void dither() {
    log.append("dither " + " done. ");
  }

  /**
   * This method mocks the mosaic method and log is appended if the controller calls this method.
   *
   * @param seeds the user specified seeds in the image.
   */
  @Override
  public void mosaic(int seeds) {
    log.append("mosaic " + seeds + " done. ");
  }

  /**
   * This method mocks the model's getter for the image.
   *
   * @return the unique array given for this mock.
   */
  @Override
  public int[][][] getMyImage() {
    return this.uniqueArray;
  }

  /**
   * This method mocks the model's getter for the image width.
   *
   * @return the unique code for this mock.
   */
  public int getMyImageWidth() {
    return this.uniqueCode;
  }

  /**
   * This method mocks the model's getter for the image height.
   *
   * @return the unique code for this mock plus one to differentiate from the width.
   */
  @Override
  public int getMyImageHeight() {
    return this.uniqueCode + 1;
  }

}
