package entity.render;

import entity.Entity;
import entity.model.EntityModel;

public abstract class EntityRenderer<T extends Entity> {
    private final EntityModel<T> model;

    protected EntityRenderer(EntityModel<T> model) {
        this.model = model;
    }

    public abstract void render(T entity);
}
