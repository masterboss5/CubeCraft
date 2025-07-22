package util.collection;

public interface PaletteStorage {
    void set(int index, int value);

    int swap(int index, int value);

    int get(int index);

    int[] getUnpackedData();

    int getMaxBitWidth();
}
