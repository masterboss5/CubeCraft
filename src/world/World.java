package world;

import block.Block;
import block.GrassBlock;
import render.RenderSystem;

import java.util.ArrayList;

public class World {
    ArrayList<Block> blocks = new ArrayList<>();

    public void loadWorld() {
        for (int x = 0; x < 10; x++) {
            for (int z = 0; z < 100; z++) {
                for (int y = 0; y < 100; y++) {
                    blocks.add(new GrassBlock(x, y ,z));
                }
            }
        }
    }

    public void tickWorld() {
    }

    public void renderWorld() {
//        blocks.forEach((RenderSystem::render));
        RenderSystem.renderBatched(blocks);
    }
}
