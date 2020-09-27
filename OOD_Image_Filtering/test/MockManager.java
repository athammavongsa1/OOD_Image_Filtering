import java.awt.image.BufferedImage;

/**
 * The purpose of this class is to mock the implementation of the ImageMangerInterface. It is
 * designed similar to the image manager and verifies that the controller calls load, create, and
 * save methods at appropriate times. It is a utility mock to isolate the controller operations.
 */

public class MockManager implements ImageManagerInterface {
  /**
   * This field is a mock of the ImageInterface object.
   */
  private ImageInterface mockImage;
  /**
   * This field is a log of which methods are called.
   */
  private StringBuilder log;

  /**
   * The constructor for this mock class takes in a mock ImageInterface object and a log that is
   * built after every method is called.
   *
   * @param mockImage mock Image.
   * @param log       StringBuilder to keep track of void methods.
   */
  public MockManager(ImageInterface mockImage, StringBuilder log) {
    this.mockImage = mockImage;
    this.log = log;
  }

  /**
   * This method mocks the load image method and log is appended if the controller calls this
   * method.
   *
   * @param fileName the text file to be read.
   * @return the mock image that is loaded.
   */
  @Override
  public ImageInterface loadImage(String fileName) {
    log.append("Image loaded" + " " + fileName + " done. ");
    return mockImage;
  }

  /**
   * This method mocks the createImage method and log is appended if the controller calls this
   * method.
   *
   * @return the mock image that is created.
   */
  @Override
  public ImageInterface createImage() {
    log.append("Image created" + " done. ");
    return mockImage;
  }

  /**
   * This method mocks the saveImage method and log is appended if the controller calls this
   * method.
   *
   * @param imageObject    The ImageInterace object to be saved.
   * @param saveAsFileName the name of the file.
   */
  @Override
  public void saveImage(ImageInterface imageObject, String filePath, String saveAsFileName) {
    log.append("Image saved" + " " + saveAsFileName + " done. ");
  }

  @Override
  public BufferedImage displayImage(int[][][] rgb, int width, int height) {
    return null;
  }


}
