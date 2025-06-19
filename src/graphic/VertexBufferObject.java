package graphic;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;

public final class VertexBufferObject {
    private final int attribute;
    private final int vertexBufferID;
    private final int size;
    private final glUsage bufferUsage;
    private final VertexBuffer parent;
    Object data;
    private final boolean normalized;

    protected VertexBufferObject(VertexBuffer parent, int attribute, Object data, byte size, boolean normalized, glUsage bufferUsage) {
        this.parent = parent;
        this.attribute = attribute;
        this.data = data;
        this.size = size;
        this.bufferUsage = bufferUsage;
        this.normalized = normalized;

        this.parent.bind();
        this.vertexBufferID = GL46.glGenBuffers();
        this.bind();

        if (data instanceof short[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribPointer(attribute, size, GL46.GL_SHORT, normalized, 0, 0);
        }

        if (data instanceof int[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribIPointer(attribute, size, GL46.GL_INT, 0, 0);
        }

        if (data instanceof float[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribPointer(attribute, size, GL46.GL_FLOAT, normalized, 0, 0);
        }

        if (data instanceof double[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribPointer(attribute, size, GL46.GL_DOUBLE, normalized, 0, 0);
        }

        this.unbind();
        this.parent.unbind();
    }

    public int getID() {
        return vertexBufferID;
    }

    private void bind() {
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vertexBufferID);
    }

    private void unbind() {
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public void delete() {
        GL46.glDeleteBuffers(this.vertexBufferID);
    }

    public int getAttribute() {
        return attribute;
    }

    public Object getData() {
        return data;
    }

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
