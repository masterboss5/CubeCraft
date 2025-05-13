package block;

import graphic.BasicModel;
import render.Renderable;

public abstract class AbstractBlock implements Renderable {
    BasicModel model;

    protected AbstractBlock(BasicModel model) {
        this.model = model;
    }

    @Override
    public BasicModel getModel() {
        return model;
    }
}
