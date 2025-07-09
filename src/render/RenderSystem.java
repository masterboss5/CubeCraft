package render;

import block.Block;
import graphic.BlockModel;
import graphic.Camera;
import io.Window;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL46;
import texture.TextureManager;
import util.Math;
import world.Chunk;
import world.ChunkMesh;
import world.ChunkPosition;

import java.util.List;
import java.util.Random;

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
        projectionMatrix = Math.createProjectionMatrix(window, camera.getFOV(), camera.getNearPlane(), camera.getFarPlane());
    }

/*    @Deprecated
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
    }*/

/*    public static void render(Block block) {

        Model model = block.getModel();

        model.startShader();
        model.tickShaderProgram();
        GL46.glBindVertexArray(model.getVaoID());
        GL46.glEnableVertexAttribArray(0);

        model.getShaderProgram().setViewMatrix4fUniform(Math.createViewMatrix(camera));
        model.getShaderProgram().setTransformationMatrix4fUniform(Math.createTransformationMatrix(block.getPosition(), model.getRotation(), model.getScale()));
        model.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);

        GL46.glDrawElements(GL46.GL_TRIANGLES, model.getVertices(), GL46.GL_UNSIGNED_INT, 0);
        GL46.glDisableVertexAttribArray(0);
        GL46.glBindVertexArray(0);
        model.stopShader();
    }*/

    public static void render(Block block) {
        BlockModel model = block.getModel();

        model.startShaderProgram();
        model.tickShaderProgram();

        model.getVertexBuffer().bindAll();

        model.getShaderProgram().setViewMatrix4fUniform(Math.createViewMatrix(camera));
        model.getShaderProgram().setTransformationMatrix4fUniform(Math.createTransformationMatrix(block.getPosition().toVector3f(), model.getRotation(), model.getScale()));
        model.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);

/*        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getTextureID());*/

        GL46.glDrawElements(GL46.GL_TRIANGLES, model.getIndicesCount(), GL46.GL_UNSIGNED_INT, 0);

        model.getVertexBuffer().unbindAll();
        model.stopShader();
    }
    
    public static void renderBatched(List<Block> blocks) {
        BlockModel model = blocks.getFirst().getModel();

        model.startShaderProgram();
        model.tickShaderProgram();

        model.getVertexBuffer().bindAll();

        model.getShaderProgram().setViewMatrix4fUniform(Math.createViewMatrix(camera));
        model.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);

/*        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getTextureID());*/

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL46.GL_TEXTURE_2D_ARRAY, TextureManager.getBlockTextureArrayID());

        for (Block block : blocks) {

            if (block.isAirBlock()) continue;

            model.getShaderProgram().setTransformationMatrix4fUniform(Math.createTransformationMatrix(block.getPosition().toVector3f(), model.getRotation(), model.getScale()));
            GL46.glDrawElements(GL46.GL_TRIANGLES, model.getIndicesCount(), GL46.GL_UNSIGNED_INT, 0);
        }

        model.getVertexBuffer().unbindAll();
        model.stopShader();
    }

    public static void renderChunk(ChunkMesh chunkMesh, Chunk chunk) {
        chunkMesh.startShaderProgram();
        chunkMesh.getShaderProgram().tickShaderProgram();

        chunkMesh.getVertexBuffer().bindAll();

        chunkMesh.getShaderProgram().setViewMatrix4fUniform(Math.createViewMatrix(camera));
        chunkMesh.getShaderProgram().setProjectionMatrix4fUniform(projectionMatrix);

        Vector3f chunkGridPos = chunk.getChunkPosition().toVector3f(); // e.g., (2, 0, 3)
        Vector3f chunkWorldPos = new Vector3f(
                chunkGridPos.x * ChunkPosition.CHUNK_WIDTH,
                chunkGridPos.y * ChunkPosition.CHUNK_HEIGHT,
                chunkGridPos.z * ChunkPosition.CHUNK_WIDTH
        );

        Matrix4f transform = Math.createTransformationMatrix(
                chunkWorldPos,
                new Vector3f(0, 0, 0),          // No rotation
                new Vector3f(1, 1, 1)           // Uniform scale
        );

        chunkMesh.getShaderProgram().setTransformationMatrix4fUniform(transform);

/*        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getTextureID());*/

        System.out.println(chunkMesh.getVertexBuffer().getIndicesCount());
        GL46.glDrawElements(GL46.GL_TRIANGLES, chunkMesh.getVertexBuffer().getIndicesCount(), GL46.GL_UNSIGNED_INT, 0);

        chunkMesh.getVertexBuffer().unbindAll();
        GL46.glUseProgram(0);
    }
}
