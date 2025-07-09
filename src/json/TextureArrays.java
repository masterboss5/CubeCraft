package json;

import texture.TextureArray;

public class TextureArrays {
    public static final TextureArray BLOCK_TEXTURE_ARRAY = new TextureArray("block_texture_array", 0, 2048, 16, 16);

    private TextureArrays() {
        throw new UnsupportedOperationException();
    }

    public static void loadTextureArrays() {
    }
}
