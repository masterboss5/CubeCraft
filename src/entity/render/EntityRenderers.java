package entity.render;

import entity.Entity;
import entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityRenderers {
    public static final Map<EntityType<?>, Supplier<EntityRenderer<?>>> ENTITY_RENDER_FACTORIES = new HashMap<>();

    public static void registerEntityRenderers() {

    }

    static {

    }

    private static void register(EntityType<?> entityType, Supplier<EntityRenderer<?>> entityRendererSupplier) {
        ENTITY_RENDER_FACTORIES.put(entityType, entityRendererSupplier);
    }
}
