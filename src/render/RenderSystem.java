package render;

import block.AbstractBlock;
import graphic.BasicModel;
import graphic.Camera;
import graphic.Model;
import io.Window;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL46;
import util.Math;

public class RenderSystem {
    private static Matrix4f projectionMatrix;
    private static Window window;
    private static Camera camera;

    public static void init(Window _window, Camera _camera) {
        window = _window;
        camera = _camera;
        projectionMatrix = Math.createProjectionMatrix(window, camera.getFOV(), camera.getNearPlane(), camera.getFarPlane());
    }

    public static void render(BasicModel model) {
        model.startShader();
        GL46.glBindVertexArray(model.getVaoID());
        GL46.glEnableVertexAttribArray(0);

        model.getShaderProgram().setViewMatrix4fUniform(Math.createViewMatrix(camera));
        model.getShaderProgram().setTransformationMatrix4fUniform(Math.createTransformationMatrix(model.getPosition(), model.getRotation(), model.getScale()));
        model.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);

        GL46.glDrawElements(GL46.GL_TRIANGLES, model.getVertices(), GL46.GL_UNSIGNED_INT, 0);
        GL46.glDisableVertexAttribArray(0);
        GL46.glBindVertexArray(0);
        model.stopShader();
    }

    public static void render(AbstractBlock block) {

        Model model = block.getModel();

        model.startShader();
        GL46.glBindVertexArray(model.getVaoID());
        GL46.glEnableVertexAttribArray(0);

        model.getShaderProgram().setViewMatrix4fUniform(Math.createViewMatrix(camera));
        model.getShaderProgram().setTransformationMatrix4fUniform(Math.createTransformationMatrix(model.getPosition(), model.getRotation(), model.getScale()));
        model.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);

        GL46.glDrawElements(GL46.GL_TRIANGLES, model.getVertices(), GL46.GL_UNSIGNED_INT, 0);
        GL46.glDisableVertexAttribArray(0);
        GL46.glBindVertexArray(0);
        model.stopShader();
    }
}
