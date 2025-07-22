package util.collection;

public interface PaletteStorage {
    void set(int index, int value);

    int swap(int index, int value);

    int get(int index);

    int[] getData();

    int getMaxBitWidth();

    default int getTotalBufferSize(int totalBits) {
        return (int) Math.ceil((double) totalBits / this.getMaxBitWidth());
    }
}
