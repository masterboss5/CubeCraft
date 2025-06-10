package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

public class WindowInput implements InputHandler {
    private Window window;
    private long windowAddress;
    private GLFWWindowSizeCallback windowResizeCallback;

    public WindowInput(Window window) {
        this.window = window;
        this.windowAddress = window.getWindowAddress();

        this.createCallbacks();
        this.bindCallbacks();
    }

    @Override
    public void createCallbacks() {
        this.windowResizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                WindowInput.this.window.setWidth(width);
                WindowInput.this.window.setHeight(height);
            }
        };
    }

    @Override
    public void bindCallbacks() {
        GLFW.glfwSetWindowSizeCallback(this.windowAddress, this.windowResizeCallback);
    }

    @Override
    public void cleanup() {
        this.windowResizeCallback.free();
    }
}
