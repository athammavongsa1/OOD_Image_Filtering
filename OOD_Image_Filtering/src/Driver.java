
import java.io.FileReader;
import java.io.IOException;


/**
 * This class represents a simple driver with a main method. It does not directly interact with the
 * user nor does it show any results. It starts the application with an initialized controller.
 */
public class Driver {

  /**
   * This main method is the driver for the application. It can be run with two different
   * configurations. If the args configuration is -script input.txt, the takes in any text file. For
   * this program, the text file is considered to be input.txt, which contains the commands that the
   * user puts in for the program and runs as a batch script. If args configuration is -interactive,
   * then the program opens up a gui where the user can apply commands and see changes one at a
   * time. A batch script functionality is also incorporated within the GUI.
   *
   * @param args command line arguments, input.txt.
   * @throws IOException if the file reader cannot find the file.
   */
  public static void main(String[] args) throws IOException {
    if (args[0].equals("-script")) {
      Readable in = new FileReader(args[1]);
      new ControllerForScript(in).goControllerForScript(new ImageManager());
    } else if (args[0].equals("-interactive")) {
      ViewInterface view = new ViewImpl("Image Processor");
      view.display();
      new Controller(new ImageManager(), view);
    }
  }
}




