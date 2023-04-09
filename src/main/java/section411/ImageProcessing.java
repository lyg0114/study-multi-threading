package section411;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author : iyeong-gyo
 * @package : section411
 * @since : 2023/04/09
 */
public class ImageProcessing {

  public static final String SOURCE_FILE = "/Users/iyeong-gyo/Desktop/study/java-study/multi-threading/src/main/resources/many-flowers.jpg";
  public static final String DESTATION_FILE = "/Users/iyeong-gyo/Desktop/study/java-study/multi-threading/src/main/resources/resources/out/result-flowers.jpg";

  public static void main(String[] args) throws IOException {
    BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
    BufferedImage resultImage = new BufferedImage(originalImage.getWidth(),
        originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

    File outFile = new File(DESTATION_FILE);
    ImageIO.write(resultImage, "jpg", outFile);
  }

  public static void recolorSingleThread(BufferedImage original, BufferedImage result) {
    recolorImage(original, result, 0, 0, original.getWidth(), original.getHeight());
  }

  public static void recolorImage(BufferedImage original, BufferedImage result,
      int leftCorner, int topCorner, int width, int height) {
    for (int x = leftCorner; x < leftCorner + width && x < original.getWidth(); x++) {
      for (int y = topCorner; y < topCorner + height && y < original.getHeight(); y++) {
        recolorPixel(original, result, x, y);
      }
    }
  }

  public static void recolorPixel(BufferedImage original, BufferedImage result, int x, int y) {
    int rgb = original.getRGB(x, y);

    int red = getRed(rgb);
    int green = getGreen(rgb);
    int blue = getBlue(rgb);

    int nRed;
    int nGreen;
    int nBlue;

    if (isShadeOfGray(red, green, blue)) {
      nRed = Math.min(255, red + 10);
      nGreen = Math.max(0, green - 80);
      nBlue = Math.max(0, blue - 20);
    } else {
      nRed = red;
      nGreen = green;
      nBlue = blue;
    }
    int nRGB = createRGBFromColors(nRed, nGreen, nBlue);
    setRGB(result, x, y, nRGB);
  }

  public static void setRGB(BufferedImage image, int x, int y, int rgb) {
    image.getRaster()
        .setDataElements(x, y, image.getColorModel()
            .getDataElements(rgb, null))
    ;
  }

  public static boolean isShadeOfGray(int red, int green, int blue) {
    return Math.abs(red - green) < 30
        && Math.abs(red - blue) < 30
        && Math.abs(green - blue) < 30
        ;
  }

  public static int createRGBFromColors(int red, int green, int blue) {
    int rgb = 0;
    rgb |= blue;
    rgb |= green << 8;
    rgb |= blue << 16;
    rgb |= 0xFF000000;
    return rgb;
  }


  public static int getRed(int rgb) {
    return (rgb & 0x00FF0000) >> 16;
  }

  public static int getGreen(int rgb) {
    return (rgb & 0x0000FF00) >> 8;
  }

  public static int getBlue(int rgb) {
    return rgb & 0x000000FF;
  }
}
