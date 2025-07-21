package util.collection;

public class SinglePaletteStorage implements PaletteStorage {
    private final int constantValue = 0;

    public SinglePaletteStorage() {
    }

    @Override
    public void set(int index, int value) {
    }

    @Override
    public int swap(int index, int value) {
        return 0;
    }

    @Override
    public int get(int index) {
        return 0;
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public int getMaxBitsPerValue() {
        return 0;
    }
}
