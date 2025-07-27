package block;

import graphic.BlockModel;
import render.Renderable;

import java.util.Objects;

public abstract class Block implements Renderable {
    final BlockModel model;

    protected Block(BlockModel model) {
        this.model = model;
    }

    @Override
    public BlockModel getModel() {
        return model;
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
