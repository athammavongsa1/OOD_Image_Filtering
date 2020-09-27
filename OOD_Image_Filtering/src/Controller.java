import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class acts as a formal controller for for the GUI part of the project. The roll of the
 * controller here is to take inputs from the user through the GUI. This class gets in user data
 * from the view, calls to the model. If input data from view is invalid in the model, controller
 * notifies the view of the error. An example of this situation would be if input data is negative
 * for its dimensions for certain features. If all data is valid, controller gets the data from the
 * model and outputs it to the view. The only other contact point for the controller is a utilities
 * manager interface. This interface is for creating, saving, loading, and displaying images. The
 * most important part is that this controller is independent of the GUI dependent view. As a
 * result, it doesn't have any JavaSwing related code.Therefore, the controller works for other
 * input formats. The controller also uses the model to output the transformed image to the view.
 */


public class Controller implements Features {
  private ImageInterface model;
  private ViewInterface view;
  private ImageManagerInterface imageMan;
  private List<BufferedImage> historyOfImages;

  /**
   * This the constructor for this Controller. It initializes this model, this view, an ImageManager
   * , and features necessary for the undo function.
   *
   * @param imageMan the ImageManagerInterface for this project. This is a utilites interface that
   *                 organizes access with the model.
   * @param v        the view for this controller.
   */
  public Controller(ImageManagerInterface imageMan, ViewInterface v) {
    this.imageMan = imageMan;
    this.model = null;
    this.view = v;
    view.addFeatures(this);
    this.historyOfImages = new ArrayList<>();
  }

  @Override
  public void addHistory() {
    if (this.model != null) {
      BufferedImage currentImage = this.imageMan.displayImage(this.model.getMyImage(),
              this.model.getMyImageWidth(), this.model.getMyImageHeight());
      historyOfImages.add(currentImage);
      System.out.println(historyOfImages.size() + " Size of list");
    }
  }

