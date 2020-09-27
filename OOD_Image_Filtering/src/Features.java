/**
 * The purpose of this interface is to ensure that the controller for the GUI communicates with the
 * view in a manner that does not reveal how the view is implemented. The advantage to this method
 * is that the view can change button names and dialog boxes without having to affect the controller
 * for the GUI. The methods in this interface represents features in the view.
 */
public interface Features {
  /**
   * This method has the call back features to the view. It includes all filters, load, and save
   * methods. It ensures that data from the view is a valid input to the best of its abilities. It
   * then calls the model and gives the data from the model to the view. There are scenarios where
   * the controller does not check for errors in user input. These situations specifically require
   * the model to throw the error. The controller will then catch that error and output to the view.
   * The view from this controller has an interactive mode as well as a batch script. This method
   * responds appropriately  to the user's request based on the mode of input.
   *
   * @param command the user input data from the view.
   * @param source  the mode in which the user is utilizing the view. This could be GUI or batch.
   */
  void applyFilters(String command, String source);

  /**
   * This method is to undo operations that the user has created. The purpose of this method being
   * in the controller is that the controller should have the ability to undo actions by the user
   * since the model only represents operations on an image.
   *
   * @param undoAmount this keeps track of the number of times the user has pressed the undo
   *                   button.
   */
  void undoOperation(int undoAmount);

  /**
   * This method creates a record of every model created by the user input through the GUI.
   */
  void addHistory();
}
