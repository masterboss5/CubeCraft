package render;

public class DeferredChunkMesher implements ChunkMesher {
    @Override
    public native ChunkMesh meshChunk();
}
