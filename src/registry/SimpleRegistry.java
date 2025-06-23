package registry;

import java.util.*;

public class SimpleRegistry<ENTRY> implements Registry<ENTRY> {
    private boolean frozen = false;
    private final Map<String, ENTRY> entries = new HashMap<>();

    @Override
    public ENTRY get(String key) {
        return this.entries.get(key);
    }

    @Override
    public ENTRY get(RegistryKey<ENTRY> registryKey) {
        return this.get(registryKey.getKey());
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

    @Override
    public void freeze() {
        this.frozen = true;
    }
}