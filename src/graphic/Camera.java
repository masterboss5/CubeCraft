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
        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_W)) {
            this.position.z = this.position.z - 0.1F;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_S)) {
            this.position.z = this.position.z + 0.1F;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_D)) {
            this.position.x = this.position.x + 0.1F;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_A)) {
            this.position.x = this.position.x - 0.1F;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_UP)) {
            this.position.y = this.position.y + 0.1F;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            this.position.y = this.position.y - 0.1F;
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            this.rotation.x = this.rotation.x - org.joml.Math.toRadians(90);
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            this.rotation.x = this.rotation.x + Math.toRadians(90);
        }

//        if (InputManager.getMouse().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
//            this.rotation.y += ((float) (InputManager.getMouse().getMousePos().getDeltaY() * SENSITIVITY));
//            this.rotation.x -= -((float) (InputManager.getMouse().getMousePos().getDeltaX() * SENSITIVITY));
//        }

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
