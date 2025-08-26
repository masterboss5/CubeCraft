package io;

import exception.WindowInitializationException;
import exception.WindowNotCreatedException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import render.RenderSystem;

public class Window {
    private int x;
    private int y;
    private int width;
    private int height;
    private String title;
    private long pointer;
    private boolean viewportNeedsAdjusting;
    private boolean fullscreen = false;
    private boolean cursorEnabled = true;

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

            if (this.isFullscreen()) {
                GLFW.glfwSetWindowMonitor(this.getPointer(), GLFW.glfwGetPrimaryMonitor(), 0, 0, this.getWidth(), this.getHeight(), 180);
            } else {
                GLFW.glfwSetWindowMonitor(this.getPointer(), 0, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 180);
            }

            GL11.glViewport(0, 0, this.getWidth(), this.getHeight());
        }
    }

    public void init() throws WindowInitializationException, WindowNotCreatedException {
        if (!GLFW.glfwInit()) {
            throw new WindowInitializationException();
        }

        this.setPointer(GLFW.glfwCreateWindow(this.getWidth(), this.getHeight(), this.getTitle(), 0, 0));

        if (this.getPointer() == 0) {
            throw new WindowNotCreatedException();
        }

        GLFW.glfwMakeContextCurrent(this.getPointer());
        GLFW.glfwShowWindow(this.getPointer());
        GLFW.glfwSwapInterval(0);
        GLFW.glfwSetInputMode(this.getPointer(), GLFW.GLFW_RAW_MOUSE_MOTION, GLFW.GLFW_TRUE);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.getPointer());
    }

    //TODO add cleanup method
    public void cleanUp() {
    }

    public long getPointer() {
        return pointer;
    }

    public void setPointer(long pointer) {
        this.pointer = pointer;
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

    public void disableCursor() {
        this.setCursorEnabled(false);
        GLFW.glfwSetInputMode(this.getPointer(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    }

    public void enableCursor() {
        this.setCursorEnabled(true);
        GLFW.glfwSetInputMode(this.getPointer(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        GLFW.glfwSetWindowTitle(this.getPointer(), title);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        RenderSystem.createProjectionMatrix();
        GL11.glViewport(0, 0, this.getWidth(), this.getHeight());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        RenderSystem.createProjectionMatrix();
        GL11.glViewport(0, 0, this.getWidth(), this.getHeight());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCursorEnabled() {
        return cursorEnabled;
    }

    private void setCursorEnabled(boolean cursorEnabled) {
        this.cursorEnabled = cursorEnabled;
    }
}
