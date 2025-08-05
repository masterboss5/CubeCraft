package texture;

import java.util.HashMap;
import java.util.Map;

public class BindlessTextureStorage {
    private final String name;
    private final Map<Texture, Integer> textures = new HashMap<>();

    public BindlessTextureStorage(String name) {
        this.name = name;
    }


}
