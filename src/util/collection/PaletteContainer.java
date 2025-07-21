package util.collection;

public class PaletteContainer<T> {
    private static final PaletteFactory SINGULAR_PALETTE = SingularPalette::create;
    private static final PaletteFactory ARRAY_PALETTE = ArrayPalette::create;
    public Palette<T> palette;

    public PaletteContainer() {
        this.palette = this.getCompatiblePalette(null, 0);

//        System.out.println(this.palette);
    }

    private Palette<T> getCompatiblePalette(T[] array, int bits) {
        if (array == null) {
            array = (T[]) new Object[1];
        }

        return switch (bits) {
            case 0 -> SINGULAR_PALETTE.create(0, array, this);
            default -> ARRAY_PALETTE.create(bits, array, this);
        };
    }

    int resize(int bits, T object, T[] oldData) {
        System.out.println("resize_bits{" + (bits) + "}");
        Palette<T> newPalette = this.getCompatiblePalette(oldData, bits);
        this.palette = newPalette;
        return newPalette.index(object);
    }
}
