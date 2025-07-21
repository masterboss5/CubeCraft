package main;

import block.*;
import gl.glDebugger;
import graphic.Camera;
import graphic.Models;
import io.*;
import json.TextureArrays;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import render.RenderSystem;
import shader.ShaderPrograms;
import texture.TextureArray;
import texture.Textures;
import util.collection.PaletteContainer;
import world.World;

public class Main {
    public static Main INSTANCE;
    private Mouse mouse;
    public Window window;
    public static Camera camera;
    private World world;

    public static void main(String[] args) {
        INSTANCE = new Main();
        INSTANCE.start();

        //Testing
        PaletteContainer<Block> blockPaletteContainer = new PaletteContainer<>();
//        int x = blockPaletteContainer.palette.index(new GrassBlock(new BlockPosition()));
        System.out.println(blockPaletteContainer.palette.index(new GrassBlock(new BlockPosition())));
        System.out.println(blockPaletteContainer.palette.index(new DirtBlock(new BlockPosition())));
        System.out.println(blockPaletteContainer.palette.getClass());
        System.out.println(blockPaletteContainer.palette.index(new OakPlanksBlock(new BlockPosition())));
        System.out.println(blockPaletteContainer.palette.index(new CobblestoneBlock(new BlockPosition())));
//        System.out.println(blockPaletteContainer.palette.index(new AirBlock(new BlockPosition())));
    }

    private void render() {
        TextureArray temp = TextureArrays.BLOCK_TEXTURE_ARRAY;
        this.world.renderWorld();
        this.world.tickWorld();
        GLFW.glfwSwapBuffers(this.window.getPointer());
    }

    private void update() {
        this.window.updateWindow();
        this.camera.tick();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.53f, 0.81f, 0.92f, 1.0f);
        GLFW.glfwPollEvents();
    }

    private void initInputs() {
        InputManager.registerMouseInput(new Mouse(this.window));
        InputManager.registerKeyboardInput(new Keyboard(this.window));
        InputManager.registerWindowInput(new WindowInput(this.window));
    }

    private void init() {
        this.window = new Window("CubeCraft3D", 1200, 2000, 0, 0);
        this.window.init();

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        glDebugger._glCullFace();
        glDebugger._glPolygonMode();

        this.initInputs();
        ShaderPrograms.loadShaderPrograms();
        Textures.loadTextures();
        Models.loadModels();
        TextureArrays.loadTextureArrays();
        this.world = new World();
        this.world.loadWorld();
        this.camera = new Camera(this.window);
        RenderSystem.init(this.window, this.camera);

        /*TextureManager.createTextureArrays(1920, 1920, 4);
        TextureManager.upload(new TextureData("C:\\Users\\Armen\\Desktop\\CubeCraft\\resources\\textures\\grass.png"), 0);*/

        glDebugger.init();
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

        GLFW.glfwWindowShouldClose(this.window.getPointer());
        GLFW.glfwDestroyWindow(this.window.getPointer());
        GLFW.glfwTerminate();
    }
}
