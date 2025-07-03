package world;

import gl.VertexBuffer;
import registry.Registries;
import shader.ShaderProgram;

public class ChunkMesh {
    private final VertexBuffer vertexBuffer;
    private final ShaderProgram shaderProgram = Registries.SHADER_PROGRAM.get("color_shader");

    public ChunkMesh(VertexBuffer vertexBuffer) {
        this.vertexBuffer = vertexBuffer;
    }

    public VertexBuffer getVertexBuffer() {
        return vertexBuffer;
    }
}
