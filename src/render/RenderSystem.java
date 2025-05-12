package render;

import graphic.BasicModel;
import io.Window;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL46;

public class RenderSystem {
    private Window window;
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1F;
    private static final float FAR_PLANE = 100F;
    private Matrix4f projectionMatrix;

    public static void render(BasicModel model) {
        model.startShader();
        GL46.glBindVertexArray(model.getVaoID());
        GL46.glEnableVertexAttribArray(0);
        GL46.glDrawElements(GL46.GL_TRIANGLES, model.getVertices(), GL46.GL_UNSIGNED_INT, 0);
        GL46.glDisableVertexAttribArray(0);
        GL46.glBindVertexArray(0);
        model.stopShader();
    }
}
