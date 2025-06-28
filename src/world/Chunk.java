package world;

import block.Block;

public class Chunk {
    Block[][][] blockGrid = new Block[ChunkPosition.CHUNK_WIDTH][ChunkPosition.CHUNK_HEIGHT][ChunkPosition.CHUNK_WIDTH];

    protected Chunk() {

    }
}
