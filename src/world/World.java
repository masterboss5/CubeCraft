package world;

import block.Block;
import block.BlockPosition;
import block.GrassBlock;
import render.RenderSystem;

import java.util.HashMap;
import java.util.Map;

public class World {
    Map<BlockPosition, Block> blocks = new HashMap<>();
    public void loadWorld() {
        for (int x = 0; x < 10; x++) {
            for (int z = 0; z < 10; z++) {
                for (int y = 0; y < 2; y++) {
                    blocks.put(new BlockPosition(x, y, z), new GrassBlock());
                }
            }
        }
    }

    public void tickWorld() {
    }

    public void renderWorld() {
        blocks.forEach(((blockPosition, block) -> {
            block.setPosition(blockPosition);
            RenderSystem.render(block);
        }));
    }
}
