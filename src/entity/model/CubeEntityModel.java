package entity.model;

import entity.CubeEntity;
import graphic.ModelPart;
import graphic.ModelPartTexture;
import texture.Texture;

public class CubeEntityModel extends EntityModel<CubeEntity> {
    @Override
    public void appendRootPart() {
        final ModelPartTexture TEST_TEXTURE = new ModelPartTexture(
                new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm_top.png"),
                new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm_bottom.png"),
                new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png"),
                new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png"),
                new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png"),
                new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png")
        );

        this.appendModelPart(new ModelPart(
                EntityPartName.ROOT,
                TEST_TEXTURE
        ).pivot(0, 0, 0).scale(1, 1.5f, 1));
    }

    public void appendModelParts() {
/*        this.appendModelPart(new ModelPart(EntityPartName.HEAD, new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\entity\\player\\player_arm.png")))
                .pivot(1, 0, 0));*/
    }
}
