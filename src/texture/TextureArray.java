package texture;

import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL46;

public class TextureArray {
    private final String name;
    private final int textureUnit;
    private final int ID;
    private final int size;
    private final int width;
    private final int height;
    private boolean bound;

    public TextureArray(String name, int textureUnit, int size, int width, int height) {
        this.name = name;
        this.textureUnit = textureUnit;
        this.ID = GL46.glGenTextures();
        this.size = size;
        this.width = width;
        this.height = height;
    }

    public void allocate() {
        this.bind();

        GL46.glTexStorage3D(
                GL46.GL_TEXTURE_2D_ARRAY,
                1,
                GL46.GL_RGBA8,
                this.getWidth(),
                this.getHeight(),
                this.getSize()
        );

        this.unbind();
    }

    public void upload(TextureData textureData, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Texture layer " + index + " out of bounds (0–" + (size - 1) + ")");
        }

        int width = textureData.getWidth();
        int height = textureData.getHeight();
        int[] pixels = textureData.getPixels();

        this.bind();

        GL46.glTexSubImage3D(
                GL46.GL_TEXTURE_2D_ARRAY,
                0,
                0,
                0,
                index,
                width,
                height,
                1,
                GL46.GL_RGBA,
                GL46.GL_UNSIGNED_BYTE,
                pixels
        );

        this.unbind();
    }

    public void texParameters() {
        this.bind();

        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_MIN_FILTER, GL46.GL_NEAREST);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_MAG_FILTER, GL46.GL_LINEAR);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_WRAP_S, GL46.GL_MIRRORED_REPEAT);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D_ARRAY, GL46.GL_TEXTURE_WRAP_T, GL46.GL_MIRRORED_REPEAT);

        this.unbind();
    }

    public void bindForRendering() {
        GL46.glActiveTexture(GL13.GL_TEXTURE0 + this.getTextureUnit());
        this.bind();
    }

    public void bind() {
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, this.ID);
        this.bound = true;
    }

    public void unbind() {
        GL46.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, 0);
        this.bound = false;
    }

    public void destroy() {
        this.unbind();
        GL46.glDeleteTextures(this.getID());
    }

    public String getName() {
        return name;
    }

    public int getTextureUnit() {
        return textureUnit;
    }

    public int getID() {
        return ID;
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isBound() {
        return bound;
    }
}
