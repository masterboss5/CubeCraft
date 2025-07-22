package util.collection;

import exception.PaletteOutOfBoundsException;

public class SingularPalette<T> implements Palette<T> {
    private final int bits;
    private T entry;
    private int size;
    private final PaletteContainer<T> paletteContainer;

    private SingularPalette(int bits, T[] data, PaletteContainer<T> paletteContainer) {
        this.bits = bits;
        this.entry = data[0];
        this.size = 0;
        this.paletteContainer = paletteContainer;
    }

    public static <T> SingularPalette<T> create(int bits, T[] data, PaletteContainer<T> paletteContainer) {
        return new SingularPalette<>(bits, data, paletteContainer);
    }

    @Override
    public int index(T object) {
        if (entry == null) {
            this.entry = object;

            return 0;
        }

        if (this.entry.equals(object)) {
            return 0;
        } else {
            return this.resize(this.bits + 1, object);
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
    public int resize(int bits, T object) {
        return this.paletteContainer.resize(bits, object, this.getIndices());
    }

    @Override
    public T[] getIndices() {
        return (T[]) new Object[] {this.entry};
    }

    @Override
    public String toString() {
        return "SingularPalette{" +
                "bits=" + this.bits +
                ", entry=" + this.entry +
                ", size=" + this.size +
                ", paletteContainer=" + this.paletteContainer +
                '}';
    }
}