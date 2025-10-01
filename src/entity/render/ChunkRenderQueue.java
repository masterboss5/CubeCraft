package entity.render;

import entity.model.RenderQueue;
import render.RenderSystem;
import world.Chunk;

public class ChunkRenderQueue extends RenderQueue<Chunk> {
    @Override
    public void renderAll() {
        RenderSystem.renderChunk(this.queue);
        this.queue.clear();
    }
}
