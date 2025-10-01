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
    private final Chunk chunk;
    private final VertexBuffer vertexBuffer;

    public ChunkMesh(Chunk chunk, VertexBuffer vertexBuffer) {
        this.chunk = chunk;
        this.vertexBuffer = vertexBuffer;
    }

    public VertexBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public Matrix4f getTransformationMatrix(Matrix4f matrices) {
        matrices.identity();
        int chunkX = this.getChunk().getChunkPosition().getX() * Chunk.CHUNK_WIDTH;
        int chunkZ = this.getChunk().getChunkPosition().getZ() * Chunk.CHUNK_WIDTH;
        matrices.translate(chunkX, 0, chunkZ);

        return matrices;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
