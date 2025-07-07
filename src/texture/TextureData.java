package texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class TextureData {
    private int width;
    private int height;
    private int[] pixels;

    public TextureData(String path) {
        this.pixels = this.loadFromPath(path);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    private int[] loadFromPath(String path) {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.pixels = new int[this.width * this.height];
            image.getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        int[] data = new int[this.width * this.height];

        for (int i = 0; i < this.width * this.height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        return data;
    }
}
