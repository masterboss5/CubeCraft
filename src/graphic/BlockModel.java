package graphic;

import gl.VertexBuffer;
import shader.ShaderProgram;
import shader.TexturedShaderProgram;
import texture.BlockTextureMap;
import texture.BlockUVMap;

public class BlockModel extends Model {
    private final BlockTextureMap textures;
    private final BlockFaceMap faces;
    private final BlockUVMap uvBlockMap;

    public BlockModel(ShaderProgram shaderProgram, VertexBuffer vertexBuffer, BlockTextureMap textures, BlockFaceMap faces, BlockUVMap uvBlockMap) {
        super(shaderProgram, vertexBuffer);

        this.textures = textures;
        this.faces = faces;
        this.uvBlockMap = uvBlockMap;
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

    public BlockUVMap getUvBlockMap() {
        return uvBlockMap;
    }
}
