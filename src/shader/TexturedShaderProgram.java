package shader;

import graphic.Texture;

public class TexturedShaderProgram extends ShaderProgram {
    final private Texture texture;

    TexturedShaderProgram(String vertexShaderFilePath, String fragmentShaderFilePath, Texture texture) {
        super(vertexShaderFilePath, fragmentShaderFilePath);
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void tickShaderProgram() {
    }
}
