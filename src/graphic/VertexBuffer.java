package graphic;

import org.lwjgl.opengl.GL46;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VertexBuffer {
    private int attributes = 0;
    private final int vertexArrayID;
    private final glUsage bufferUsage;
    private VertexBufferObject positionBufferObject;
    private IndexBufferObject indexBufferObject;
    Map<Integer, VertexBufferObject> vertexBufferObjects = new HashMap<>();
    ArrayList<Float> vertexes = new ArrayList<>();

    public VertexBuffer(glUsage bufferUsage) {
        this.bufferUsage = bufferUsage;
        this.vertexArrayID = GL46.glGenVertexArrays();
    }

    public VertexBuffer indices(int[] indices) {
        this.indexBufferObject = new IndexBufferObject(this, indices, this.bufferUsage);

        return this;
    }

    public VertexBuffer vertex(float x, float y, float z) {
        this.vertexes.add(x);
        this.vertexes.add(y);
        this.vertexes.add(z);

        return this;
    }

    public VertexBuffer vertexes(Float[] vertexes) {
        if (vertexes.length % 3 != 0) throw new IllegalArgumentException("vertexes must be in groups of 3");

        Collections.addAll(this.vertexes, vertexes);

        return this;
    }

    public void build() {
        this.positionBufferObject = new VertexBufferObject(this, 0, this.vertexes.toArray(new Float[0]), (byte) 0, false, glUsage.GL_STATIC_DRAW);
    }

    public void bind() {
        GL46.glBindVertexArray(this.vertexArrayID);
    }

    public void unbind() {
        GL46.glBindVertexArray(0);
    }

    public int[] getIndices() {
        return this.indexBufferObject.getIndices();
    }

    public int getAttributes() {
        return attributes;
    }

    protected void incrementAttributes() {
        this.attributes = attributes + 1;
    }

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
