package texture;

import json.ReadBlockModel;

public record BlockTextureMap(String top, String bottom, String front, String back, String left, String right) {
    public BlockTextureMap(ReadBlockModel readBlockModel) {
        this(readBlockModel.top(), readBlockModel.bottom(), readBlockModel.front(), readBlockModel.back(), readBlockModel.left(), readBlockModel.right());
    }
}
