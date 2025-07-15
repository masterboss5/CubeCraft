package util.collection;

/**
 * @param <T> the type of data the palette stores
* */
public interface Palette<T> {
    /**
    * Will retrieve the index tied to the value, if the value does not exist
    * it will register the new value to a new index. If the palette is not big enough
    * it will transfer to the next biggest palette
    * */
    int index(T block);

    /**
     * Gets the object associated with the index in this palette
     * */
    T get(int id);

    /**
     * Checks to see if this palette contains a specific object
     * */
    boolean contains(T object);

    /**
     * Checks to see if this palette has no indices
     * */
    boolean isEmpty();

    /**
     * Adds an object to the palette
     * */
    int add(T object);

    /**
     * Requests a new bigger palette, returns
     * the new index of the newly indexed object
     * */
    int resize(int bits, T object);
}
