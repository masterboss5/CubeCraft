package util.collection;

public class PackedIntegerArray implements PaletteStorage {
    private long[] internalData;
    private final int bits;
    private final int size;
    private final int valuesPerLong;
    private final int internalSize;

    public PackedIntegerArray(int bits, int[] unpackedData) {
        this(bits, unpackedData.length);

        for (int i = 0; i < unpackedData.length; i++) {
            this.set(i, unpackedData[i]);
        }
    }

    public PackedIntegerArray(int bits, int size) {
        this.bits = bits;
        this.size = size;
        this.valuesPerLong = (int) Math.floor((double) this.getMaxBitWidth() / bits);
        this.internalSize = (size + this.valuesPerLong - 1) / this.valuesPerLong;

/*        System.out.println("values per long" + this.valuesPerLong);
        System.out.println("total size" + this.internalSize);*/

        if (valuesPerLong < 1 || bits > this.getMaxBitWidth()) {
            throw new IllegalArgumentException("bits must be 64 or less");
        }

        this.internalData = new long[this.internalSize];
    }

    @Override
    public void set(int index, int value) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }

        long mask = (1L << this.bits) - 1;
        int longIndex = index / this.valuesPerLong;
        int bitOffset = (index % this.valuesPerLong) * this.bits;

        // Clear the bits at the offset
        this.internalData[longIndex] &= ~(mask << bitOffset);
        // Set the new value
        this.internalData[longIndex] |= ((long) value & mask) << bitOffset;
    }

    @Override
    public int swap(int index, int value) {
        int previous = this.get(index);
        this.set(index, value);

        return previous;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }

        long mask = (1L << this.bits) - 1;
        int longIndex = index / this.valuesPerLong;
        int bitOffset = (index % this.valuesPerLong) * this.bits;

        return (int) ((this.internalData[longIndex] >> bitOffset) & mask);
    }

    @Override
    public int[] getUnpackedData() {
        int[] unpacked = new int[this.size];
        long mask = (1L << this.bits) - 1;

        for (int i = 0; i < this.size; i++) {
            int longIndex = i / this.valuesPerLong;
            int bitOffset = (i % this.valuesPerLong) * this.bits;

            unpacked[i] = (int) ((this.internalData[longIndex] >> bitOffset) & mask);
        }

        return unpacked;
    }

    @Override
    public int getMaxBitWidth() {
        return Long.SIZE;
    }
}
