package registry;

public final class RegistryKey<T> {
    private final Registry<T> registry;
    private final ResourceLocation key;

    private RegistryKey(Registry<T> registry, ResourceLocation key) {
        this.registry = registry;
        this.key = key;
    }

    public Registry<T> getRegistry() {
        return registry;
    }

    public ResourceLocation getKey() {
        return key;
    }

    public static <T> RegistryKey<T> of(Registry<T> registry, ResourceLocation key) {
        return new RegistryKey<>(registry, key);
    }
}
