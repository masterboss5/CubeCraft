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
    private static final float MOUSE_SENSITIVITY = 0.5F;
    private static final float MOVE_SPEED = 0.1f;
    private static final float ROTATION_SPEED = 0.02f;
    private static boolean isYAxisInverted = false;

    public Camera(Window window) {
        this.window = window;
    }

    public void tick() {
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            this.setRotationY(this.getRotationY() - ROTATION_SPEED);
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            this.setRotationY(this.getRotationY() + ROTATION_SPEED);
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_UP)) {
            this.setRotationX(this.getRotationX() - ROTATION_SPEED);
        }
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            this.setRotationX(this.getRotationX() + ROTATION_SPEED);
        }

        Vector3f forward = new Vector3f(
                Math.cos(this.getRotationX()) * Math.sin(this.getRotationY()),
                -Math.sin(this.getRotationX()),
                -(Math.cos(this.getRotationX()) * Math.cos(this.getRotationY()))
        ).normalize();

        Vector3f right = new Vector3f(
                Math.cos(this.getRotationY()),
                0,
                Math.sin(this.getRotationY())
        ).normalize();

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_W)) {
            this.getPosition().add(forward.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_S)) {
            this.getPosition().sub(forward.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_D)) {
            this.getPosition().add(right.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_A)) {
            this.getPosition().sub(right.mul(MOVE_SPEED, new Vector3f()));
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            this.setPositionY(this.getPositionY() + MOVE_SPEED);
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            this.setPositionY(this.getPositionY() - MOVE_SPEED);
        }

        boolean IsMouseDown = InputManager.getMouse().isRightMouseButtonDown();

        if (IsMouseDown) {
            this.getWindow().disableCursor();

            if (!this.mouseLockedLastFrame) {
                InputManager.getMouse().getMousePos().reset();
                this.skipRotationFrame = true;
            }

            if (!this.skipRotationFrame) {
                float deltaX = (float) InputManager.getMouse().getMousePos().getDeltaX();
                float deltaY = (float) InputManager.getMouse().getMousePos().getDeltaY();

                if (isYAxisInverted == true) {
                    deltaY = -1 * deltaY;
                }

                this.setRotationY(this.getRotationY() + deltaX * MOUSE_SENSITIVITY * 0.01f);
                this.setRotationX(this.getRotationX() + deltaY * MOUSE_SENSITIVITY * 0.01f);
            }
        } else {
            this.getWindow().enableCursor();
        }

        if (Math.toDegrees(this.getRotationX()) < -89) {
            this.setRotationX(Math.toRadians(-89));
        }

        if (Math.toDegrees(this.getRotationX()) > 89) {
            this.setRotationX(Math.toRadians(89));
        }

        this.mouseLockedLastFrame = IsMouseDown;
        this.skipRotationFrame = false;
        InputManager.getMouse().getMousePos().resetDelta();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPositionX() {
        return this.getPosition().x;
    }

    public void setPositionX(float x) {
        this.getPosition().x = x;
    }

    public float getPositionY() {
        return this.getPosition().y;
    }

    public void setPositionY(float y) {
        this.getPosition().y = y;
    }

    public float getPositionZ() {
        return this.getPosition().z;
    }

    public void setPositionZ(float z) {
        this.getPosition().z = z;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getRotationX() {
        return this.getRotation().x;
    }

    public void setRotationX(float x) {
        this.getRotation().x = x;
    }

    public float getRotationY() {
        return this.getRotation().y;
    }

    public void setRotationY(float y) {
        this.getRotation().y = y;
    }

    public float getRotationZ() {
        return this.getRotation().z;
    }

    public void setRotationZ(float z) {
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