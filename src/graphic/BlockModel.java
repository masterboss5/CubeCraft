package graphic;

import gl.VertexBuffer;
import shader.ShaderProgram;
import shader.TexturedShaderProgram;

public class BlockModel extends Model {
    private final Texture texture;

    public BlockModel(ShaderProgram shaderProgram, VertexBuffer vertexBuffer, Texture texture) {
        super(shaderProgram, vertexBuffer);

        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getTextureID() {
        return this.getTexture().getTextureID();
    }

    @Override
    public TexturedShaderProgram getShaderProgram() {
        return (TexturedShaderProgram) super.getShaderProgram();
    }
}
