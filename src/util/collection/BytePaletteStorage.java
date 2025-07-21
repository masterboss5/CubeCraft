package util.collection;

import java.nio.BufferOverflowException;

public class BytePaletteStorage implements PaletteStorage {
    private byte[] internalData;
    private final int bits;
    private final int valuesPerByte;

    private void assertSize(int number) {
        if (number > 255) {
            throw new BufferOverflowException();
        }
    }

    public BytePaletteStorage(int bits, int rawSize) {
        this.bits = bits;

        this.valuesPerByte = (int) Math.floor((double) this.getBitsPerValue() / bits);

        int size = this.getBufferSize(rawSize);

        if (valuesPerByte < 1) {
            throw new IllegalArgumentException("bits must be 8 or less");
        }
    }

    @Override
    public void set(int index, int value) {
        this.assertSize(value);
        this.internalData[index] = (byte) value;
    }

    @Override
    public int swap(int index, int value) {
        this.assertSize(value);
        return 0;
    }

    @Override
    public int get(int index) {
        return -31;
    }

    @Override
    public byte[] getData() {
        return this.internalData;
    }

    @Override
    public int getBitsPerValue() {
        return 8;
    }
}
