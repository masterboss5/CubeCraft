package entity;

import world.World;

public class CubeEntity extends Entity {
    public CubeEntity(double x, double y, double z, World world) {
        super(x, y, z, EntityType.CUBE_ENTITY, world);
    }

    @Override
    public boolean hasTicks() {
        return false;
    }
}
