package texture;

import json.ReadBlockModel;
import registry.Registries;

public record BlockTextureMap(TextureData top, TextureData bottom, TextureData front, TextureData back, TextureData left, TextureData right) {
    public BlockTextureMap(ReadBlockModel readBlockModel) {
        this(get(readBlockModel.top()),
                get(readBlockModel.bottom()),
                get(readBlockModel.front()),
                get(readBlockModel.bottom()),
                get(readBlockModel.left()),
                get(readBlockModel.right()));
    }

    private static TextureData get(String key) {
        return Registries.TEXTURES.get(key);
    }
}
