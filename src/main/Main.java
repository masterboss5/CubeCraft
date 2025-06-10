package main;

import block.GrassBlock;
import graphic.Camera;
import graphic.Models;
import io.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import render.RenderSystem;

public class Main {
    public static Main INSTANCE;
    private Mouse mouse;
    public Window window;
    private Camera camera;

    GrassBlock grassBlock;

    public static void main(String[] args) {
        INSTANCE = new Main();
        INSTANCE.start();
    }

    private void render() {
        RenderSystem.render(this.grassBlock);
        GLFW.glfwSwapBuffers(this.window.getWindowAddress());
    }

    private void update() {
        this.window.updateWindow();
        this.camera.tick();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1F);
        GLFW.glfwPollEvents();

        System.out.println(InputManager.getMouse().getMousePos().getDeltaX());
    }

    private void initInputs() {
        InputManager.registerMouseInput(new Mouse(this.window));
        InputManager.registerKeyboardInput(new Keyboard(this.window));
        InputManager.registerWindowInput(new WindowInput(this.window));
    }

    private void init() {
        this.window = new Window("CubeCraft3D", 600, 800, 0, 0);
        this.window.init();

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        this.initInputs();
        this.grassBlock = new GrassBlock();
        this.camera = new Camera(this.window, this.mouse);
        RenderSystem.init(this.window, this.camera);
        Models.loadModels();
    }

    private void start() {
        this.init();

        while (!this.window.shouldClose()) {
            this.update();
            this.render();

            if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_F11)) {
                this.window.setFullscreen(!this.window.isFullscreen());
                System.out.println("F11");
            };
        }

        this.cleanUp();
    }

    private void cleanUp() {
        InputManager.cleanUp();

        GLFW.glfwWindowShouldClose(this.window.getWindowAddress());
        GLFW.glfwDestroyWindow(this.window.getWindowAddress());
        GLFW.glfwTerminate();
    }
}
