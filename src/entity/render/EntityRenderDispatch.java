package entity.render;

import entity.Entity;
import entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class EntityRenderDispatch {
    private static final Map<EntityType<?>, EntityRenderer<?>> ENTITY_RENDERERS = new HashMap<>();

    public <T extends Entity> void render(T entity) {

    }

    public void reload() {
        EntityRenderers.ENTITY_RENDER_FACTORIES.forEach(((entityType, entityRendererSupplier) -> {
            ENTITY_RENDERERS.put(entityType, entityRendererSupplier.get());
        }));
    }
}
