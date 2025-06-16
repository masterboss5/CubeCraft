package graphic;

public class VertexBufferObjects {
    private final int attributeID;

    public <T> VertexBufferObjects(int attributeID, T[] value, byte size) {
        this.attributeID = attributeID;
    }
}
