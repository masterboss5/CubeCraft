package entity.model;

import entity.Entity;
import graphic.ModelPartInstance;
import render.RenderSystem;

import java.util.ArrayList;
import java.util.List;

public class EntityRenderQueue extends RenderQueue<Entity> {
    private final List<ModelPartInstance> partInstances = new ArrayList<>(24);

    @Override
    public void renderAll() {
        for (Entity entity : this.queue) {
            EntityModel<?> model = entity.getType().getModel();
            partInstances.addAll(model.getPartInstances(entity));
        }

        RenderSystem.renderEntityInstanced(partInstances);
        this.partInstances.clear();
        this.queue.clear();
    }
}
