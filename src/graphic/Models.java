package graphic;

import json.JsonLoader;
import registry.Registries;

public class Models {
    public static final BlockModel AIR_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "air_block_model", JsonLoader.readBlockModelJson("air.json"));

    public static final BlockModel DIRT_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "dirt_block_model", JsonLoader.readBlockModelJson("dirt.json"));
    public static final BlockModel GRASS_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "grass_block_model", JsonLoader.readBlockModelJson("grass.json"));
    public static final BlockModel COBBLESTONE_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "cobblestone_block_model", JsonLoader.readBlockModelJson("cobblestone.json"));
    public static final BlockModel OAK_PLANKS_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "oak_planks_block_model", JsonLoader.readBlockModelJson("oak_planks.json"));

    private Models() {
        throw new UnsupportedOperationException();
    }

    public static void loadModels() {
    }
}
