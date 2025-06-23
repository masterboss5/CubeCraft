package registry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class RootRegistry implements Iterable<Registry<?>> {
    public final Map<String, Registry<?>> entries = new HashMap<>();

    protected RootRegistry() {
    }

    public <T extends Registry<?>> T register(T registry, String name) {
        this.entries.put(name, registry);

        return registry;
    }

    public Registry<?> get(String name) {
        return this.entries.get(name);
    }

    public Set<String> keys() {
        return this.entries.keySet();
    }

    public boolean containsKey(String key) {
        return this.entries.containsKey(key);
    }

    @Override
    public Iterator<Registry<?>> iterator() {
        return this.entries.values().iterator();
    }
}
