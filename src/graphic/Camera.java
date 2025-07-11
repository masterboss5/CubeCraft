package graphic;

import io.InputManager;
import io.Window;
import org.joml.Math;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position = new Vector3f();
    private Vector3f rotation = new Vector3f();
    private final Window window;
    private boolean mouseLockedLastFrame = false;
    private boolean skipRotationFrame = false;
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1F;
    private static final float FAR_PLANE = 360F;
    private static final float MOUSE_SENSITIVITY = 0.1F;
    private static final float MOVE_SPEED = 0.1f;
    private static final float ROTATION_SPEED = 0.02f;

    public Camera(Window window) {
        this.window = window;
    }

    public void tick() {
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            rotation.y -= ROTATION_SPEED;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            rotation.y += ROTATION_SPEED;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_UP)) {
            rotation.x -= ROTATION_SPEED;
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            rotation.x += ROTATION_SPEED;
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

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_W)) {
            position.add(forward.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_S)) {
            position.sub(forward.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_D)) {
            position.add(right.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_A)) {
            position.sub(right.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            position.y += MOVE_SPEED;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            position.y -= MOVE_SPEED;
        }

        boolean mouseDown = InputManager.getMouse().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT);

        if (mouseDown) {
            this.getWindow().disableCursor();

            if (!this.mouseLockedLastFrame) {
                InputManager.getMouse().getMousePos().reset();
                this.skipRotationFrame = true;
            }

            if (!this.skipRotationFrame) {
                float deltaX = (float) InputManager.getMouse().getMousePos().getDeltaX();
                float deltaY = (float) InputManager.getMouse().getMousePos().getDeltaY();

                this.rotation.y += deltaX * MOUSE_SENSITIVITY * 0.01f;
                this.rotation.x += deltaY * MOUSE_SENSITIVITY * 0.01f;
                this.rotation.x = Math.clamp(this.rotation.x, -Math.toRadians(89), Math.toRadians(89));
            }
        } else {
            this.getWindow().enableCursor();
        }

        this.mouseLockedLastFrame = mouseDown;
        this.skipRotationFrame = false;
        InputManager.getMouse().getMousePos().resetDelta();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setPositionX(int x) {
        this.getPosition().x = x;
    }

    public void setPositionY(int y) {
        this.getPosition().y = y;
    }

    public void setPositionZ(int z) {
        this.getPosition().z = z;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setRotationX(int x) {
        this.getRotation().x = x;
    }

    public void setRotationY(int y) {
        this.getRotation().y = y;
    }

    public void setRotationZ(int z) {
        this.getRotation().z = z;
    }

    public Window getWindow() {
        return window;
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