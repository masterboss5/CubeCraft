package block;

import graphic.Models;

public class AirBlock extends Block {
    public AirBlock() {
        super(Models.AIR_BLOCK_MODEL);
    }

    @Override
    public boolean isAirBlock() {
        return true;
    }
}
