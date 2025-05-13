package util;

public class BlockPos {
    private int x;
    private int y;
    private int z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void up() {
        this.up(1);
    }

    public void up(int y) {
        this.y = this.y + y;
    }

    public void down() {
        this.down(1);
    }

    public void down(int y) {
        this.y = this.y - y;
    }

    public void left() {
        this.left(1);
    }

    public void left(int x) {
        this.x = this.x - x;
    }

    public void right() {
        this.right(1);
    }

    public void right(int x) {
        this.x = this.x + x;
    }

    public void set(int x, int y, int z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
