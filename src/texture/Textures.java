package texture;

import registry.Registries;

public class Textures {
    public static final TextureData DIRT_BLOCK = Registries.TEXTURES.register("dirt_block_texture", new TextureData("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\grass.png"));

    private Textures() {
        throw new UnsupportedOperationException();
    }

    public static void loadTextures() {
    }
}
