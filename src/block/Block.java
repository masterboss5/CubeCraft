package block;

import graphic.BlockModel;
import render.Renderable;
import util.Box;
import util.Hitbox;

import java.util.Objects;

public abstract class Block implements Renderable {
    private final Hitbox hitbox = new Hitbox(new Box(1, 1, 1));
    private final BlockModel model;

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

    public int getIlluminance() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(this.getModel(), block.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getModel());
    }
}
