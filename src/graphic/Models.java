package graphic;

import json.JsonLoader;

import java.io.IOException;

public class Models {
    public static void loadModels() {
    }

    public static final BlockModel GRASS_BLOCK_MODEL;

    static {
        try {
            GRASS_BLOCK_MODEL = JsonLoader.readBlockModelJSON("cube.json");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
