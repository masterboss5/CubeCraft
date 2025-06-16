package graphic;

import org.lwjgl.opengl.GL46;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VertexBuffer {
    private int attributes;
    private final int vertexArrayID;
    Map<Integer, VertexBufferObject> vertexBufferObjects = new HashMap<>();

    public VertexBuffer() {
        this.vertexArrayID = GL46.glGenVertexArrays();
//        GL46.glBindVertexArray(this.vertexArrayID);
    }

    public void createBufferObject(int attributeID, Object data, byte size, boolean normalized) {

    }
}
