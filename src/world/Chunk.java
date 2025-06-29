package world;

import block.Block;
import block.BlockPosition;

import java.util.UUID;

public class Chunk {
    private Block[][][] blockGrid = new Block[ChunkPosition.CHUNK_WIDTH][ChunkPosition.CHUNK_HEIGHT][ChunkPosition.CHUNK_WIDTH];
    private final ChunkPosition position;
    private boolean needsSaving = false;
    private boolean needsMeshing = false;
    private boolean isLoaded = false;
    private UUID ID = UUID.randomUUID();
    private final WorldChunkManager chunkManager;

    protected Chunk(ChunkPosition position, WorldChunkManager chunkManager) {
        this.position = position;
        this.chunkManager = chunkManager;

        chunkManager.cache(this);
    }

    public ChunkPosition getChunkPosition() {
        return position;
    }

    public Block getBlock(int x, int y, int z) {
        return this.blockGrid[x][y][z];
    }

    public Block getBlock(BlockPosition position) {
        return this.blockGrid[position.getX()][position.getY()][position.getZ()];
    }

    public void setBlock(Block newBlock, int x, int y, int z) {
        this.blockGrid[x][y][z] = newBlock;
        this.setNeedsMeshing();
        this.setNeedsSaving();
    }

    public void setBlock(Block newBlock, BlockPosition blockPosition) {
        this.setBlock(newBlock, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    public boolean isNeedsMeshing() {
        return needsMeshing;
    }

    public void setNeedsMeshing() {
        this.needsMeshing = true;
    }

    public boolean isNeedsSaving() {
        return needsSaving;
    }

    public void setNeedsSaving() {
        this.needsSaving = true;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public UUID getID() {
        return ID;
    }
}
