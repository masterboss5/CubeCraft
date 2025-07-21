package util.collection;

public class PaletteContainer<T> {
    private static final PaletteFactory SINGULAR_PALETTE = SingularPalette::create;
    private static final PaletteFactory ARRAY_PALETTE = ArrayPalette::create;
    public Palette<T> palette;
    private PaletteStorage storage;
    private int bits;
    private final int storageSize;

    public PaletteContainer(int storageSize) {
        this.palette = this.getCompatiblePalette(null, 0);
        this.bits = 0;
        this.storageSize = storageSize;
    }

    private Palette<T> getCompatiblePalette(T[] array, int bits) {
        if (array == null) {
            array = (T[]) new Object[1];
        }

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
        if (bits >= 0 && bits <= 8) {
            return new BytePaletteStorage(bits, this.storageSize);
        }

        if (bits > 8  && bits <= 16) {
            return new BytePaletteStorage(bits, this.storageSize);
        }

        //temp
        return new BytePaletteStorage(bits, this.storageSize);
    }
}
