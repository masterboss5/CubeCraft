package entity.model;

import entity.CubeEntity;
import entity.Entity;
import graphic.ModelPart;

public class CubeEntityModel extends EntityModel<CubeEntity> {
    @Override
    public void appendRootPart() {
        this.appendModelPart(new ModelPart(EntityPartNames.ROOT)
                .move(0, 0, 0));
    }

    @Override
    public void appendModelParts() {
    }
}
