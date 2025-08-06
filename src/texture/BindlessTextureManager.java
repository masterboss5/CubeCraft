package texture;

import org.lwjgl.opengl.ARBBindlessTexture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL46;

import java.util.HashMap;
import java.util.Map;

public class BindlessTextureManager {
    private final String name;
    private int storedTextures;
    private final Map<Texture, Long> textures = new HashMap<>();
    private final Map<Texture, Integer> textureIDs = new HashMap<>();

    public BindlessTextureManager(String name) {
        this.name = name;
    }

    public long registerOrGet(Texture texture) {
        return this.textures.computeIfAbsent(texture, (absentTexture -> {
            long pointer = this.upload(absentTexture);
            this.storedTextures = this.storedTextures + 1;

            return pointer;
        }));
    }

    private long upload(Texture texture) {
        int textureID = GL46.glGenTextures();
        GL46.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        GL46.glTexImage2D(
                GL11.GL_TEXTURE_2D,
                0,
                GL46.GL_RGBA8,
                texture.getWidth(),
                texture.getHeight(),
                0,
                GL46.GL_RGBA,
                GL11.GL_UNSIGNED_BYTE,
                texture.getPixels()
        );

        this.textureIDs.put(texture, textureID);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_MIN_FILTER, GL46.GL_NEAREST);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_MAG_FILTER, GL46.GL_NEAREST);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_WRAP_S, GL46.GL_MIRRORED_REPEAT);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_WRAP_T, GL46.GL_MIRRORED_REPEAT);

        long pointer = ARBBindlessTexture.glGetTextureHandleARB(textureID);
        ARBBindlessTexture.glMakeTextureHandleResidentARB(pointer);
        GL46.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        return pointer;
    }
}
