package entity.model;

import entity.CubeEntity;
import graphic.ModelPart;
import graphic.ModelPartTextureData;
import texture.Texture;

public class CubeEntityModel extends EntityModel<CubeEntity> {
    @Override
    public void appendRootPart() {
        this.appendModelPart(new ModelPart(EntityPartName.ROOT, new ModelPartTextureData(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cow.png")))
                .pivot(0, 2.5f, 0).size(1, 0.5f, 0));
    }

    public void appendModelParts() {
        this.appendModelPart(new ModelPart(EntityPartName.HEAD, new ModelPartTextureData(new Texture("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\cow.png")))
                .pivot(1, 0.5f, 0));
    }
}
