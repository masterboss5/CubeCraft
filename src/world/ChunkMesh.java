package world;

import gl.VertexBuffer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL46;
import registry.Registries;
import shader.ShaderProgram;
import shader.ShaderPrograms;
import util.MathUtils;

public class ChunkMesh {
    private Matrix4f transformationMatrix;
    private final Chunk chunk;
    private final VertexBuffer vertexBuffer;
    private final ShaderProgram shaderProgram = ShaderPrograms.CUBE_ALL_TEXTURED_SHADER_PROGRAM;

    public ChunkMesh(Chunk chunk, VertexBuffer vertexBuffer) {
        this.chunk = chunk;
        this.vertexBuffer = vertexBuffer;
        this.createTransformationMatrix();
    }

    private void createTransformationMatrix() {
        Vector3f chunkPosition = this.getChunk().getChunkPosition().toVector3f();
        Vector3f chunkWorldPos = new Vector3f(
                chunkPosition.x * Chunk.CHUNK_WIDTH,
                chunkPosition.y * Chunk.CHUNK_HEIGHT,
                chunkPosition.z * Chunk.CHUNK_WIDTH
        );

        this.transformationMatrix = MathUtils.createTransformationMatrix(
                chunkWorldPos,
                new Vector3f(),
                new Vector3f(1));
    }

    public void startShaderProgram() {
        GL46.glUseProgram(this.getShaderProgram().getProgramID());
    }

    public VertexBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public Matrix4f getTransformationMatrix() {
        return transformationMatrix;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
