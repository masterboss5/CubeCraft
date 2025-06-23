package registry;

import java.util.Collection;
import java.util.Set;

public interface Registry<ENTRY> {
    ENTRY get(String key);

    ENTRY get(RegistryKey<ENTRY> registryKey);

    ENTRY register(String key, ENTRY entry);

    ENTRY register(RegistryKey<ENTRY> registryKey, ENTRY entry);

    Collection<ENTRY> values();

    Set<String> keys();

    boolean contains(String key);

    void freeze();
}