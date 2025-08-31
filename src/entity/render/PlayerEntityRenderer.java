package entity.render;

import entity.PlayerEntity;
import entity.model.EntityModel;
import entity.model.PlayerEntityModel;

public class PlayerEntityRenderer extends EntityRenderer<PlayerEntity> {
    protected PlayerEntityRenderer() {
        super(new PlayerEntityModel());
    }
}
