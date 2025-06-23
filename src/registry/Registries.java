package registry;

import block.Block;

public class Registries {
    private static boolean REGISTRIES_FROZEN = false;
    public static final Registry<Block> BLOCK = create("");

    public static <R, T extends R> R register(Registry<R> registry, String key, T entry) {
        return registry.register(key, entry);
    }

    public static <R, T extends R> R register(Registry<R> registry, RegistryKey<R> registryKey, T entry) {
        return register(registry, registryKey.getKey(), entry);
    }

    private static <R> Registry<R> create() {
        return new SimpleRegistry<R>();
    }

    public static void freezeRegistries() {
        REGISTRIES_FROZEN = true;

        for (Registry registry : )
    }
}
