package graphic;

import json.JsonLoader;
import registry.Registries;

public class Models {
    public static final BlockModel AIR_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "air_block_model", JsonLoader.readBlockModelJson("air.json"));
    public static final BlockModel DIRT_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "dirt_block_model", JsonLoader.readBlockModelJson("dirt.json"));

    private Models() {
        throw new UnsupportedOperationException();
    }

    public static void loadModels() {
    }
}
