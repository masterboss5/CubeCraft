package json;

import com.google.gson.Gson;
import exception.JsonParsingException;
import graphic.BlockModel;
import graphic.ModelBuilder;
import texture.BlockTextureMap;
import registry.Registries;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {
    public static final Gson INSTANCE = new Gson();
    public static final String ROOT_MODEL_PATH = "C:/Users/Armen/Desktop/CubeCraft/resources/models/";
    public static final String ROOT_TEXTURE_PATH = "C:/Users/Armen/Desktop/CubeCraft/resources/textures/";

    public static BlockModel readBlockModelJson(String fileName) {
        ReadBlockModel readBlockModel;

        try (FileReader fileReader = new FileReader(ROOT_MODEL_PATH + fileName)) {
            readBlockModel = INSTANCE.fromJson(fileReader, ReadBlockModel.class);
        } catch (IOException exception) {
            throw new JsonParsingException(JsonParsingException.LoadingContext.MODEL, fileName);
        }

        return ModelBuilder.buildBlockModel(Registries.SHADER_PROGRAM.get("texture_shader"),
                readBlockModel.vertices(),
                readBlockModel.indices(),
                readBlockModel.uv(),
                new BlockTextureMap(readBlockModel));
    }
}
