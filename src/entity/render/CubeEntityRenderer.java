package entity.render;

import entity.CubeEntity;
import entity.model.EntityModels;

public class CubeEntityRenderer extends EntityRenderer<CubeEntity> {
    protected CubeEntityRenderer() {
        super(EntityModels.CUBE_ENTITY_MODEL);
    }
}
