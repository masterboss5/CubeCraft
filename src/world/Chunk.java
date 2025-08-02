package world;

import block.AirBlock;
import block.Block;
import block.BlockPosition;
import block.Blocks;
import render.RenderSystem;
import util.collection.PaletteContainer;

import java.util.UUID;

public class Chunk {
    public static final int CHUNK_HEIGHT = 128;
    public static final int CHUNK_WIDTH = 16;
    private final WorldChunkManager chunkManager;
    private final PaletteContainer<Block> blockStorage = new PaletteContainer<>(CHUNK_WIDTH * CHUNK_WIDTH * CHUNK_HEIGHT);
    private final ChunkPosition position;
    private ChunkMesh mesh;
    private boolean needsSaving = true;
    private boolean needsMeshing = true;
    private boolean isLoaded = false;
    private final UUID ID = UUID.randomUUID();

    {
        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                for (int z = 0; z < CHUNK_WIDTH; z++) {
                    this.blockStorage.set(this.computePositionIndex(new BlockPosition(x, y, z)), Blocks.AIR_BLOCK);
                }
            }
        }
    }

    protected Chunk(ChunkPosition position, WorldChunkManager chunkManager) {
        this.position = position;
        this.chunkManager = chunkManager;

        chunkManager.cache(this);
    }

    public void unload() {

    }

    public void render() {
        if (this.isNeedsMeshing()) {
            for (int x = 0; x < 4; x++) {
                for (int z = 0; z < 4; z++) {
                    this.setBlock(Blocks.AIR_BLOCK, new BlockPosition(4 + x, 4, 4 + z));
                }
            }

            this.setBlock(Blocks.AIR_BLOCK, new BlockPosition(0, 4, 0));

            this.mesh = WorldChunkManager.CHUNK_MESHER.meshChunk(this);
            this.needsMeshing = false;
        }

        RenderSystem.renderChunk(this.mesh, this);
    }

    public ChunkPosition getChunkPosition() {
        return position;
    }

    public Block getBlock(BlockPosition position) {
        return this.blockStorage.get(this.computePositionIndex(position));
    }

    public Block safeGetBlock(BlockPosition position) {
        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        if (x < 0 || x >= CHUNK_WIDTH || y < 0 || y >= CHUNK_HEIGHT || z < 0 || z >= CHUNK_WIDTH) {
            return Blocks.AIR_BLOCK;
        }

        return this.getBlock(position);
    }

    public void setBlock(Block newBlock, BlockPosition position) {
        this.blockStorage.set(this.computePositionIndex(position), newBlock);
        this.setNeedsMeshing();
        this.setNeedsSaving();
    }

    public int getBlockCount() {
        int count = 0;

        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                for (int z = 0; z < CHUNK_WIDTH; z++) {
                    Block block = this.getBlock(new BlockPosition(x, y, z));

                    if (block != null) {
                        if (!block.isAirBlock()) {
                            count = count + 1;
                        }
                    }
                }
            }
        }

        return count;
    }

    public int computePositionIndex(BlockPosition position) {
        return position.getX() + CHUNK_WIDTH * (position.getY() + CHUNK_HEIGHT * position.getZ());
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
