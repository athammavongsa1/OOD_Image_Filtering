import java.awt.image.BufferedImage;

/**
 * This interface defines the GUI view of this project. The methods in this interface represent the
 * operations that this view should offer. Within this view, the user is able to interactively end
 * with a batch script, load an image, apply features to it provided by the model, and save it to
 * where ever needed in the system.
 */
public interface ViewInterface {

  /**
   * This method gets the string representation of the path of the image to load.
   */
  String getFileInputString();

  /**
   * This method gets the first dimension for features in the model that require user specified
   * width and or height.
   *
   * @return integer dimension.
   */
  int getDimensionOne();

  /**
   * This method gets the second dimension for features in the model that require user specified
   * width and or height.
   *
   * @return integer dimension.
   */
  int getDimensionTwo();

  /**
   * Display this view.
   */
  void display();

  /**
   * This method sets the final output image to the image view inside the GUI.
   *
   * @param s output image of type BufferedImage.
   */
  void setOutput(BufferedImage s);

  /**
   * This method replaces the addListeners and allows for the view to define its own listeners. Each
   * listener in this method calls back to the controller's appropriate callback function.
   *
   * @param features allows for callback function be called when button is clicked.
   */
  void addFeatures(Features features);

  /**
   * This method gets the name of the file given in the batch script if the user chooses to write in
   * the filename instead of loading it.
   *
   * @return the name of the file.
   */
  String getFileName();

  /**
   * This method gets the first dimension in the batch script if the user decides to use the batch
   * script instead of the GUI.
   *
   * @return integer dimension.
   */
  int getBatchDimensionOne();

  /**
   * This method gets the second dimension in the batch script if the user decides to use the batch
   * script instead of the GUI.
   *
   * @return integer dimension.
   */
  int getBatchDimensionTwo();

  /**
   * This method outputs a JOptionPane error message if the user attempts to load or save a file
   * that's not there.
   */
  void fileNotFound();

  /**
   * This method outputs a JOptionPane error message if the user gives incorrect dimensions for
   * certain features.
   */
  void incorrectDimensions();

  /**
   * This method outputs a JOptionPane error message if the user does not load an image before
   * filtering it.
   */
  void noImageLoaded();

  /**
   * This method gets the string representation of the filepath to be saved.
   *
   * @return string filepath to be saved.
   */
  String getFilePathSave();

  /**
   * This method gets the string representation of the name of the file to be saved.
   *
   * @return name of output file.
   */
  String getFileNameSaveAs();

}
