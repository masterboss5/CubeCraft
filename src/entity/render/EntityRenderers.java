package entity.render;

import entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class EntityRenderers {
    Map<EntityType<?>, EntityRenderer<?>> entityRenderers = new HashMap<>();
}
