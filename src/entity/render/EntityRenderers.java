package entity.render;

import entity.CubeEntity;
import entity.Entity;
import entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityRenderers {
    public static final Map<EntityType<?>, EntityRenderFactory<?>> ENTITY_RENDER_FACTORIES = new HashMap<>();

    public static void loadEntityRenderers() {
        register(EntityType.CUBE_ENTITY, CubeEntityRenderer::new);
    }

    private static <T extends Entity> void register(EntityType<T> entityType, EntityRenderFactory<T> entityRendererFactory) {
        ENTITY_RENDER_FACTORIES.put(entityType, entityRendererFactory);
    }
}
