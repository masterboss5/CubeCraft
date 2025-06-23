package registry;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class DefaultedRegistry<ENTRY> implements Registry<ENTRY> {
    private final Map<String, ENTRY> entries = new HashMap<>();
    private final Function<String, ENTRY> defaultFunction;

    protected DefaultedRegistry(Supplier<ENTRY> defaultSupplier) {
        this.defaultFunction = (key) -> defaultSupplier.get();
    }

    @Override
    public ENTRY get(String key) {
        return this.entries.computeIfAbsent(key, this.defaultFunction);
    }

    @Override
    public ENTRY get(RegistryKey<ENTRY> registryKey) {
        return this.get(registryKey.getKey());
    }

    public ENTRY getDefault() {
        return this.defaultFunction.apply("");
    }

    @Override
    public ENTRY register(String key, ENTRY entry) {
        this.entries.put(key, entry);

        return entry;
    }

    @Override
    public ENTRY register(RegistryKey<ENTRY> registryKey, ENTRY entry) {
        return this.register(registryKey.getKey(), entry);
    }

    @Override
    public Collection<ENTRY> values() {
        return Collections.unmodifiableCollection(entries.values());
    }

    @Override
    public Set<String> keys() {
        return Collections.unmodifiableSet(entries.keySet());
    }

    @Override
    public boolean contains(String key) {
        return this.entries.containsKey(key);
    }
}
