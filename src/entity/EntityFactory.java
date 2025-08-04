package entity;

import world.World;

public interface EntityFactory<T> {
    T create(double x, double y, double z, World world);
}
