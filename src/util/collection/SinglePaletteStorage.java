package util.collection;

import java.util.Arrays;

public class SinglePaletteStorage implements PaletteStorage {
    private int constantValue;
    private final int bits;
    private final int size;
    private final int internalSize;

    public SinglePaletteStorage(int bits, int size) {
        this.bits = bits;
        this.size = size;
        this.internalSize = size;
    }

    @Override
    public void set(int index, int value) {
        this.constantValue = value;
    }

    @Override
    public int swap(int index, int value) {
        int temp = this.constantValue;
        this.set(0, value);

        return temp;
    }

    @Override
    public int get(int index) {
        return this.constantValue;
    }

    @Override
    public int[] getUnpackedData() {
        int[] buffer = new int[this.size];
        Arrays.fill(buffer, this.constantValue);

        return buffer;
    }

    @Override
    public int getMaxBitWidth() {
        return 0;
    }

    @Override
    public String toString() {
        return "SinglePaletteStorage{" +
                "constantValue=" + this.constantValue +
                ", bits=" + this.bits +
                ", size=" + this.size +
                ", internalSize=" + this.internalSize +
                '}';
    }
}
