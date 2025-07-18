package util.collection;

public interface PaletteFactory {
    <T> Palette<T> create(int bits);
}
