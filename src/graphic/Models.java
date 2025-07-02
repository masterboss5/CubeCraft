package graphic;

import json.JsonLoader;
import registry.Registries;

public class Models {
    public static final BlockModel AIR_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "air_block_model", JsonLoader.readBlockModelJSON("air.json"));
     public static final BlockModel GRASS_BLOCK_MODEL = (BlockModel) Registries.register(Registries.MODEL, "grass_block_model", JsonLoader.readBlockModelJSON("cube.json"));

    public static void loadModels() {
    }

    private Models() {
        throw new UnsupportedOperationException();
    }
}
