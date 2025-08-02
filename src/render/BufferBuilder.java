package render;

import gl.VertexBuffer;
import gl.VertexBufferObject;
import gl.glBufferUsage;
import util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BufferBuilder implements VertexConsumer {
    List<Float> vertices = new ArrayList<>();
    List<Float> uv = new ArrayList<>();
    List<Float> normals = new ArrayList<>();


    BufferBuilder() {
    }

    @Override
    public void vertex(float x, float y, float z) {
    }

    @Override
    public void texture(float u, float v) {

    }

    @Override
    public void normal(float nx, float ny, float nz) {

    }

    @Override
    public VertexBuffer endVertex() {
        VertexBuffer vertexBuffer = new VertexBuffer(glBufferUsage.GL_STATIC_DRAW);
        vertexBuffer.vertexes(ArrayUtils.convertListFloatToPrimitiveFloatArray(this.vertices));

        return vertexBuffer;
    }

    @Override
    public boolean isEmpty() {
        return this.vertices.isEmpty() && this.uv.isEmpty() && this.normals.isEmpty();
    }
}
