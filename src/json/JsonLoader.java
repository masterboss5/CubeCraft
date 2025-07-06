package json;

import com.google.gson.Gson;
import exception.JsonParsingException;
import graphic.BlockModel;
import graphic.ModelBuilder;
import graphic.Texture;
import registry.Registries;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {
    public static final String ROOT_MODEL_PATH = "C:/Users/Armen/Desktop/CubeCraft/resources/models/";
    public static final Gson INSTANCE = new Gson();

    public static BlockModel readBlockModelJson(String fileName) {
        ReadBlockModel readBlockModel;

        try (FileReader fileReader = new FileReader(ROOT_MODEL_PATH + fileName)) {
            readBlockModel = INSTANCE.fromJson(fileReader, ReadBlockModel.class);
        } catch (IOException exception) {
            throw new JsonParsingException(JsonParsingException.LoadingContext.MODEL, fileName);
        }

        return ModelBuilder.buildBlockModel(Registries.SHADER_PROGRAM.get(readBlockModel.shader_program()), readBlockModel.vertices(), readBlockModel.indices(), readBlockModel.uv(), new Texture(readBlockModel.texture()));
    }
}
