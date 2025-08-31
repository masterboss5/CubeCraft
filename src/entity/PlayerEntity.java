package entity;

import main.Main;
import world.World;

public class PlayerEntity extends Entity {
    public PlayerEntity(double x, double y, double z, World world) {
        super(x, y, z, EntityType.PLAYER_ENTITY, world);
    }

    @Override
    public boolean hasTicks() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        this.setPosition(
                0,
                25,
                0
        );
/*        this.setRotation(
                Main.camera.getRotationX(),
                Main.camera.getRotationY(),
                Main.camera.getRotationZ()
        );*/
    }
}
