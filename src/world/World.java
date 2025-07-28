package world;

import block.Block;
import block.BlockPosition;

import java.util.ArrayList;

public class World {
    ArrayList<Block> blocks = new ArrayList<>();
    WorldChunkManager chunkManager = new WorldChunkManager(this);

    public World() {
    }

    public void setBlock(BlockPosition position, Block block) {
        Chunk chunk = this.getChunk(position.toChunkPosition());
        chunk.setBlock(block, position);
    }

    public Block getBlock(BlockPosition position) {
        return this.getChunk(position.toChunkPosition()).getBlock(position);
    }

    public Chunk getChunk(ChunkPosition chunkPosition) {
        return this.chunkManager.getCache().stream()
                .filter(chunk -> chunk.getChunkPosition().equals(chunkPosition))
                .findFirst()
                .orElse(null);
    }

    public void loadWorld() {
        this.generateWorld();
    }

    public void generateWorld() {
        this.chunkManager.generateChunks(50);
    }

    public void tickWorld() {
    }

    public void renderWorld() {
        for (Chunk visibleChunk : this.chunkManager.getVisibleChunks()) {
            visibleChunk.render();
        }
    }
}
