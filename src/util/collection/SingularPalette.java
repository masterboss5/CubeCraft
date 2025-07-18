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

    @Override  //TODO add resize
    public int index(T block) {
        if (entry == null) {
            this.entry = block;

            return 0;
        }

        if (this.entry.equals(block)) {
            return 0;
        } else {
            return -1;
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

    @Override //TODO add resize
    public int add(T object) {

        if (this.entry == null) {
            this.entry = object;
            this.size = 1;

            return 0;
        } else {
            System.out.println("singular palette needs resize");
            //TODO add resize logic later
        }

        return 0;
    }

    @Override
    public int resize(int bits, T object) {
        return 0; //TODO
    }

    @Override
    public T[] getIndices() {
        return (T[]) new Object[] {this.entry};
    }
}