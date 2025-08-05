package entity.render;

import entity.Entity;
import entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public final class EntityRenderDispatch {
    private static final Map<EntityType<?>, EntityRenderer<?>> ENTITY_RENDERERS = new HashMap<>();

    private static <T extends Entity> EntityRenderer<T> getRenderer(EntityType<T> type) {
        return (EntityRenderer<T>) ENTITY_RENDERERS.get(type);
    }

    public <T extends Entity> void render(T entity) {
        getRenderer(entity.getType());
    }

    public void reload() {
        EntityRenderers.ENTITY_RENDER_FACTORIES.forEach(((entityType, entityRendererSupplier) -> {
            ENTITY_RENDERERS.put(entityType, entityRendererSupplier.create());
        }));
    }
}
