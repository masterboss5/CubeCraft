package util.collection;

public class ArrayPalette<T> implements Palette<T> {
    private final int bits;
    private final T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    private ArrayPalette(int bits) {
        this.bits = bits;
        this.array = (T[]) new Object[1 << bits];
        this.size = 0;
    }

    @Override
    public int index(T object) {
        if (this.contains(object)) {
            for (int i = 0; i < this.array.length; i++) {
                if (this.array[i].equals(object)) {
                    return i;
                }
            }
        }
        //resize or register here
        return -1;
    }

    @Override
    public T get(int id) {
        return this.array[id];
    }

    @Override
    public boolean contains(T object) {
        for (T element : this.array) {
            if (element != null) {
                if (object.equals(element)) {
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
    public int add(T object) {
        if (!this.contains(object)) {
            for (int i = 0; i < this.array.length; i++) {
                if (this.array[i] == null) {
                    this.size = this.size + 1;
                    this.array[i] = object;

                    return i;
                }
            }
        }

        return this.index(object);
    }

    @Override
    public int resize(int bits, T object) {
        return 0; //TODO
    }
}
