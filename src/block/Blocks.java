package block;

import registry.Registries;

public class Blocks {
    public static final Block AIR_BLOCK = Registries.register(Registries.BLOCK, "air_block", new AirBlock());

    public static final Block DIRT_BLOCK = Registries.register(Registries.BLOCK, "dirt_block", new DirtBlock());
    public static final Block GRASS_BLOCK = Registries.register(Registries.BLOCK, "grass_block", new GrassBlock());
    public static final Block COBBLESTONE_BLOCK = Registries.register(Registries.BLOCK, "cobblestone_block", new CobblestoneBlock());
    public static final Block OAK_PLANKS_BLOCK = Registries.register(Registries.BLOCK, "oak_planks_block", new OakPlanksBlock());

    private Blocks() {
        throw new UnsupportedOperationException();
    }

    public static void loadBlocks() {
    }
}
