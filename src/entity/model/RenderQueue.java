package entity.model;

import java.util.ArrayList;

public abstract class RenderQueue<T> {
    protected final ArrayList<T> queue = new ArrayList<>();

    public int size() {
        return queue.size();
    }

    public void submit(T item) {
        this.queue.add(item);
    }

    protected abstract void renderAll();
}
