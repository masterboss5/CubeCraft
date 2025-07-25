package texture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL46;

public class TextureManager {
    private static int blockTextureArrayID;

    public static void createTextureArrays(int width, int height, int count) {
        blockTextureArrayID = GL46.glGenTextures();
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, blockTextureArrayID);

        GL46.glTexStorage3D(GL46.GL_TEXTURE_2D_ARRAY, 1, GL46.GL_RGBA8, width, height, count);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_MIN_FILTER, GL46.GL_NEAREST);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_MAG_FILTER, GL46.GL_LINEAR);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_WRAP_S, GL46.GL_MIRRORED_REPEAT);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_WRAP_T, GL46.GL_MIRRORED_REPEAT);

        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, 0);
    }

    public static void upload(Texture texture, int textureIndex) {
        int width = texture.getWidth();
        int height = texture.getHeight();
        int[] pixels = texture.getPixels();

//        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, blockTextureArrayID);
        GL46.glTexSubImage3D(GL46.GL_TEXTURE_2D_ARRAY, 0,
                0, 0, textureIndex,
                width, height, 1,
                GL46.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
                pixels);
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, 0);
    }

    public static int getBlockTextureArrayID() {
        return blockTextureArrayID;
    }
}
