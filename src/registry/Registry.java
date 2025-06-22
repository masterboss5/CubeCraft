package registry;

import java.util.Collection;
import java.util.Set;

public interface Registry<ENTRY> {
    ENTRY get(ResourceLocation key);

    ENTRY get(RegistryKey<ENTRY> registryKey);

    ENTRY register(ResourceLocation key, ENTRY entry);

    ENTRY register(RegistryKey<ENTRY> registryKey, ENTRY entry);

    Collection<ENTRY> values();

    Set<ResourceLocation> keys();

    boolean contains(ResourceLocation key);
}