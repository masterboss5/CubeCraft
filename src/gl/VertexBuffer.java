package gl;

import org.lwjgl.opengl.GL46;
import util.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VertexBuffer {
    private int attributeCount = 0;
    private final int vertexArrayID;
    private final glUsage bufferUsage;
    private VertexBufferObject positionBufferObject;
    private IndexBufferObject indexBufferObject;
    private final Map<Integer, VertexBufferObject> vertexBufferObjects = new HashMap<>();
    ArrayList<Float> vertices = new ArrayList<>();

    public VertexBuffer(glUsage bufferUsage) {
        this.bufferUsage = bufferUsage;
        this.vertexArrayID = GL46.glGenVertexArrays();
    }

    public VertexBuffer indices(int[] indices) {
        this.indexBufferObject = new IndexBufferObject(this, indices, this.bufferUsage);

        return this;
    }

    public VertexBuffer vertex(float x, float y, float z) {
        this.vertices.add(x);
        this.vertices.add(y);
        this.vertices.add(z);

        return this;
    }

    public VertexBuffer vertexes(float[] vertexes) {
        if (vertexes.length % 3 != 0) throw new IllegalArgumentException("vertexes must be in groups of 3");

        Float[] floatObjectArray = new Float[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            floatObjectArray[i] = vertexes[i];
        }

        Collections.addAll(this.vertices, floatObjectArray);

        return this;
    }

    public void createNewVertexBufferObject(Object data, byte size, boolean normalized, glUsage bufferUsage) {
        this.vertexBufferObjects.put(this.attributeCount, new VertexBufferObject(this, this.attributeCount, data, size, normalized, bufferUsage));
        this.incrementAttributeCount();
    }

    public void build() {
        this.positionBufferObject = new VertexBufferObject(this, 0, ArrayUtils.convertWrapperFloatToPrimitiveFloat(this.vertices.toArray(new Float[0])), (byte) 3, false, glUsage.GL_STATIC_DRAW);
        this.vertexBufferObjects.put(0, positionBufferObject);
        this.incrementAttributeCount();
    }

    public int getID() {
        return vertexArrayID;
    }

    public void bindAll() {
        this.bind();
        this.getIndexBufferObject().bind();

        this.vertexBufferObjects.forEach(((attrib, vertexBufferObject) -> {
            GL46.glEnableVertexAttribArray(attrib);
        }));
    }

    public void unbindAll() {
        this.unbind();
        this.getIndexBufferObject().unbind();

        this.vertexBufferObjects.forEach(((attrib, vertexBufferObject) -> {
            GL46.glDisableVertexAttribArray(attrib);
        }));
    }

    public void bind() {
        GL46.glBindVertexArray(this.vertexArrayID);
    }

    public void unbind() {
        GL46.glBindVertexArray(0);
    }

    public int getAttributeCount() {
        return attributeCount;
    }

    public VertexBufferObject getAttribute(Integer attribute) {
        return this.vertexBufferObjects.get(attribute);
    }

    public IndexBufferObject getIndexBufferObject() {
        return indexBufferObject;
    }

    protected void incrementAttributeCount() {
        this.attributeCount = attributeCount + 1;
    }

    public int getVerticesCount() {
        return ((Float[]) this.positionBufferObject.getData()).length / 3;
    }

    public int getIndicesCount() {
        return this.indexBufferObject.getIndicesCount();
    }

    public Float[] getVertices() {
        return (Float[]) this.positionBufferObject.getData();
    }

    public int[] getIndices() {
        return this.indexBufferObject.getIndices();
    }

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
