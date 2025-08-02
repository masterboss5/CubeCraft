package render;

import gl.VertexBuffer;

public interface VertexConsumer {
    void vertex(float x, float y, float z);

    void quad(float x1, float y1, float z1,
              float x2, float y2, float z2,
              float x3, float y3, float z3,
              float x4, float y4, float z4);

    void texture(float u, float v);

    void normal(float nx, float ny, float nz);

    boolean isEmpty();

    VertexBuffer endVertex();
}
