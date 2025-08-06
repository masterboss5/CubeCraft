package entity.render;

import entity.CubeEntity;
import entity.Entity;
import entity.model.CubeEntityModel;

public class CubeEntityRenderer extends EntityRenderer<CubeEntity> {
    protected CubeEntityRenderer() {
        super(new CubeEntityModel());
    }

    @Override
    public void render(CubeEntity entity) {
    }
}
