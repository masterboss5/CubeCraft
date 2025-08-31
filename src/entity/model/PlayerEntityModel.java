package entity.model;

import entity.PlayerEntity;
import graphic.ModelPart;
import graphic.ModelPartTexture;
import texture.Texture;

public class PlayerEntityModel extends EntityModel<PlayerEntity> {
    @Override
    public void appendRootPart() {
        this.appendModelPart(new ModelPart(EntityPartName.ROOT, new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cow.png")))
                .pivot(0, 0, 0).scale(0.6f, 0.8f, 0.35f));
    }

    @Override
    public void appendModelParts() {
        this.appendModelPart(new ModelPart(
                EntityPartName.HEAD,
                new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cow.png"))
        ).pivot(0, 0.65f, 0).scale(0.55f, 0.55f, 0.55f));

        this.appendModelPart(new ModelPart(
                EntityPartName.LEFT_ARM,
                new ModelPartTexture(new Texture("resources\\textures\\entity\\player\\player_arm.png"))
        ).pivot(-0.425f, 0.0f, 0).scale(0.25f, 0.8f, 0.35f));

        this.appendModelPart(new ModelPart(
                EntityPartName.RIGHT_ARM,
                new ModelPartTexture(new Texture("resources\\textures\\entity\\player\\player_arm.png"))
        ).pivot(0.425f, 0.0f, 0).scale(0.25f, 0.8f, 0.35f));

        this.appendModelPart(new ModelPart(
                EntityPartName.LEFT_LEG,
                new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cow.png"))
        ).pivot(-0.15f, -0.8f, 0).scale(0.3f, 0.8f, 0.35f));

        this.appendModelPart(new ModelPart(
                EntityPartName.RIGHT_LEG,
                new ModelPartTexture(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cow.png"))
        ).pivot(0.15f, -0.8f, 0).scale(0.3f, 0.8f, 0.35f));
    }

}
