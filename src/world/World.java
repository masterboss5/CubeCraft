package world;

import block.Block;

import java.util.ArrayList;

public class World {
    ArrayList<Block> blocks = new ArrayList<>();
    WorldChunkManager chunkManager = new WorldChunkManager(this);

    public void loadWorld() {
        this.generateWorld();
    }

    public void generateWorld() {
        this.chunkManager.generateChunks(25);
    }

    public void tickWorld() {
    }

    public void renderWorld() {
        for (Chunk visibleChunk : this.chunkManager.getVisibleChunks()) {
            visibleChunk.render();
        }
    }
}
