package graphic;

import gl.VertexBuffer;
import shader.ShaderProgram;
import shader.TexturedShaderProgram;
import texture.BlockTextureMap;

public class BlockModel extends Model {
    private final BlockTextureMap textures;

    public BlockModel(ShaderProgram shaderProgram, VertexBuffer vertexBuffer, BlockTextureMap textures) {
        super(shaderProgram, vertexBuffer);

        this.textures = textures;

        System.out.println(textures);
    }

    public BlockTextureMap getTextures() {
        return textures;
    }

//    public int getTextureID() {
//        return this.getTextures().getTextureID();
//    }

    @Override
    public TexturedShaderProgram getShaderProgram() {
        return (TexturedShaderProgram) super.getShaderProgram();
    }
}
