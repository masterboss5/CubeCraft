package graphic;

import shader.TexturedShaderProgram;

public class BlockModel extends Model {
    public BlockModel(TexturedShaderProgram shaderProgram, VertexBuffer vertexBuffer, int vertices, int indices) {
        super(shaderProgram, vertexBuffer, vertices, indices);
    }

    public Texture getTexture() {
        return this.getShaderProgram().getTexture();
    }

    public int getTextureID() {
        return this.getTexture().getTextureID();
    }

    @Override
    public TexturedShaderProgram getShaderProgram() {
        return (TexturedShaderProgram) super.getShaderProgram();
    }
}
