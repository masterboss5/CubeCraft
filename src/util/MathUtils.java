package util;

import graphic.Camera;
import io.Window;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MathUtils {
    public static float[] matrix4fToFloatArray(Matrix4f matrix4f) {
        return matrix4f.get(new float[16]);
    }

    public static Matrix4f createTransformationMatrix(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f transformationMatrix4f = new Matrix4f().identity();

        transformationMatrix4f.translate(position, transformationMatrix4f);
        transformationMatrix4f.rotate((float) java.lang.Math.toRadians(rotation.x), new Vector3f(1,0,0), transformationMatrix4f);
        transformationMatrix4f.rotate((float) java.lang.Math.toRadians(rotation.y), new Vector3f(0,1,0), transformationMatrix4f);
        transformationMatrix4f.rotate((float) java.lang.Math.toRadians(rotation.z), new Vector3f(0,0,1), transformationMatrix4f);
        transformationMatrix4f.scale(scale, transformationMatrix4f);

        return transformationMatrix4f;
    }

    public static Matrix4f createProjectionMatrix(Window window, float FOV, float nearPlane, float farPlane) {
        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        float yScale = (float) (1F / java.lang.Math.tan(java.lang.Math.toRadians(FOV / 2F))) * aspectRatio;
        float xScale = yScale / aspectRatio;
        float frustumLength = farPlane - nearPlane;

        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.m00(xScale);
        projectionMatrix.m11(yScale);
        projectionMatrix.m22(-((farPlane + nearPlane) / frustumLength));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * nearPlane * farPlane) / frustumLength));
        projectionMatrix.m33(0);

        return projectionMatrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f().identity();

        // Apply pitch (rotation.x) first, then yaw (rotation.y)
        viewMatrix.rotate(camera.getRotation().x, new Vector3f(1, 0, 0)); // Pitch (X axis)
        viewMatrix.rotate(camera.getRotation().y, new Vector3f(0, 1, 0)); // Yaw (Y axis)

        // Translate to the negative camera position
        Vector3f cameraPos = camera.getPosition();
        viewMatrix.translate(new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z));

        return viewMatrix;
    }
}
