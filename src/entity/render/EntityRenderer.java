package entity.render;

import entity.Entity;
import entity.model.EntityModel;

public abstract class EntityRenderer<T extends Entity> {
    protected final EntityModel<T> model;

    protected EntityRenderer(EntityModel<T> model) {
        this.model = model;
    }

    public EntityModel<T> getModel() {
        return model;
    }

    public void render(T entity) {
    }
}