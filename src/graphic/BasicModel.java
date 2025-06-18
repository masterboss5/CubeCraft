package graphic;

import shader.ShaderProgram;

public class BasicModel extends Model {
    public BasicModel(ShaderProgram shaderProgram, VertexBuffer vertexBuffer, int vertices, int indices) {
        super(shaderProgram, vertexBuffer, vertices, indices);
    }
}
