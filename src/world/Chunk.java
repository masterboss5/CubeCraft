package world;

import block.AirBlock;
import block.Block;
import block.BlockPosition;
import render.RenderSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chunk {
    private final WorldChunkManager chunkManager;
    private Block[][][] blockGrid = new Block[ChunkPosition.CHUNK_WIDTH][ChunkPosition.CHUNK_HEIGHT][ChunkPosition.CHUNK_WIDTH];
    private final ChunkPosition position;
    private ChunkMesh mesh;
    private boolean needsSaving = true;
    private boolean needsMeshing = true;
    private boolean isLoaded = false;
    private final UUID ID = UUID.randomUUID();

    {
        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int y = 0; y < ChunkPosition.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                    blockGrid[x][y][z] = new AirBlock(new BlockPosition(x, y, z));
                }
            }
        }
    }

    protected Chunk(ChunkPosition position, WorldChunkManager chunkManager) {
        this.position = position;
        this.chunkManager = chunkManager;

        chunkManager.cache(this);
    }

    public void render() {

//        if (this.needsMeshing) {
//            mesh = WorldChunkManager.CHUNK_MESHER.meshChunk(this);
//            this.needsMeshing = false;
//        }
//
//        RenderSystem.render();

        List<Block> blocks = new ArrayList<>();

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int y = 0; y < ChunkPosition.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                    Block block = blockGrid[x][y][z];
                    blocks.add(block);
                }
            }
        }

        RenderSystem.renderBatched(blocks);
    }

    public ChunkPosition getChunkPosition() {
        return position;
    }

    public Block getBlock(BlockPosition position) {
        return this.blockGrid[position.getX()][position.getY()][position.getZ()];
    }

    public void setBlock(Block newBlock, BlockPosition blockPosition) {
        this.blockGrid[blockPosition.getX()][blockPosition.getY()][blockPosition.getZ()] = newBlock;
        this.setNeedsMeshing();
        this.setNeedsSaving();
    }

    public int getBlockCount() {
        int count = 0;

        for (int x = 0; x < ChunkPosition.CHUNK_WIDTH; x++) {
            for (int y = 0; y < ChunkPosition.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < ChunkPosition.CHUNK_WIDTH; z++) {
                    Block block = blockGrid[x][y][z];

                    if (block != null) {
                        count = count + 1;
                    }
                }
            }
        }

        return count;
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
