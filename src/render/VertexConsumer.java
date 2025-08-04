package render;

import gl.VertexBuffer;

public interface VertexConsumer {
    void vertex(float x, float y, float z);

    void quad(double x1, double y1, double z1,
              double x2, double y2, double z2,
              double x3, double y3, double z3,
              double x4, double y4, double z4);

    void texture(float u, float v);

    void normal(float nx, float ny, float nz);

    boolean isEmpty();

    VertexBuffer endVertex();
}
