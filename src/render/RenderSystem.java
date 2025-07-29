package render;

import graphic.Camera;
import io.Window;
import json.TextureArrays;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL46;
import registry.Registries;
import util.MathUtils;
import world.Chunk;
import world.ChunkMesh;

public class RenderSystem {
    private static Matrix4f projectionMatrix;
    private static Window window;
    private static Camera camera;

    public static void init(Window _window, Camera _camera) {
        window = _window;
        camera = _camera;
        createProjectionMatrix();
    }

    public static void createProjectionMatrix() {
        projectionMatrix = MathUtils.createProjectionMatrix(window, camera.getFOV(), camera.getNearPlane(), camera.getFarPlane());
        Registries.SHADER_PROGRAM.values().forEach((shaderProgram -> {
            GL46.glUseProgram(shaderProgram.getProgramID());
            shaderProgram.setProjectionMatrix4fUniform(projectionMatrix);
        }));

        GL46.glUseProgram(0);
    }

    public static void renderChunk(ChunkMesh chunkMesh, Chunk chunk) {
        chunkMesh.startShaderProgram();
        chunkMesh.getShaderProgram().tickShaderProgram();
        chunkMesh.getVertexBuffer().bindAll();

        chunkMesh.getShaderProgram().setViewMatrix4fUniform(MathUtils.createViewMatrix(camera));
//        chunkMesh.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);
        chunkMesh.getShaderProgram().setTransformationMatrix4fUniform(chunkMesh.getTransformationMatrix());

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, TextureArrays.BLOCK_TEXTURE_ARRAY.getArrayID());

        GL46.glDrawElements(GL46.GL_TRIANGLES, chunkMesh.getVertexBuffer().getIndicesCount(), GL46.GL_UNSIGNED_INT, 0);

        chunkMesh.getVertexBuffer().unbindAll();
        GL46.glUseProgram(0);
    }
}
