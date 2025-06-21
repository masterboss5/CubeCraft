package graphic;

import gl.VertexBuffer;
import shader.TexturedShaderProgram;

public class BlockModel extends Model {
    public BlockModel(TexturedShaderProgram shaderProgram, VertexBuffer vertexBuffer) {
        super(shaderProgram, vertexBuffer);
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
