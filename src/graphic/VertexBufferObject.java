package graphic;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;

public final class VertexBufferObject {
    private final int attribute;
    private final int vertexObjectID;
    private final int size;
    private final glUsage bufferUsage;

    protected VertexBufferObject(int attribute, Object data, byte size, boolean normalized, glUsage bufferUsage) {
        this.attribute = attribute;
        this.vertexObjectID = GL46.glGenBuffers();
        this.size = size;
        this.bufferUsage = bufferUsage;

        bind();

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
    }

    protected VertexBufferObject(int attribute, int byteSize, byte size, boolean normalized, glUsage bufferUsage) {
        this.attribute = attribute;
        this.vertexObjectID = GL46.glGenBuffers();
        this.size = size;
        this.bufferUsage = bufferUsage;

        bind();
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, byteSize, bufferUsage.getID());
        GL46.glVertexAttribPointer(attribute, size, GL46.GL_FLOAT, normalized, 0, 0);
        unbind();
    }

    protected void update(Object data, boolean normalized) {
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, this.vertexObjectID);

        if (data instanceof short[] bufferData) {
            GL46.glBufferData(GL46.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribPointer(this.attribute, this.size, GL46.GL_SHORT, normalized, 0, 0);
        }

        if (data instanceof int[] bufferData) {
            GL46.glBufferData(GL46.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribIPointer(this.attribute, this.size, GL46.GL_INT, 0, 0);
        }

        if (data instanceof float[] bufferData) {
            GL46.glBufferData(GL46.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribPointer(this.attribute, this.size, GL46.GL_FLOAT, normalized, 0, 0);
        }

        if (data instanceof double[] bufferData) {
            GL46.glBufferData(GL46.GL_ARRAY_BUFFER, bufferData, this.bufferUsage.getID());
            GL46.glVertexAttribPointer(this.attribute, this.size, GL46.GL_DOUBLE, normalized, 0, 0);
        }

        unbind();
    }

    private void bind() {
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vertexObjectID);
    }

    private void unbind() {
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }


    public void delete() {
        GL46.glDeleteBuffers(this.vertexObjectID);
    }

    public int getAttribute() {
        return attribute;
    }

    public glUsage getBufferUsage() {
        return bufferUsage;
    }
}
