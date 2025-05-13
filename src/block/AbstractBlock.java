package block;

import graphic.BasicModel;

public abstract class AbstractBlock {
    BasicModel model;

    protected AbstractBlock(BasicModel model) {
        this.model = model;
    }
}
