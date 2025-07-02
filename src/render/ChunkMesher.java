package render;

import world.Chunk;

public interface ChunkMesher {
    ChunkMesh meshChunk(Chunk chunk);
}
