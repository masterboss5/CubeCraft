package graphic;

import org.lwjgl.opengl.GL46;

import java.util.HashMap;
import java.util.Map;

public class VertexBuffer {
    private int attributes = 0;
    private final int vertexArrayID;
    private final glUsage bufferUsage;
    private VertexBufferObject positionBufferObject;
    private IndexBufferObject indexBufferObject;
    Map<Integer, VertexBufferObject> vertexBufferObjects = new HashMap<>();

    public VertexBuffer(glUsage bufferUsage) {
        this.bufferUsage = bufferUsage;
        this.vertexArrayID = GL46.glGenVertexArrays();
        this.positionBufferObject = new VertexBufferObject(0, (byte) 0, (byte) 0, false, glUsage.GL_STATIC_DRAW);
    }

    public VertexBuffer indices(int[] indices) {
        this.indexBufferObject = new IndexBufferObject(indices, this.bufferUsage);

        return this;
    }

    public VertexBuffer vertex(float x, float y, float z) {
        this.positionBufferObject.update();

        return this;
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

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
