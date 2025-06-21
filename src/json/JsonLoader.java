package json;

import com.google.gson.Gson;
import graphic.BlockModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class JsonLoader {
    public static final String ROOT_PATH = "C:/Users/Armen/Desktop/CubeCraft/resources/models/";
    public static final Gson INSTANCE = new Gson();

    public static BlockModel read(String fileName) throws IOException {
        ReadBlockModel readBlockModel;

        try (FileReader fileReader = new FileReader(ROOT_PATH + fileName)) {
            readBlockModel = INSTANCE.fromJson(fileReader, ReadBlockModel.class);
        } catch (FileNotFoundException e) {
            throw new RemoteException(e.getMessage());
        }

        System.out.println(Arrays.toString(readBlockModel.vertices()));
        System.out.println(Arrays.toString(readBlockModel.indices()));
        System.out.println(Arrays.toString(readBlockModel.uv()));

        return null;
    }
}
