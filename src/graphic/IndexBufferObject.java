package graphic;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;

public final class IndexBufferObject {
    private final int[] indices;
    private final int indexBufferID;
    private final glUsage bufferUsage;
    private final int count;

    protected IndexBufferObject(int[] indices, glUsage bufferUsage) {
        this.indices = indices;
        this.indexBufferID = GL46.glGenBuffers();
        this.bufferUsage = bufferUsage;
        this.count = indices.length;

        this.bind();
        GL46.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, this.indices, this.bufferUsage.getID());
        this.unbind();
    }

    public int[] getIndices() {
        return indices;
    }

    private void bind() {
        GL46.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.indexBufferID);
    }

    private void unbind() {
        GL46.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void delete() {
        GL46.glDeleteBuffers(this.indexBufferID);
    }

    public int getCount() {
        return count;
    }
}