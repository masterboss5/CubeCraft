package util.collection;

import java.nio.BufferOverflowException;

public class BytePaletteStorage implements PaletteStorage {
    private byte[] internalData;
    private final int bits;
    private final int valuesPerByte;
    private final int bufferSize;

    private void assertSize(int number) {
        if (number > 255) {
            throw new BufferOverflowException();
        }
    }

    public BytePaletteStorage(int bits, int rawSize) {
        this.bits = bits;
        this.valuesPerByte = (int) Math.floor((double) this.getMaxBitsPerValue() / bits);
        this.bufferSize = this.getBufferSize(rawSize);

        System.out.println(valuesPerByte);

        if (valuesPerByte < 1 || bits > this.getMaxBitsPerValue()) {
            throw new IllegalArgumentException("bits must be 8 or less");
        }

        this.internalData = new byte[this.bufferSize];
    }

    @Override
    public void set(int index, int value) {
        this.assertSize(value);
        this.internalData[index] = (byte) value;
    }

    @Override
    public int swap(int index, int value) {
        this.assertSize(value);
        int temp = this.get(index);
        this.set(index, value);

        return temp;
    }

    @Override
    public int get(int index) {
        return this.internalData[index];
    }

    @Override
    public byte[] getData() {
        return this.internalData;
    }

    @Override
    public int getMaxBitsPerValue() {
        return 8;
    }
}
