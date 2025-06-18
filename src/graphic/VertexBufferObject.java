package graphic;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;

public final class VertexBufferObject {
    private final int attribute;
    private final int vertexBufferID;
    private final int size;
    private final glUsage bufferUsage;
    private final VertexBuffer parent;

    protected VertexBufferObject(VertexBuffer parent, int attribute, Object data, byte size, boolean normalized, glUsage bufferUsage) {
        this.parent = parent;
        this.attribute = attribute;
        this.vertexBufferID = GL46.glGenBuffers();
        this.size = size;
        this.bufferUsage = bufferUsage;

        this.parent.bind();
        bind();
        this.parent.incrementAttributes();

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

        unbind();
        this.parent.unbind();
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

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
