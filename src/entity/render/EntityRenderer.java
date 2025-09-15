package entity.render;

import entity.Entity;
import entity.model.EntityModel;
import entity.model.EntityRenderQueue;
import world.World;

public abstract class EntityRenderer<T extends Entity> {
    protected final EntityModel<T> model;

    protected EntityRenderer(EntityModel<T> model) {
        this.model = model;
    }

    public EntityModel<T> getModel() {
        return model;
    }

    public void render(T entity, EntityRenderQueue queue) {
        queue.submit(entity);
    }
}