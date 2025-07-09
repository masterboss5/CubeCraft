package texture;

import registry.Registries;

public class Textures {
    public static final Texture DIRT_BLOCK_TEXTURE = Registries.TEXTURES.register("dirt_block_texture", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\dirt_block.png"));
    public static final Texture GRASS_BLOCK_TOP_TEXTURE = Registries.TEXTURES.register("grass_block_top_texture", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\grass_block_top.png"));
    public static final Texture GRASS_BLOCK_SIDE_TEXTURE = Registries.TEXTURES.register("grass_block_side_texture", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\grass_block_side.png"));
    public static final Texture COBBLESTONE_BLOCK_TEXTURE = Registries.TEXTURES.register("cobblestone_block_texture", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cobblestone_block.png"));
    public static final Texture OAK_PLANKS_BLOCK_TEXTURE = Registries.TEXTURES.register("oak_planks_block_texture", new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\oak_plank_block.png"));

    private Textures() {
        throw new UnsupportedOperationException();
    }

    public static void loadTextures() {
    }
}
