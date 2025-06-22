package registry;

import java.util.Collection;
import java.util.Set;

public interface Registry<ENTRY> {
    ENTRY get(ResourceLocation key);

    ENTRY register(ResourceLocation key, ENTRY entry);

    Collection<ENTRY> values();

    Set<ResourceLocation> keys();

    boolean contains(ResourceLocation key);
}