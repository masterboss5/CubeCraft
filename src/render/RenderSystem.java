package render;

import entity.Entity;
import gl.VertexBuffer;
import graphic.Camera;
import graphic.ModelPart;
import graphic.ModelPartInstance;
import io.Window;
import json.TextureArrays;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.ARBBindlessTexture;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL46;
import registry.Registries;
import shader.ShaderPrograms;
import util.MathUtils;
import world.Chunk;
import world.ChunkMesh;

import java.util.List;

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

    public static void renderEntity(VertexBuffer vertexBuffer, Entity entity) {
        GL46.glUseProgram(ShaderPrograms.ENTITY_SHADER_PROGRAM.getProgramID());
        vertexBuffer.bindAll();

        ShaderPrograms.ENTITY_SHADER_PROGRAM.setViewMatrix4fUniform(MathUtils.createViewMatrix(camera));
        ShaderPrograms.ENTITY_SHADER_PROGRAM.setTransformationMatrix4fUniform(MathUtils.createTransformationMatrix(
                new Vector3f((float) entity.getX(), (float) entity.getY(), (float) entity.getZ()),
                new Vector3f(0, 0, 0),
                new Vector3f(1)
        ));

        GL46.glDrawElements(GL46.GL_TRIANGLES, vertexBuffer.getIndicesCount(), GL46.GL_UNSIGNED_INT, 0);

        vertexBuffer.unbindAll();
        GL46.glUseProgram(0);
    }

/*    public static void renderEntityInstanced(List<ModelPart> parts) {
        GL46.glUseProgram(ShaderPrograms.ENTITY_SHADER_PROGRAM.getProgramID());
        ShaderPrograms.ENTITY_SHADER_PROGRAM.tickShaderProgram();

        // Bind shared unit cube VAO
        GL46.glBindVertexArray(unitCubeVAO);

        ShaderPrograms.ENTITY_SHADER_PROGRAM.setViewMatrix4fUniform(MathUtils.createViewMatrix(camera));

        // Prepare transform matrix array
        float[] matrices = new float[16 * parts.size()];
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).getModelTransformationMatrix4f().get(matrices, i * 16);
        }

        // Upload transform data to GPU
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, instanceTransformVBO);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, matrices, GL46.GL_DYNAMIC_DRAW);

        // Bind transform attributes (mat4 = 4 vec4s)
        int baseLocation = 3;
        int stride = 64;

        for (int i = 0; i < 4; i++) {
            GL46.glEnableVertexAttribArray(baseLocation + i);
            GL46.glVertexAttribPointer(baseLocation + i, 4, GL46.GL_FLOAT, false, stride, i * 16);
            GL46.glVertexAttribDivisor(baseLocation + i, 1);
        }

        // Draw all instances using shared cube geometry
        GL46.glDrawElementsInstanced(
                GL46.GL_TRIANGLES,
                indexCount, // ← use the correct index count from unit cube setup
                GL46.GL_UNSIGNED_INT,
                0,
                parts.size()
        );

        GL46.glBindVertexArray(0);
        GL46.glUseProgram(0);
    }*/

    public static void renderEntityInstanced(List<ModelPartInstance> parts) {
        GL46.glUseProgram(ShaderPrograms.ENTITY_SHADER_PROGRAM.getProgramID());
        ModelPart.UNIFORM_CUBE_VERTEX_BUFFER.bindAll();
        ShaderPrograms.ENTITY_SHADER_PROGRAM.setViewMatrix4fUniform(MathUtils.createViewMatrix(camera));

        long[] texturePointers = new long[parts.size() * 24];
        for (int i = 0; i < parts.size(); i++) {
            System.arraycopy(parts.get(i).modelPart().getTextureData().toLongArray(), 0, texturePointers, i * 24, 24);
        }

        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, GL46.glGenBuffers());
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, texturePointers, GL46.GL_DYNAMIC_DRAW);
        GL46.glVertexAttribLPointer(2, 1, ARBBindlessTexture.GL_UNSIGNED_INT64_ARB, 0, 0);
        GL46.glEnableVertexAttribArray(2);

        float[] matrices = new float[16 * parts.size()];
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).modelPart().getModelTransformationMatrix4f(parts.get(i).entity()).get(matrices, i * 16);
        }

        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, GL46.glGenBuffers());
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, matrices, GL46.GL_DYNAMIC_DRAW);

        int baseLocation = 3;
        for (int i = 0; i < 4; i++) {
            GL46.glEnableVertexAttribArray(baseLocation + i);
            GL46.glVertexAttribPointer(baseLocation + i, 4, GL46.GL_FLOAT, false, 64, i * 16);
            GL46.glVertexAttribDivisor(baseLocation + i, 1);
        }

        GL46.glDrawElementsInstanced(
                GL46.GL_TRIANGLES,
                36,
                GL46.GL_UNSIGNED_INT,
                0,
                parts.size()
        );

        GL46.glBindVertexArray(0);
        GL46.glUseProgram(0);
    }
}
