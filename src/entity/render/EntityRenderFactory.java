package entity.render;

import entity.Entity;

public interface EntityRenderFactory<T extends Entity> {
    EntityRenderer<T> create();
}
