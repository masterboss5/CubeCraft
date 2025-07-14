package world;

import block.Block;

public interface BlockStorage<T> {
    int index(Block block);

    T get(int x, int y, int z);

    void set(Block block, int x, int y, int z);

    boolean contains(int x, int y, int z);

    boolean isEmpty();
}
