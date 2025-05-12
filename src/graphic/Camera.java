package graphic;

import io.InputHandler;
import io.Window;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private InputHandler inputHandler;
    private Vector3f position = new Vector3f();
    private Vector3f rotation = new Vector3f();
    private Window window;
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1F;
    private static final float FAR_PLANE = 100F;
    private Matrix4f viewMatrix;

    public Camera(Window window, InputHandler inputHandler) {
        this.window = window;
        this.inputHandler = inputHandler;
    }

    public void tick() {
        this.viewMatrix = util.Math.createViewMatrix(this);

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_W)) {
            this.position.z = this.position.z - 0.02F;
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_S)) {
            this.position.z = this.position.z + 0.02F;
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_D)) {
            this.position.x = this.position.x + 0.02F;
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_A)) {
            this.position.x = this.position.x - 0.02F;
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_UP)) {
            this.position.y = this.position.y + 0.02F;
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            this.position.y = this.position.y - 0.02F;
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            this.rotation.x = this.rotation.x - org.joml.Math.toRadians(15);
        }

        if (this.inputHandler.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            this.rotation.x = this.rotation.x + Math.toRadians(15);
        }

        this.viewMatrix.translate(this.position.negate());
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }
}
