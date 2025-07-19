package block;

import graphic.BlockModel;
import render.Renderable;

import java.util.Objects;

public abstract class Block implements Renderable {
    final BlockModel model;
    private BlockPosition position;

    protected Block(BlockModel model, BlockPosition position) {
        this.model = model;
        this.position = position;
    }

    @Override
    public BlockModel getModel() {
        return model;
    }

    public BlockPosition getPosition() {
        return position;
    }

    public void setPosition(BlockPosition position) {
        this.position = position;
    }

    public int getX() {
        return this.position.getX();
    }

    public void setX(int x) {
        this.position = this.position.setX(x);
    }

    public int getY() {
        return this.position.getY();
    }

    public void setY(int y) {
        this.position = this.position.setY(y);
    }

    public int getZ() {
        return this.position.getZ();
    }

    public void setZ(int z) {
        this.position = this.position.setZ(z);
    }

    public boolean isAirBlock() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(getModel(), block.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel());
    }
}
