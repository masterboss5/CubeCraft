package entity.render;

import entity.model.RenderQueue;
import render.RenderSystem;
import world.Chunk;

public class ChunkRenderQueue extends RenderQueue<Chunk> {
    @Override
    public void renderAll() {
        for (Chunk chunk : this.queue) {
            RenderSystem.renderChunk(chunk.getMesh(), chunk);
        }

        this.queue.clear();
    }
}
