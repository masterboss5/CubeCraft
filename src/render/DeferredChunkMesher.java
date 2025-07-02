package render;

import world.Chunk;

public class DeferredChunkMesher implements ChunkMesher {
    @Override
    public native ChunkMesh meshChunk(Chunk chunk);
}
