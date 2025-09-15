package entity.render;

import entity.PlayerEntity;
import entity.model.EntityModels;
import entity.model.EntityRenderQueue;
import render.RenderSystem;

public class PlayerEntityRenderer extends EntityRenderer<PlayerEntity> {
    protected PlayerEntityRenderer() {
        super(EntityModels.PLAYER_ENTITY_MODEL);
    }

    @Override
    public void render(PlayerEntity entity, EntityRenderQueue queue) {
        super.render(entity, queue);
        RenderSystem.renderHitbox(entity.getBoundingBox(), entity);
    }
}
