package registry;

public final class RegistryKey<T> {
    String key;
    T entry;

    private RegistryKey(String key, T entry) {
        this.key = key;
        this.entry = entry;
    }

    public String getKey() {
        return key;
    }

    public T getEntry() {
        return entry;
    }

    public static <T> RegistryKey<T> of(String key, Registry<T> registry) {
        return new RegistryKey<>(key, registry);
    }
}
