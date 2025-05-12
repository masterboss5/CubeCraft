package graphic;

import shader.ShaderProgram;

public class BasicModel extends Model {
    public BasicModel(ShaderProgram shaderProgram, int vaoID, int vertices, int indices) {
        super(shaderProgram, vaoID, vertices, indices);
    }
}
