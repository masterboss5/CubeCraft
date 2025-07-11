package graphic;

import io.InputManager;
import io.Window;
import org.joml.Math;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

//TODO fix control and add relative movement
public class Camera {
    private Vector3f position = new Vector3f();
    private Vector3f rotation = new Vector3f();
    private Window window;
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1F;
    private static final float FAR_PLANE = 360F;
    private static final float SENSITIVITY = 0.15F;

    public Camera(Window window) {
        this.window = window;
    }

    public void tick() {
        float moveSpeed = 0.1f;
        float rotateSpeed = 0.02f;

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            rotation.y -= rotateSpeed;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            rotation.y += rotateSpeed;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_UP)) {
            rotation.x -= rotateSpeed;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            rotation.x += rotateSpeed;
        }

        rotation.x = Math.clamp(rotation.x, -Math.toRadians(89), Math.toRadians(89));

        float yaw = rotation.y;
        float pitch = rotation.x;

        Vector3f forward = new Vector3f(
                Math.cos(pitch) * Math.sin(yaw),
                -Math.sin(pitch),
                -(Math.cos(pitch) * Math.cos(yaw))
        ).normalize();

        Vector3f right = new Vector3f(
                Math.cos(yaw),
                0,
                Math.sin(yaw)
        ).normalize();

        Vector3f up = new Vector3f(0, 1, 0);

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_W)) {
            position.add(forward.mul(moveSpeed, new Vector3f()));
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_S)) {
            position.sub(forward.mul(moveSpeed, new Vector3f()));
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_D)) {
            position.add(right.mul(moveSpeed, new Vector3f()));
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_A)) {
            position.sub(right.mul(moveSpeed, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            position.y += moveSpeed;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position.y -= moveSpeed;
        }

        if (InputManager.getMouse().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
            float deltaX = (float) InputManager.getMouse().getMousePos().getDeltaX();
            float deltaY = (float) InputManager.getMouse().getMousePos().getDeltaY();

            rotation.y += deltaX * SENSITIVITY * 0.01f;
            rotation.x += deltaY * SENSITIVITY * 0.01f;
            GLFW.glfwSetInputMode(window.getPointer(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

            // Clamp pitch to avoid flipping
            rotation.x = Math.clamp(rotation.x, -Math.toRadians(89), Math.toRadians(89));
        } else {
            GLFW.glfwSetInputMode(window.getPointer(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        }

        InputManager.getMouse().getMousePos().resetDelta();
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

    public float getFOV() {
        return FOV;
    }

    public float getFarPlane() {
        return FAR_PLANE;
    }

    public float getNearPlane() {
        return NEAR_PLANE;
    }
}
