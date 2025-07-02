package block;

import graphic.Models;

public class AirBlock extends Block {
    public AirBlock(BlockPosition position) {
        super(Models.AIR_BLOCK_MODEL, position);
    }

    @Override
    public boolean isAirBlock() {
        return true;
    }
}