  @Override
  public void applyFilters(String command, String source) {
    switch (command) {
      case "Load":
        String text;
        if (source.equals("GUI")) {
          text = view.getFileInputString();
        } else {
          text = view.getFileName();
        }
        try {
          this.model = this.imageMan.loadImage(text);
          BufferedImage output = this.imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          historyOfImages.add(output);
          view.setOutput(output);
        } catch (IOException e) {
          view.fileNotFound();
        }
        break;

      case "Blur":
        if (this.model != null) {
          this.model.blurImage();
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } else {
          view.noImageLoaded();
        }
        break;

      case "Sharpen":
        if (this.model != null) {
          this.model.sharpenImage();
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } else {
          view.noImageLoaded();
        }
        break;

      case "Sepia":
        if (this.model != null) {
          this.model.sepiaTone();
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } else {
          view.noImageLoaded();
        }
        break;

      case "Grayscale":
        if (this.model != null) {
          this.model.grayScale();
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } else {
          view.noImageLoaded();
        }
        break;

      case "Dither":
        if (this.model != null) {
          this.model.dither();
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } else {
          view.noImageLoaded();
        }
        break;

      case "Mosaic":
        try {
          if (this.model != null) {
            int seeds;
            if (source.equals("GUI")) {
              seeds = view.getDimensionOne();
            } else {
              seeds = view.getBatchDimensionOne();
            }
            this.model.mosaic(seeds);
            BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                    this.model.getMyImageWidth(), this.model.getMyImageHeight());
            view.setOutput(output);
          } else {
            view.noImageLoaded();
          }
        } catch (IndexOutOfBoundsException e) {
          view.incorrectDimensions();
        }
        break;

      case "Checkerboard":
        try {
          this.model = this.imageMan.createImage();
          int squareSize;
          if (source.equals("GUI")) {
            squareSize = view.getDimensionOne();
          } else {
            squareSize = view.getBatchDimensionOne();
          }

          this.model.checkerBoard(squareSize);
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } catch (NegativeArraySizeException e) {
          view.incorrectDimensions();
        }
        break;

      case "Horizontal Rainbow":
        try {
          this.model = this.imageMan.createImage();
          int dimOne;
          int dimTwo;
          if (source.equals("GUI")) {
            dimOne = view.getDimensionOne();
            dimTwo = view.getDimensionTwo();
          } else {
            dimOne = view.getBatchDimensionOne();
            dimTwo = view.getBatchDimensionTwo();
          }
          this.model.rainbowHorizontal(dimOne, dimTwo);
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } catch (NegativeArraySizeException e) {
          view.incorrectDimensions();
        }
        break;
      case "Vertical Rainbow":
        try {
          this.model = this.imageMan.createImage();
          int dimOne;
          int dimTwo;
          if (source.equals("GUI")) {
            dimOne = view.getDimensionOne();
            dimTwo = view.getDimensionTwo();
          } else {
            dimOne = view.getBatchDimensionOne();
            dimTwo = view.getBatchDimensionTwo();
          }
          this.model.rainbowVertical(dimOne, dimTwo);
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } catch (NegativeArraySizeException e) {
          view.incorrectDimensions();
        }
        break;
      case "Flag France":
        try {
          int dimOne;
          int dimTwo;
          this.model = this.imageMan.createImage();
          if (source.equals("GUI")) {
            dimOne = view.getDimensionOne();
            dimTwo = view.getDimensionTwo();
          } else {
            dimOne = view.getBatchDimensionOne();
            dimTwo = view.getBatchDimensionTwo();
          }
          this.model.flagFrance(dimOne, dimTwo);
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } catch (NegativeArraySizeException e) {
          view.incorrectDimensions();
        }
        break;
      case "Flag Switzerland":
        try {
          int dimOne;
          this.model = this.imageMan.createImage();

          if (source.equals("GUI")) {
            dimOne = view.getDimensionOne();
          } else {
            dimOne = view.getBatchDimensionOne();
          }
          this.model.flagSwitzerland(dimOne);
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } catch (NegativeArraySizeException e) {
          view.incorrectDimensions();
        }
        break;
      case "Flag Greece":
        try {
          int dimOne;
          int dimTwo;
          this.model = this.imageMan.createImage();

          if (source.equals("GUI")) {
            dimOne = view.getDimensionOne();
            dimTwo = view.getDimensionTwo();
          } else {
            dimOne = view.getBatchDimensionOne();
            dimTwo = view.getBatchDimensionTwo();
          }
          this.model.flagGreek(dimOne, dimTwo);
          BufferedImage output = imageMan.displayImage(this.model.getMyImage(),
                  this.model.getMyImageWidth(), this.model.getMyImageHeight());
          view.setOutput(output);
        } catch (NegativeArraySizeException e) {
          view.incorrectDimensions();
        }
        break;
      case "Save Image":
        if (this.model != null) {
          try {
            {
              this.imageMan.saveImage(this.model, view.getFilePathSave(), view.getFileNameSaveAs());
            }
          } catch (IOException e) {
            view.fileNotFound();
          }
        } else {
          view.noImageLoaded();
        }
        break;
      default:
        break;
    }
  }

  @Override
  public void undoOperation(int amount) {
    try {
      historyOfImages.remove(historyOfImages.size() - 1);
      view.setOutput(historyOfImages.get(historyOfImages.size() - 1));
      this.model = new Image(readImage(historyOfImages.get(historyOfImages.size() - 1)),
              historyOfImages.get(historyOfImages.size() - 1).getWidth(),
              historyOfImages.get(historyOfImages.size() - 1).getHeight());
      //System.out.println(historyOfImages.size() + " Size of list after undo");
    } catch (NullPointerException | IndexOutOfBoundsException e) {
      return;
    }
  }

  private static int[][][] readImage(BufferedImage input) {
    int[][][] result = new int[input.getHeight()][input.getWidth()][3];

    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        result[i][j][0] = c.getRed();
        result[i][j][1] = c.getGreen();
        result[i][j][2] = c.getBlue();
      }
    }
    return result;
  }
}



