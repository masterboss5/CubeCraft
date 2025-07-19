package util.collection;

public class ArrayPalette<T> implements Palette<T> {
    private final int bits;
    private final T[] array;
    private final PaletteContainer<T> paletteContainer;

    @SuppressWarnings("unchecked")
    private ArrayPalette(int bits, T[] data, PaletteContainer<T> paletteContainer) {
        this.bits = bits;
        this.array = (T[]) new Object[1 << bits];
        this.paletteContainer = paletteContainer;

        for (int i = 0; i < data.length; i++) {
            this.array[i] = data[i];
        }
    }

    public static <T> ArrayPalette<T> create(int bits, T[] data, PaletteContainer<T> paletteContainer) {
        return new ArrayPalette<>(bits, data, paletteContainer);
    }

    @Override
    public int index(T object) {
        if (this.contains(object)) {
            for (int i = 0; i < this.array.length; i++) {
                if (this.array[i].equals(object)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.array.length; i++) {
                if (this.array[i] == null) {
                    this.array[i] = object;

                    return i;
                }
            }
        }

        return this.resize(this.bits + 1, object);
    }

    @Override
    public T get(int id) {
        return this.array[id];
    }

    @Override
    public boolean contains(T object) {
        for (T t : this.array) {
            if (t != null) {
                if (t.equals(object)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        for (T element : this.array) {
            if (element != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int resize(int bits, T object) {
        return this.paletteContainer.resize(bits, object, this.getIndices());
    }

    @Override
    public T[] getIndices() {
        return this.array;
    }
}
