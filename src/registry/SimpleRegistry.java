package registry;

import java.util.*;

public class SimpleRegistry<ENTRY> implements Registry<ENTRY> {
    private final Map<ResourceLocation, ENTRY> entries = new HashMap<>();

    @Override
    public ENTRY get(ResourceLocation key) {
        return this.entries.get(key);
    }

    @Override
    public ENTRY register(ResourceLocation key, ENTRY entry) {
        this.entries.put(key, entry);
        return entry;
    }

    @Override
    public Collection<ENTRY> values() {
        return Collections.unmodifiableCollection(entries.values());
    }

    @Override
    public Set<ResourceLocation> keys() {
        return Collections.unmodifiableSet(entries.keySet());
    }

    @Override
    public boolean contains(ResourceLocation key) {
        return this.entries.containsKey(key);
    }
}