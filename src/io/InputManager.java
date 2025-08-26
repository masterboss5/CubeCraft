package io;

import exception.InputAlreadyInitializedException;

public class InputManager {
    private static final boolean[] registered = {false, false, false};
    private static Mouse mouse;
    private static Keyboard keyboard;
    private static WindowInput windowInput;

    public static void registerMouseInput(Mouse mouseInput) {
        if (registered[0]) throw new InputAlreadyInitializedException("Mouse");
        mouse = mouseInput;
        registered[0] = true;
    }

    public static void registerKeyboardInput(Keyboard keyboardInput) {
        if (registered[1]) throw new InputAlreadyInitializedException("Keyboard");
        keyboard = keyboardInput;
        registered[1] = true;
    }

    public static void registerWindowInput(WindowInput _windowInput) {
        if (registered[2]) throw new InputAlreadyInitializedException("Window");
        windowInput = _windowInput;
        registered[2] = true;
    }

    public static void cleanUp() {
        mouse.cleanup();
        keyboard.cleanup();
    }

    public static Mouse getMouse() {
        return mouse;
    }

    public static Keyboard getKeyboard() {
        return keyboard;
    }
}
