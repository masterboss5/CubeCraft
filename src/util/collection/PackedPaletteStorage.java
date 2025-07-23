package util.collection;

import java.nio.BufferOverflowException;

public class PackedPaletteStorage implements PaletteStorage {
    private long[] internalData;
    private final int bits;
    private final int size;
    private final int valuesPerLong;
    private final int internalSize;

    public PackedPaletteStorage(int bits, int size) {
        this.bits = bits;
        this.size = size;
        this.valuesPerLong = (int) Math.floor((double) this.getMaxBitWidth() / bits);
        this.internalSize = this.getTotalBufferSize(size);

        System.out.println(valuesPerLong);

        if (valuesPerLong < 1 || bits > this.getMaxBitWidth()) {
            throw new IllegalArgumentException("bits must be 64 or less");
        }

        this.internalData = new long[this.internalSize];
    }

    @Override  //TODO
    public void set(int index, int value) {
/*        this.assertSize(value);
        this.internalData[index] = (byte) value;*/
    }

    @Override //TODO
    public int swap(int index, int value) {
/*        this.assertSize(value);
        int temp = this.get(index);
        this.set(index, value);

        return temp;*/

        return 0;
    }

    @Override   //TODO
    public int get(int index) {
//        return this.internalData[index];
        return 0;
    }

    @Override
    public int[] getData() {
        return new int[] {404};
    }

    @Override
    public int getMaxBitWidth() {
        return Long.SIZE;
    }
}
