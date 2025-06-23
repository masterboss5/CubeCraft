package registry;

public final class RegistryKey<T> {
    private final Registry<T> registry;
    private final String key;

    private RegistryKey(Registry<T> registry, String key) {
        this.registry = registry;
        this.key = key;
    }

    public Registry<T> getRegistry() {
        return registry;
    }

    public String getKey() {
        return key;
    }

    public static <T> RegistryKey<T> of(Registry<T> registry, String key) {
        return new RegistryKey<>(registry, key);
    }
}
