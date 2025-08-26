package gl;

import org.lwjgl.opengl.ARBBindlessTexture;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;

public final class VertexBufferObject {
    private final int attribute;
    private final int vertexBufferID;
    private final int size;
    private final glBufferUsage bufferUsage;
    private final VertexBuffer parent;
    private final boolean normalized;
    final Object data;

    protected VertexBufferObject(VertexBuffer parent, int attribute, Object data, byte size, boolean normalized, glBufferUsage bufferUsage) {
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
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.getBufferUsage().getConstant());
            GL46.glVertexAttribPointer(attribute, size, GL46.GL_SHORT, normalized, 0, 0);
        }

        if (data instanceof int[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.getBufferUsage().getConstant());
            GL46.glVertexAttribIPointer(attribute, size, GL46.GL_INT, 0, 0);
        }

        if (data instanceof float[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.getBufferUsage().getConstant());
            GL46.glVertexAttribPointer(attribute, size, GL46.GL_FLOAT, normalized, 0, 0);
        }

        if (data instanceof double[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.getBufferUsage().getConstant());
            GL46.glVertexAttribPointer(attribute, size, GL46.GL_DOUBLE, normalized, 0, 0);
        }

        if (data instanceof long[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, this.getBufferUsage().getConstant());
            GL46.glVertexAttribLPointer(attribute, size, ARBBindlessTexture.GL_UNSIGNED_INT64_ARB, 0, 0);
        }

        this.unbind();
        this.parent.unbind();
    }

    public int getID() {
        return vertexBufferID;
    }

    private void bind() {
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.getID());
    }

    private void unbind() {
        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public void delete() {
        GL46.glDeleteBuffers(this.getID());
    }

    public int getAttribute() {
        return attribute;
    }

    public Object getData() {
        return data;
    }

    public glBufferUsage getBufferUsage() {
        return bufferUsage;
    }

    public boolean isNormalized() {
        return normalized;
    }
}
