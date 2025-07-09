package graphic;

import gl.VertexBuffer;
import shader.ShaderProgram;
import shader.TexturedShaderProgram;
import texture.BlockTextureMap;

public class BlockModel extends Model {
    private final BlockTextureMap textures;
    private final BlockFaceMap faces;

    public BlockModel(ShaderProgram shaderProgram, VertexBuffer vertexBuffer, BlockTextureMap textures, BlockFaceMap faces) {
        super(shaderProgram, vertexBuffer);

        this.textures = textures;
        this.faces = faces;

        System.out.println(textures);
    }

    public BlockTextureMap getTextures() {
        return textures;
    }

    @Override
    public TexturedShaderProgram getShaderProgram() {
        return (TexturedShaderProgram) super.getShaderProgram();
    }

    public BlockFaceMap getFaces() {
        return faces;
    }
}
