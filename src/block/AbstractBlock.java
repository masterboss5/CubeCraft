package block;

import graphic.BlockModel;
import graphic.Model;
import render.Renderable;

public abstract class AbstractBlock implements Renderable {
    BlockModel model;

    protected AbstractBlock(BlockModel model) {
        this.model = model;
    }

    @Override
    public Model getModel() {
        return model;
    }
}
