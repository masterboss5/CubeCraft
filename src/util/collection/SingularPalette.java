package util.collection;

import exception.PaletteOutOfBoundsException;

public class SingularPalette<T> implements Palette<T> {
    private T entry;

    public SingularPalette() {
        this.entry = null;
    }

    @Override
    public int index(T block) {
        if (entry == null) {
            this.entry = block;

            return 0;
        }

        if (this.entry.equals(block)) {
            return 0;
        } else {
            return -1; //TODO add resize
        }
    }

    @Override
    public T get(int id) {
        if (id == 0) {
            return this.entry;
        } else {
            throw new PaletteOutOfBoundsException(id, "singular palette");
        }
    }

    @Override
    public boolean contains(T object) {
        if (this.entry != null) {
            if (this.entry.equals(object)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return entry == null;
    }

    @Override
    public int add(T object) {
        //TODO resize
        return 0;
    }
}