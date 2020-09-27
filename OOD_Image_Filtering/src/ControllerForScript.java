import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class acts as a controller for the script mode. The roll of the controller here is to take
 * inputs from the user from the input.txt file. Since the file reader is in the main method, this
 * controller is independent of how the input is given. Therefore, the controller works for other
 * input formats. The controller also uses the model to output the transformed image to the view.
 */
public class ControllerForScript {
  final Readable in;

  /**
   * This constructor abstracts the input using a readable object "in". It gets all the inputs from
   * the readable object and calls the model to preform according to the user commands.
   *
   * @param in readable object "in". Data provided by the user.
   */
  public ControllerForScript(Readable in) {
    this.in = in;
  }

  /**
   * This method is called by the main method once an input is given. The purpose of this method is
   * to divide tasks within this project and give controller he ability to call the model when
   * appropriate utilizing the appropriate user commands. It takes in a type ManagerInterface so
   * that the controller is decoupled with the model's implementation. The manager interface is then
   * used by the controller to utilize the model.
   *
   * @param imageProvided the ImageManagerInterface for this project.
   * @throws IOException throws IOException when an input or output operation cannot be preformed.
   */
  public void goControllerForScript(ImageManagerInterface imageProvided) throws IOException {
    Objects.requireNonNull(imageProvided);
    Scanner input = new Scanner(this.in);
    ImageInterface image = null;
    try {
      while (true) {
        switch (input.next()) {
          case "load":
            try {
              String fileName = input.next();
              image = imageProvided.loadImage(fileName);
            } catch (IOException e) {
              System.out.println("File Not found.");
            }
            break;
          case "blur":
            try {
              image.blurImage();
            } catch (NullPointerException e) {
              System.out.println("No file Loaded. Please Try again.");
              return;
            }
            break;
          case "sharpen":
            try {
              image.sharpenImage();
            } catch (NullPointerException e) {
              System.out.println("No file Loaded. Please Try again.");
              return;
            }
            break;
          case "gray":
            try {
              image.grayScale();
            } catch (NullPointerException e) {
              System.out.println("No file Loaded. Please Try again.");
              return;
            }
            break;
          case "sepia":
            try {
              image.sepiaTone();
            } catch (NullPointerException e) {
              System.out.println("No file Loaded. Please Try again.");
              return;
            }
            break;
          case "dither":
            try {
              image.dither();
            } catch (NullPointerException e) {
              System.out.println("No file Loaded. Please Try again.");
              return;
            }
            break;
          case "mosaic":
            try {
              List<Integer> dimensions = validTwoInputs(input);
              if (dimensions.size() == 1) {
                image.mosaic(dimensions.get(0));
              } else {
                System.out.println("Incorrect seed input. Try again.");
                return;
              }
            } catch (NullPointerException e) {
              System.out.println("No file Loaded. Please Try again.");
              return;
            }
            break;
          case "horizontalRainbow":

            image = imageProvided.createImage();
            List<Integer> dimensions = validTwoInputs(input);
            if (dimensions.size() == 2) {
              image.rainbowHorizontal(dimensions.get(0), dimensions.get(1));
            } else {
              System.out.println("Incorrect Dimension Input. Try again.");
              return;
            }
            break;

          case "verticalRainbow":

            image = imageProvided.createImage();
            dimensions = validTwoInputs(input);
            if (dimensions.size() == 2) {
              image.rainbowVertical(dimensions.get(0), dimensions.get(1));
            } else {
              System.out.println("Incorrect Dimension Input. Try again.");
              return;
            }
            break;

          case "checkerboard":

            image = imageProvided.createImage();
            dimensions = validTwoInputs(input);
            if (dimensions.size() == 1) {
              image.checkerBoard(dimensions.get(0));
            } else {
              System.out.println("Incorrect Dimension Input. Try again.");
              return;
            }

            break;

          case "france":

            image = imageProvided.createImage();
            dimensions = validTwoInputs(input);
            if (dimensions.size() == 2) {
              image.flagFrance(dimensions.get(0), dimensions.get(1));
            } else {
              System.out.println("Incorrect Dimension Input. Try again.");
              return;
            }

            break;
          case "greece":

            image = imageProvided.createImage();
            dimensions = validTwoInputs(input);
            if (dimensions.size() == 2) {
              image.flagGreek(dimensions.get(0), dimensions.get(1));
            } else {
              System.out.println("Incorrect Dimension Input. Try again.");
              return;
            }
            break;
          case "switzerland":

            image = imageProvided.createImage();
            dimensions = validTwoInputs(input);
            if (dimensions.size() == 1) {
              image.flagSwitzerland(dimensions.get(0));
            } else {
              System.out.println("Incorrect Dimension Input. Try again.");
              return;
            }
            break;
          case "save":
            try {
              if (image != null) {
                String filePath = input.next();
                String fileNameForSave = input.next();
                imageProvided.saveImage(image, filePath, fileNameForSave);
              }
            } catch (IOException e) {
              System.out.println("Folder to save to not found.");
            }
            break;
          default:
            return;
        }
      }
    } catch (NoSuchElementException e) {
      return;
    }
  }

  /**
   * This private helper method is to check if the user provides proper dimensions for the methods
   * that require user specifications on size and the number of seeds.
   *
   * @param input the token that the scanner is on.
   * @return array list of integer dimensions that the user has provided.
   */
  private List<Integer> validTwoInputs(Scanner input) {
    List<Integer> dimensions = new ArrayList<>();
    while (input.hasNextInt()) {
      dimensions.add(input.nextInt());
    }
    return dimensions;
  }
}



