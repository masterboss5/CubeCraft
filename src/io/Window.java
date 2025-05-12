package io;

import exception.WindowInitializationException;
import exception.WindowNotCreatedException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Window {
    private int x;
    private int y;
    private int width;
    private int height;
    private String title;
    private long windowAddress;
    private boolean viewportNeedsAdjusting;
    private boolean fullscreen = false;

    public Window(String title, int height, int width, int y, int x) {
        this.title = title;
        this.height = height;
        this.width = width;
        this.y = y;
        this.x = x;
    }

    public void updateWindow() {
        if (this.isViewportNeedsAdjusting()) {
            this.setViewportNeedsAdjusting(false);

            if (this.fullscreen) {
                GLFW.glfwSetWindowMonitor(this.windowAddress, GLFW.glfwGetPrimaryMonitor(), 0, 0, this.width, this.height, 180);
            } else {
                GLFW.glfwSetWindowMonitor(this.windowAddress, 0, this.x, this.y, this.width, this.height, 180);
            }

            GL11.glViewport(0, 0, this.getWidth(), this.getHeight());
        }
    }

    public void init() throws WindowInitializationException, WindowNotCreatedException {
        if (!GLFW.glfwInit()) {
            throw new WindowInitializationException();
        }

        this.windowAddress = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);

        if (this.windowAddress == 0) {
            throw new WindowNotCreatedException();
        }

        GLFW.glfwMakeContextCurrent(this.windowAddress);
        GLFW.glfwShowWindow(this.windowAddress);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.windowAddress);
    }

    public void cleanUp() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public long getWindowAddress() {
        return windowAddress;
    }

    public void setWindowAddress(long windowAddress) {
        this.windowAddress = windowAddress;
    }

    public boolean isViewportNeedsAdjusting() {
        return viewportNeedsAdjusting;
    }

    public void setViewportNeedsAdjusting(boolean viewportNeedsAdjusting) {
        this.viewportNeedsAdjusting = viewportNeedsAdjusting;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        this.setViewportNeedsAdjusting(true);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        GLFW.glfwSetWindowTitle(this.getWindowAddress(), title);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        GL11.glViewport(0, 0, this.getWidth(), this.getHeight());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        GL11.glViewport(0, 0, this.getWidth(), this.getHeight());
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
