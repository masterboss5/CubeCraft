package util.collection;

public interface PaletteStorage {
    void set(int index, int value);

    int swap(int index, int value);

    int get(int index);

    byte[] getData();

    int getBitsPerValue();

    default int getBufferSize(int totalBits) {
        return (int) Math.ceil((double) totalBits / this.getBitsPerValue());
    }
}
