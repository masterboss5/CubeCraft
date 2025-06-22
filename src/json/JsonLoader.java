package json;

import com.google.gson.Gson;
import graphic.BlockModel;
import graphic.ModelBuilder;
import shader.ShaderPrograms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

public class JsonLoader {
    public static final String ROOT_PATH = "C:/Users/Armen/Desktop/CubeCraft/resources/models/";
    public static final Gson INSTANCE = new Gson();

    public static BlockModel readBlockModelJSON(String fileName) throws IOException {
        ReadBlockModel readBlockModel;

        try (FileReader fileReader = new FileReader(ROOT_PATH + fileName)) {
            readBlockModel = INSTANCE.fromJson(fileReader, ReadBlockModel.class);
        } catch (FileNotFoundException exception) {
            throw new RemoteException(exception.getMessage());
        }

        return ModelBuilder.buildBlockModel(ShaderPrograms.CUBE_ALL_TEXTURED_SHADER_PROGRAM, readBlockModel.vertices(), readBlockModel.indices(), readBlockModel.uv());
    }
}
