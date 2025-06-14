package block;

import graphic.BlockModel;
import graphic.Texture;
import org.joml.Vector3f;
import render.Renderable;

public abstract class Block implements Renderable {
    final Texture texture;
    BlockModel model;
    int x;
    int y;
    int z;

    protected Block(BlockModel model) {
        this.model = model;
        this.texture = model.getTexture();
    }

    @Override
    public BlockModel getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return new Vector3f(this.x, this.y, this.z);
    }

    public void setPosition(BlockPosition blockPosition) {
        this.x = blockPosition.getX();
        this.y = blockPosition.getY();
        this.z = blockPosition.getZ();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
