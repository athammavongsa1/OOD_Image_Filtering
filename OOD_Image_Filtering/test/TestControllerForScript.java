import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * This is the test class for the controller. The mock manager class isolates the controller from
 * the loading, creating and saving operations. The mock model class isolates the controller from
 * processing the image. The controller in this project accepts user input and uses the model when
 * input is sufficient to provide to the view. The testing was made simpler after isolating the
 * tasks of the controller.
 */

public class TestControllerForScript {

  //This tests loads image, uses a feature that needs a loaded image, and saves transformed image.
  @Test
  public void testOne() throws IOException {
    Readable in = new FileReader("testOne.txt");
    ControllerForScript testOne = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testOne.goControllerForScript(mockManager);

    assertEquals("blur done. ", logForModel.toString());
    assertEquals("Image loaded carrie.jpg done. Image saved carrieBlur.jpg done. ",
            logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }

  //This tests does not have a load command, uses a feature that needs a loaded image, and
  // saves transformed image. This test will result in an empty string since the controller realizes
  // that the input is invalid. User has to load image before calling methods that require a loaded
  // image.
  @Test
  public void testTwo() throws IOException {
    Readable in = new FileReader("testTwo.txt");
    ControllerForScript testTwo = new ControllerForScript(in);

    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testTwo.goControllerForScript(mockManager);
    assertEquals("", logForModel.toString());
    assertEquals("", logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }

  //This test loads image, uses a feature that does not needs a loaded image, and
  // saves transformed image. This creates the feature because though image is loaded, it is
  // does not effect creating the image as long as the dimensions are given properly.
  @Test
  public void testThree() throws IOException {
    Readable in = new FileReader("testThree.txt");
    ControllerForScript testThree = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testThree.goControllerForScript(mockManager);

    assertEquals("checker 30 done. ", logForModel.toString());
    assertEquals("Image loaded carrie.jpg done. Image created done."
                    + " Image saved checkerBoard.jpg done. ",
            logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }

  // This test does not load image but saves an image, uses a feature that does not
  // require a loaded image.
  @Test
  public void testFour() throws IOException {
    Readable in = new FileReader("testFour.txt");
    ControllerForScript testFour = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testFour.goControllerForScript(mockManager);

    //System.out.println(mockImage.getMyImageHeight());
    assertEquals("checker 44 done. ", logForModel.toString());
    assertEquals("Image created done. Image saved checkerBoard.jpg done. ",
            logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }

  // This test does not load image, but user calls save image. It shows that the mock model is not
  // called if the user does not load image before saving. It also shows that the image manager is
  // not called since neither loading nor saving is done correctly.
  @Test
  public void testFive() throws IOException {
    Readable in = new FileReader("testFive.txt");
    ControllerForScript testFive = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testFive.goControllerForScript(mockManager);
    assertEquals("", logForModel.toString());
    assertEquals("", logForImageManager.toString());
  }

  //This tests for methods that require dimensions. Here user calls a method that does not require
  // a preloaded image, however the user calls the feature with more than necessary dimensions.
  @Test
  public void testSix() throws IOException {
    Readable in = new FileReader("testSix.txt");
    ControllerForScript testSix = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testSix.goControllerForScript(mockManager);
    assertEquals("", logForModel.toString());
    assertEquals("Image created done. ", logForImageManager.toString());
  }

  // This tests a case where the image manager (which is not the model) is called but the model is
  // not. The user loads an image, however calls the image without following specifications. This
  // means that the image is loaded and saved; but model is never used since user specification is
  // incorrect.
  @Test
  public void testSeven() throws IOException {
    Readable in = new FileReader("testSeven.txt");
    ControllerForScript testSeven = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testSeven.goControllerForScript(mockManager);
    assertEquals("", logForModel.toString());
    assertEquals("Image loaded carrie.jpg done. ", logForImageManager.toString());
  }

  //This tests for multiple features called on one image.
  @Test
  public void testEight() throws IOException {
    Readable in = new FileReader("testEight.txt");
    ControllerForScript testEight = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testEight.goControllerForScript(mockManager);

    assertEquals("blur done. sepiaTone done. ", logForModel.toString());
    assertEquals("Image loaded carrie.jpg done. Image saved carrieSepiaAndBlur.jpg done. ",
            logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }

  //This tests for multiple features called and being saved to multiple images.
  @Test
  public void testNine() throws IOException {
    Readable in = new FileReader("testNine.txt");
    ControllerForScript testNine = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testNine.goControllerForScript(mockManager);

    assertEquals("blur done. sepiaTone done. ", logForModel.toString());
    assertEquals("Image loaded carrie.jpg done. Image saved carrieBlur.jpg done."
            + " Image saved carrieSepiaAndBlur.jpg done. ", logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }

  //This tests image loaded, then transformed and saved, and another image loaded (in this case
  // this is the original image), then transformed and saved as another file.
  @Test
  public void testTen() throws IOException {
    Readable in = new FileReader("testTen.txt");
    ControllerForScript testTen = new ControllerForScript(in);
    StringBuilder logForModel = new StringBuilder();
    StringBuilder logForImageManager = new StringBuilder();
    int[][][] uniqueArray = new int[2][3][3];
    ImageInterface mockImage = new MockModel(logForModel, 123, uniqueArray);
    ImageManagerInterface mockManager = new MockManager((mockImage), logForImageManager);
    testTen.goControllerForScript(mockManager);

    assertEquals("blur done. sepiaTone done. ", logForModel.toString());
    assertEquals("Image loaded carrie.jpg done. Image saved carrieBlur.jpg done." +
                    " Image loaded carrie.jpg done. Image saved carrieSepia.jpg done. ",
            logForImageManager.toString());

    assertEquals(uniqueArray, mockManager.createImage().getMyImage());
    assertEquals(123, mockManager.createImage().getMyImageWidth());
    assertEquals(124, mockManager.createImage().getMyImageHeight());
  }
}

