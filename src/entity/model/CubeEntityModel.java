package entity.model;

import entity.CubeEntity;
import graphic.ModelPart;
import graphic.ModelPartTexture;
import texture.Texture;

public class CubeEntityModel extends EntityModel<CubeEntity> {
    @Override
    public void appendRootPart() {
        this.appendModelPart(new ModelPart(EntityPartName.ROOT, new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png")))
                .pivot(0, 0, 0).scale(1, 1.5f, 1));
    }

    public void appendModelParts() {
        this.appendModelPart(new ModelPart(EntityPartName.HEAD, new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png")))
                .pivot(1, 0, 0));
    }
}
