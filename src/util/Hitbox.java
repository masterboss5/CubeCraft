package util;

public class Hitbox {
    private final Box localBox;
    private final Box worldBox;

    public Hitbox(Box localBox) {
        this.localBox = localBox;
        this.worldBox = Box.NULL_BOX.get();
    }

    public void moveTo(double x, double y, double z) {
        this.worldBox.setPosition(
                this.localBox.getMinX() + x, this.localBox.getMinY() + y, this.localBox.getMinZ() + z,
                this.localBox.getMaxX() + x, this.localBox.getMaxY() + y, this.localBox.getMaxZ() + z
        );
    }

    public boolean intersects(Hitbox other) {
        return this.worldBox.intersects(other.getWorldBox());
    }

    public Box getWorldBox() {
        return worldBox;
    }

    public Box getLocalBox() {
        return localBox;
    }
}
