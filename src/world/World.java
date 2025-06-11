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
        blocks.put(new BlockPosition(0, 0, 0), new GrassBlock());
        blocks.put(new BlockPosition(0, 1, 0), new GrassBlock());
        blocks.put(new BlockPosition(0, 2, 0), new GrassBlock());
    }

    public void renderWorld() {
        blocks.forEach(((blockPosition, block) -> {
            block.setPosition(blockPosition);
            RenderSystem.render(block);
        }));
    }
}
