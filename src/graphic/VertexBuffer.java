package graphic;

import org.lwjgl.opengl.GL40;

public class VertexBuffer {
    private int vertexArrayID;

    public VertexBuffer() {
        this.vertexArrayID = GL40.glGenVertexArrays();
        GL40.glBindVertexArray(this.vertexArrayID);
    }


}
