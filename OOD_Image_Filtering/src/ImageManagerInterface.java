import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This interface represents a utility class for the controller to access when loading and saving an
 * image. The purpose of this interface is to decouple the model from having the responsibility of
 * creating images for features that don't have a previously loaded image. Depending on the input,
 * the controller now has the task of calling this interface when loading the image for methods that
 * need a previously loaded image, creating an image for methods that don't need a previously loaded
 * image, and saving the image.
 */

public interface ImageManagerInterface {
  /**
   * This method loads the image given a filename and gives the image as an ImageInterface or Model
   * object.
   *
   * @param fileName the text file to be read.
   * @return the ImageInterface object that represents the image loaded.
   */
  ImageInterface loadImage(String fileName) throws IOException;

  /**
   * This method creates an empty image. This is called when the user gives commands that don't
   * require a previously loaded image. The purpose of this method is to ensure that the model only
   * transforms image. It does not create them.
   *
   * @return an empty image object of type ImageInterface.
   */
  ImageInterface createImage();

  /**
   * This method saves a give image to the specified file name.
   *
   * @param imageObject    The ImageInterace object to be saved.
   * @param filePath       the path of the file to be saved.
   * @param saveAsFileName the name of the file.
   */
  void saveImage(ImageInterface imageObject, String filePath, String saveAsFileName)
          throws IOException;

  /**
   * This method transforms the output of the model into a buffered image. It is a utility method
   * used when controller is sending data from the model to the view.
   *
   * @param rgb    the rgb array of this model.
   * @param width  the width of this model.
   * @param height the height of this model.
   * @return the buffered image of this model.
   */
  BufferedImage displayImage(int[][][] rgb, int width, int height);
}
