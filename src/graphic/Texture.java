package graphic;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

public class Texture {
    private int width;
    private int height;
    final private int textureID;

    public Texture(String path) {
        this.textureID = this.loadFromPath(path);
    }

    private int loadFromPath(String path) {
        int[] pixels = null;

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            this.width = image.getWidth();
            this.height = image.getHeight();
            pixels = new int[this.width * this.height];
            image.getRGB(0, 0, this.width, this.height, pixels, 0, this.width);
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

        int textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_MIRRORED_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);

        IntBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(data).flip();

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glBindTexture(GL_TEXTURE_2D, 0);

        return textureID;
    }

    public int getTextureID() {
        return textureID;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}