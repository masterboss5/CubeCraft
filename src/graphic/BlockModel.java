package graphic;

import gl.VertexBuffer;
import shader.ShaderProgram;
import shader.TexturedShaderProgram;
import texture.BlockTextureMap;

public class BlockModel extends Model {
    private final BlockTextureMap textures;
    private final BlockFaceMap faces;
    private final float[] uvCoordinates;

    public BlockModel(ShaderProgram shaderProgram, VertexBuffer vertexBuffer, BlockTextureMap textures, BlockFaceMap faces, float[] uvCoordinates) {
        super(shaderProgram, vertexBuffer);

        this.textures = textures;
        this.faces = faces;
        this.uvCoordinates = uvCoordinates;

//        System.out.println(textures);
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

    public float[] getUvCoordinates() {
        return uvCoordinates;
    }
}
