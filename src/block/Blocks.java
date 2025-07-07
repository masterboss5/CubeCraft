package block;

import registry.Registries;

import java.util.function.Function;

public class Blocks {
    public static final Function<BlockPosition, Block> AIR_BLOCK = Registries.BLOCK.register("air_block", AirBlock::new);
    public static final Function<BlockPosition, Block> DIRT_BLOCK = Registries.BLOCK.register("dirt_block", DirtBlock::new);

    private Blocks() {
        throw new UnsupportedOperationException();
    }

    public static void loadBlocks() {
    }
}
