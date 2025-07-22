package util.collection;

public class PaletteContainer<T> {
    private static final PaletteFactory SINGULAR_PALETTE = SingularPalette::create;
    private static final PaletteFactory ARRAY_PALETTE = ArrayPalette::create;
    public Palette<T> palette;
    private PaletteStorage storage;
    private final int storageSize;
    private int bits;

    public PaletteContainer(int storageSize) {
        this.storageSize = storageSize;
        this.palette = this.getCompatiblePalette(null, 0);
        this.bits = 0;
    }

    public T get(int index) {
        return this.palette.get(this.storage.get(index));
    }

    public void set(int index, T value) {
        this.storage.set(index, this.palette.index(value));
    }

    private Palette<T> getCompatiblePalette(T[] array, int bits) {
        if (array == null) {
            array = (T[]) new Object[1];
        }

        this.storage = this.createPaletteStorage(bits);
        System.out.println(this.storage);

        this.bits = bits;
        return switch (bits) {
            case 0 -> SINGULAR_PALETTE.create(0, array, this);
            default -> ARRAY_PALETTE.create(bits, array, this);
        };
    }

    protected int resize(int bits, T object, T[] oldData) {
        System.out.println("resize_bits{" + (bits) + "}");
        Palette<T> newPalette = this.getCompatiblePalette(oldData, bits);
        this.palette = newPalette;

        return newPalette.index(object);
    }

    private PaletteStorage createPaletteStorage(int bits) {
        if (bits == 0) {
            return new SinglePaletteStorage(bits, this.storageSize);
        }

        if (bits > 0 && bits <= Long.SIZE) {
            throw new Error();

//            return new PackedPaletteStorage(bits, this.storageSize);
        }

/*        if (bits > 8  && bits <= 16) {
            return new PackedPaletteStorage(bits, this.storageSize);
        }*/

        throw new Error();
        //temp
//        return new PackedPaletteStorage(bits, this.storageSize);
    }

    public PaletteStorage getStorage() {
        return this.storage;
    }
}
