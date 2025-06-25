package world;

import block.Block;
import block.BlockPosition;
import block.GrassBlock;
import render.RenderSystem;

import java.util.ArrayList;

public class World {
    ArrayList<Block> blocks = new ArrayList<>();

    public void loadWorld() {
        for (int x = 0; x < 100; x++) {
            for (int z = 0; z < 10; z++) {
                for (int y = 0; y < 10; y++) {
                    blocks.add(new GrassBlock(x, y ,z));
                }
            }
        }
    }

    public void tickWorld() {
    }

    public void renderWorld() {
        blocks.forEach((RenderSystem::render));
    }
}
