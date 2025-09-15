package entity.render;

import entity.CubeEntity;
import entity.Entity;
import entity.EntityType;
import entity.model.EntityRenderQueue;

import java.util.HashMap;
import java.util.Map;

public final class EntityRenderDispatch {
    private static final Map<EntityType<?>, EntityRenderer<?>> ENTITY_RENDERERS = new HashMap<>();

    public static <T extends Entity> EntityRenderer<T> getRenderer(EntityType<T> type) {
        return (EntityRenderer<T>) ENTITY_RENDERERS.get(type);
    }

    public <T extends Entity> void render(T entity, EntityRenderQueue queue) {
        EntityRenderer<T> renderer = (EntityRenderer<T>) getRenderer(entity.getType());
        renderer.render(entity, queue);
    }

    public void reload() {
        EntityRenderers.ENTITY_RENDER_FACTORIES.forEach(((entityType, entityRendererFactory) -> {
            ENTITY_RENDERERS.put(entityType, entityRendererFactory.create());
        }));

        System.out.println(ENTITY_RENDERERS.values());
    }
}
