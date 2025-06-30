package world;

import block.Block;
import block.GrassBlock;
import render.RenderSystem;

import java.util.ArrayList;

public class World {
    ArrayList<Block> blocks = new ArrayList<>();
    WorldChunkManager chunkManager = new WorldChunkManager(this);

    public void loadWorld() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    blocks.add(new GrassBlock(x, y ,z));
                }
            }
        }
    }

    public void generateWorld() {
        this.chunkManager.generateChunks();
    }

    public void tickWorld() {
    }

    public void renderWorld() {
//        blocks.forEach((RenderSystem::render));
        RenderSystem.renderBatched(blocks);
    }
}
