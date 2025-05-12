package graphic;

import io.InputHandler;
import org.joml.Math;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private InputHandler inputHandler;
    private Vector3f position = new Vector3f();
    private Vector3f rotation = new Vector3f();

    public Camera(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void tick() {
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
}
