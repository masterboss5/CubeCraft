package world;

import block.AirBlock;
import block.Block;
import block.BlockPosition;
import block.GrassBlock;
import render.RenderSystem;

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

        if (this.isNeedsMeshing()) {
            if (this.getChunkPosition().equals(new ChunkPosition(0, 0))) {
//                this.setBlock(new AirBlock(new BlockPosition(0, 4, 0)), new BlockPosition(0, 4, 0));
            }

            for (int x = 0; x < 4; x++) {
                for (int z = 0; z < 4; z++) {
                    this.setBlock(new AirBlock(new BlockPosition(4 + x, 4, 4 + z)), new BlockPosition(4 + x, 4, 4 + z));
                }
            }

            this.setBlock(new AirBlock(new BlockPosition(0, 4, 0)), new BlockPosition(0, 4, 0));


            mesh = WorldChunkManager.CHUNK_MESHER.meshChunk(this);
            this.needsMeshing = false;
        }

        RenderSystem.renderChunk(this.mesh, this);
    }

    public ChunkPosition getChunkPosition() {
        return position;
    }

    public Block getBlock(BlockPosition position) {
        return this.blockGrid[position.getX()][position.getY()][position.getZ()];
    }

    public Block safeGetBlock(BlockPosition position) {
        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        if (x < 0 || x >= ChunkPosition.CHUNK_WIDTH ||
                y < 0 || y >= ChunkPosition.CHUNK_HEIGHT ||
                z < 0 || z >= ChunkPosition.CHUNK_WIDTH) {
            return new AirBlock(position); // or null, depending on your preference
        }

        return blockGrid[x][y][z];
    }

    public void setBlock(Block newBlock, BlockPosition blockPosition) {
        this.blockGrid[blockPosition.getX()][blockPosition.getY()][blockPosition.getZ()] = newBlock;
        this.setNeedsMeshing();
        this.setNeedsSaving();
    }
    //TODO fix method
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
