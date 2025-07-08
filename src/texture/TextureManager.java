package texture;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL46;

import java.nio.ByteBuffer;

public class TextureManager {
    private static int blockTextureArrayID;

    public static void createTextureArrays() {
        blockTextureArrayID = GL46.glGenTextures();
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, blockTextureArrayID);

        int width = 1920;
        int height = 1920;
        int count = 4;

        GL46.glTexStorage3D(GL46.GL_TEXTURE_2D_ARRAY, 1, GL46.GL_RGBA8, width, height, count);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_MIN_FILTER, GL46.GL_NEAREST);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_MAG_FILTER, GL46.GL_LINEAR);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_WRAP_S, GL46.GL_MIRRORED_REPEAT);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_WRAP_T, GL46.GL_MIRRORED_REPEAT);

        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, 0);
    }

    public static void upload(TextureData texture) {
        int width = texture.getWidth();
        int height = texture.getHeight();
        int[] pixels = texture.getPixels();

        // Step 1: Convert int[] pixels → ByteBuffer in RGBA order
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            int a = (pixel >> 24) & 0xFF;
            int r = (pixel >> 16) & 0xFF;
            int g = (pixel >> 8) & 0xFF;
            int b = pixel & 0xFF;

            buffer.put((byte) r);
            buffer.put((byte) g);
            buffer.put((byte) b);
            buffer.put((byte) a);
        }
        buffer.flip();

        // Step 2: Upload to OpenGL
        int textureIndex = 0; // or use textureArrayType.ordinal() if you expand
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, blockTextureArrayID);
        GL46.glTexSubImage3D(GL46.GL_TEXTURE_2D_ARRAY, 0,
                0, 0, textureIndex,
                width, height, 1,
                GL46.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
                buffer);
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, 0);
    }

    public static int getBlockTextureArrayID() {
        return blockTextureArrayID;
    }
}
