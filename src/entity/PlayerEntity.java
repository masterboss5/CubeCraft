package entity;

import io.InputManager;
import main.Main;
import org.lwjgl.glfw.GLFW;
import world.World;

public class PlayerEntity extends Entity {
    public PlayerEntity(double x, double y, double z, World world) {
        super(x, y, z, EntityType.PLAYER_ENTITY, world);

        this.setPosition(
                15,
                10,
                15
        );
    }

    @Override
    public boolean hasTicks() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_W)) {
            this.setVelocityX(0.02);
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            this.setVelocityY(0.02);
        } else {
            this.setVelocityY(-0.1);
        }

        if (InputManager.getKeyboard().isKeyDown(GLFW.GLFW_KEY_U)) {
            this.setPosition(Main.camera.getPositionX(), Main.camera.getPositionY(), Main.camera.getPositionZ());
        }
    }
}
