package graphic;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL46;

public class VertexBufferObject {
    private final int attributeID;
    private final int vertexObjectID;

    protected VertexBufferObject(int attributeID, Object data, byte size, boolean normalized) {
        this.attributeID = attributeID;
        this.vertexObjectID = GL46.glGenBuffers();
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, this.vertexObjectID);

        if (data instanceof short[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, GL46.GL_STATIC_DRAW);
            GL46.glVertexAttribPointer(attributeID, size, GL46.GL_SHORT, normalized, 0, 0);
        }

        if (data instanceof int[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, GL46.GL_STATIC_DRAW);
            GL46.glVertexAttribIPointer(attributeID, size, GL46.GL_INT, 0, 0);
        }

        if (data instanceof float[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, GL46.GL_STATIC_DRAW);
            GL46.glVertexAttribPointer(attributeID, size, GL46.GL_FLOAT, normalized, 0, 0);
        }

        if (data instanceof double[] bufferData) {
            GL46.glBufferData(GL15.GL_ARRAY_BUFFER, bufferData, GL46.GL_STATIC_DRAW);
            GL46.glVertexAttribPointer(attributeID, size, GL46.GL_DOUBLE, normalized, 0, 0);
        }

        GL46.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
}
