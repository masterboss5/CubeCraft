package graphic;

import shader.ShaderProgram;

public class BlockModel extends Model {
    public BlockModel(ShaderProgram shaderProgram, int vaoID, int vertices, int indices) {
        super(shaderProgram, vaoID, vertices, indices);
    }
}
