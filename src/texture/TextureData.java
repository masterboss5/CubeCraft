package texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class TextureData {
    private final int width;
    private final int height;
    private final int[] pixels;

    public TextureData(String path) {
        int w = 0;
        int h = 0;
        int[] rawPixels = null;

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            w = image.getWidth();
            h = image.getHeight();

            rawPixels = new int[w * h];
            image.getRGB(0, 0, w, h, rawPixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.width = w;
        this.height = h;
        this.pixels = new int[w * h];

        for (int i = 0; i < this.pixels.length; i++) {
            int argb = rawPixels[i];
            int a = (argb >> 24) & 0xFF;
            int r = (argb >> 16) & 0xFF;
            int g = (argb >> 8) & 0xFF;
            int b = argb & 0xFF;

            pixels[i] = (a << 24) | (b << 16) | (g << 8) | r;
        }
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TextureData that)) return false;
        return getWidth() == that.getWidth() && getHeight() == that.getHeight() && Objects.deepEquals(getPixels(), that.getPixels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight(), Arrays.hashCode(getPixels()));
    }
}