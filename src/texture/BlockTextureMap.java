package texture;

import json.ReadBlockModel;
import json.TextureArrays;
import registry.Registries;

public record BlockTextureMap(int top, int bottom, int front, int back, int left, int right) {
    public BlockTextureMap(ReadBlockModel readBlockModel) {
        this(
                get(readBlockModel.top()),
                get(readBlockModel.bottom()),
                get(readBlockModel.front()),
                get(readBlockModel.back()),
                get(readBlockModel.left()),
                get(readBlockModel.right())
        );
    }

    public int[] toVertexBuffer() {
        return new int[] {
                top, top, top, top,
                bottom, bottom, bottom, bottom,
                front, front, front, front,
                back, back, back, back,
                left, left, left, left,
                right, right, right, right
        };
    }

    private static int get(String key) {
        Texture texture = Registries.TEXTURES.get(key);

        if (key == null) {
            return -1;
        }

        return TextureArrays.BLOCK_TEXTURE_ARRAY.registerOrGet(texture);
    }
}
