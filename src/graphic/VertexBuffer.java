package graphic;

import org.lwjgl.opengl.GL46;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VertexBuffer {
    private int attributes = -1;
    private final int vertexArrayID;
    private final glUsage bufferUsage;
    private VertexBufferObject positionBufferObject;
    private IndexBufferObject indexBufferObject;
    public Map<Integer, VertexBufferObject> vertexBufferObjects = new HashMap<>();
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

    public VertexBuffer vertexes(float[] vertexes) {
        if (vertexes.length % 3 != 0) throw new IllegalArgumentException("vertexes must be in groups of 3");

        Float[] floatObjectArray = new Float[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            floatObjectArray[i] = vertexes[i];
        }

        Collections.addAll(this.vertexes, floatObjectArray);

        return this;
    }

    public void createNewVertexBufferObject(Object data, byte size, boolean normalized, glUsage bufferUsage) {
        this.vertexBufferObjects.put(this.attributes + 1, new VertexBufferObject(this, this.attributes, data, size, normalized, bufferUsage));
    }

    public void build() {
        this.positionBufferObject = new VertexBufferObject(this, 0, this.vertexes.toArray(new Float[0]), (byte) 3, false, glUsage.GL_STATIC_DRAW);
        this.vertexBufferObjects.put(this.attributes, positionBufferObject);
    }

    public void bind() {
        GL46.glBindVertexArray(this.vertexArrayID);
    }

    public void unbind() {
        GL46.glBindVertexArray(0);
    }

    public int getAttributes() {
        return attributes;
    }

    public VertexBufferObject getAttribute(int attribute) {
        return this.vertexBufferObjects.get(attribute);
    }

    protected void incrementAttributes() {
        this.attributes = attributes + 1;
    }

    public int getVertices() {
        return ((Object[]) this.positionBufferObject.getData()).length;
    }

    public int[] getIndices() {
        return this.indexBufferObject.getIndices();
    }

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
