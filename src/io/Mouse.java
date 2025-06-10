package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse implements InputHandler {
    private static boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private final MousePos mousePos = new MousePos();
    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseClick;
    public Window window;
    private long windowAddress;

    public Mouse(Window window) {
        this.window = window;
        this.windowAddress = window.getWindowAddress();

        this.createCallbacks();
        this.bindCallbacks();
    }

    @Override
    public void createCallbacks() {
        this.mouseMove = new GLFWCursorPosCallback() {

            @Override
            public void invoke(long window, double xpos, double ypos) {
                double prevX = Mouse.this.getMousePos().getX();
                double prevY = Mouse.this.getMousePos().getY();

                Mouse.this.getMousePos().setDeltaX(xpos - prevX);
                Mouse.this.getMousePos().setDeltaY(ypos - prevY);

                Mouse.this.getMousePos().setX(xpos);
                Mouse.this.getMousePos().setY(ypos);

            }
        };

        this.mouseClick = new GLFWMouseButtonCallback() {

            @Override
            public void invoke(long window, int button, int action, int mods) {
                mouseButtons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
    }

    @Override
    public void bindCallbacks() {
        GLFW.glfwSetCursorPosCallback(this.windowAddress, this.mouseMove);
        GLFW.glfwSetMouseButtonCallback(this.windowAddress, this.mouseClick);
    }

    @Override
    public void cleanup() {
        this.mouseMove.free();
        this.mouseClick.free();
    }

    public MousePos getMousePos() {
        return mousePos;
    }

    public boolean isMouseButtonDown(int button) {
        return mouseButtons[button];
    }
}
