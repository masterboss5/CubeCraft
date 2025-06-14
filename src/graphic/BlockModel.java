package graphic;

import shader.TexturedShaderProgram;

public class BlockModel extends Model {
    public BlockModel(TexturedShaderProgram shaderProgram, int vaoID, int vertices, int indices) {
        super(shaderProgram, vaoID, vertices, indices);
    }

    public Texture getTexture() {
        return this.getShaderProgram().getTexture();
    }

    @Override
    public TexturedShaderProgram getShaderProgram() {
        return (TexturedShaderProgram) super.getShaderProgram();
    }
}
