package util.collection;

public class PaletteContainer<T> {
    private static final PaletteFactory SINGULAR_PALETTE = SingularPalette::create;
    private static final PaletteFactory ARRAY_PALETTE = ArrayPalette::create;

    private Palette<T> palette;

    public PaletteContainer() {

    }

    private Palette<T> getCompatiblePalette(T[] array, int bits) {
        return switch (bits) {
            case 0 -> SingularPalette.create(bits);
            default -> ArrayPalette.create(bits);
        };
    }
}
