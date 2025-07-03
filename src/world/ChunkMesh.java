package world;

import gl.VertexBuffer;
import org.lwjgl.opengl.GL46;
import registry.Registries;
import shader.ShaderProgram;

public class ChunkMesh {
    private final VertexBuffer vertexBuffer;
    private final ShaderProgram shaderProgram = Registries.SHADER_PROGRAM.get("texture_shader");

    public ChunkMesh(VertexBuffer vertexBuffer) {
        this.vertexBuffer = vertexBuffer;
    }

    public VertexBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public void startShaderProgram() {
        GL46.glUseProgram(this.getShaderProgram().getProgramID());
    }
}
