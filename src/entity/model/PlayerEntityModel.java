package entity.model;

import entity.PlayerEntity;
import graphic.ModelPart;
import graphic.ModelPartTexture;
import texture.Texture;

public class PlayerEntityModel extends EntityModel<PlayerEntity> {
    @Override
    public void appendRootPart() {
        this.appendModelPart(new ModelPart(EntityPartName.ROOT,
                new ModelPartTexture(
                        new Texture("resources\\textures\\entity\\player\\player_root_top.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_bottom.png"),
                        new Texture("resources\\textures\\entity\\player\\player_root_front.png"),
                        new Texture("resources\\textures\\entity\\player\\player_root_front.png"),
                        new Texture("resources\\textures\\entity\\player\\player_root_front.png"),
                        new Texture("resources\\textures\\entity\\player\\player_root_front.png")
                )
        ).pivot(0, 0, 0).scale(0.6f, 0.8f, 0.25f));
    }

    @Override
    public void appendModelParts() {
        final ModelPartTexture ARM_TEXTURE = new ModelPartTexture(
                new Texture("resources\\textures\\entity\\player\\player_arm_top.png"),
                new Texture("resources\\textures\\entity\\player\\player_arm_bottom.png"),
                new Texture("resources\\textures\\entity\\player\\player_arm.png"),
                new Texture("resources\\textures\\entity\\player\\player_arm.png"),
                new Texture("resources\\textures\\entity\\player\\player_arm.png"),
                new Texture("resources\\textures\\entity\\player\\player_arm.png")
        );

        this.appendModelPart(new ModelPart(
                EntityPartName.HEAD,
                new ModelPartTexture(
                        new Texture("resources\\textures\\entity\\player\\player_head_top.png"),
                        new Texture("resources\\textures\\entity\\player\\player_head_bottom.png"),
                        new Texture("resources\\textures\\entity\\player\\player_head_front.png"),
                        new Texture("resources\\textures\\entity\\player\\player_head_back.png"),
                        new Texture("resources\\textures\\entity\\player\\player_head_left.png"),
                        new Texture("resources\\textures\\entity\\player\\player_head_right.png")
                )
        ).pivot(0, 0.65f, 0).scale(0.55f, 0.55f, 0.55f).rotate(0, 0, 0));

        this.appendModelPart(new ModelPart(
                EntityPartName.LEFT_ARM,
                ARM_TEXTURE
        ).pivot(0.425f, 0.0f, 0).scale(0.25f, 0.8f, 0.25f));

        this.appendModelPart(new ModelPart(
                EntityPartName.RIGHT_ARM,
                ARM_TEXTURE
        ).pivot(-0.425f, 0.0f, 0).scale(0.25f, 0.8f, 0.25f));

        this.appendModelPart(new ModelPart(
                EntityPartName.LEFT_LEG,
                new ModelPartTexture(
                        new Texture("resources\\textures\\entity\\player\\player_leg_bottom.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_bottom.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_front.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_back.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_left.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_left.png")
                )
        ).pivot(0.15f, -0.8f, 0).scale(0.3f, 0.8f, 0.25f));

        this.appendModelPart(new ModelPart(
                EntityPartName.RIGHT_LEG,
                new ModelPartTexture(
                        new Texture("resources\\textures\\entity\\player\\player_leg_bottom.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_bottom.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_front.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_back.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_right.png"),
                        new Texture("resources\\textures\\entity\\player\\player_leg_right.png")
                )
        ).pivot(-0.15f, -0.8f, 0).scale(0.3f, 0.8f, 0.25f));
    }
}
