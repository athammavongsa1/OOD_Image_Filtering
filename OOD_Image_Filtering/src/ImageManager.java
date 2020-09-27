import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class represents the implementation of the utility interface. The purpose of this
 * implementation is to isolate tasks within the contoller so that the model is only initialized
 * once from within the controler.
 */
public class ImageManager implements ImageManagerInterface {

  @Override
  public ImageInterface loadImage(String fileName) throws IOException {
    ImageInterface imageObject = null;
    int[][][] myImage = ImageUtil.readImage(fileName);
    int myImageWidth = ImageUtil.getWidth(fileName);
    int myImageHeight = ImageUtil.getHeight(fileName);
    imageObject = new Image(myImage, myImageWidth, myImageHeight);
    return imageObject;
  }

  @Override
  public ImageInterface createImage() {
    try {
      int[][][] myImage = new int[0][0][0];
      ImageInterface imageObject = new Image(myImage, 0, 0);
      return imageObject;
    } catch (NullPointerException e) {
      return null;
    }
  }

  @Override
  public void saveImage(ImageInterface imageObject, String filePath, String saveAsFileName)
          throws IOException {
    ImageUtil.writeImage(imageObject.getMyImage(), imageObject.getMyImageWidth(),
            imageObject.getMyImageHeight(),
            "" + filePath + "/" + saveAsFileName);
  }

  @Override
  public BufferedImage displayImage(int[][][] rgb, int width, int height) {
    try {
      BufferedImage output = new BufferedImage(
              width,
              height,
              BufferedImage.TYPE_INT_RGB);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = rgb[i][j][0];
          int g = rgb[i][j][1];
          int b = rgb[i][j][2];

          //color is stored in 1 integer, with the 4 bytes storing ARGB in that
          //order. Each of r,g,b are stored in 8 bits (hence between 0 and 255).
          // So we put them all in one integer by using bit-shifting << as below
          int color = (r << 16) + (g << 8) + b;
          output.setRGB(j, i, color);
        }
      }
      return output;
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}

