package block;

import graphic.BlockModel;
import graphic.Model;
import org.joml.Vector3f;
import render.Renderable;

public abstract class Block implements Renderable {
    BlockModel model;
    int x;
    int y;
    int z;

    public Block(BlockModel model, int x, int y, int z) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    protected Block(BlockModel model) {
        this.model = model;
    }

    @Override
    public Model getModel() {
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
