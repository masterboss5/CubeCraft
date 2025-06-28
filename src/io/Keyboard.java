package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard implements InputHandler {
    public static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private Window window;
    private final long windowPointer;
    private GLFWKeyCallback keyPress;

    public Keyboard(Window window) {
        this.window = window;
        this.windowPointer = window.getPointer();

        this.createCallbacks();
        this.bindCallbacks();
    }

    @Override
    public void createCallbacks() {
        this.keyPress = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };
    }

    @Override
    public void bindCallbacks() {
        GLFW.glfwSetKeyCallback(this.windowPointer, this.keyPress);
    }

    @Override
    public void cleanup() {
        this.keyPress.free();
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }
}
