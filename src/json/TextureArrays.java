package json;

import texture.TextureArray;

public class TextureArrays {
    public static final TextureArray BLOCK_TEXTURE_ARRAY = new TextureArray("block_texture_array", 0, 24, 1920, 1920);

    private TextureArrays() {
        throw new UnsupportedOperationException();
    }

    public static void loadTextureArrays() {
    }
}
