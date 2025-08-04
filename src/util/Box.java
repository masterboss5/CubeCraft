package util;

import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.Objects;

public class Box {
    private final double minX;
    private final double minY;
    private final double minZ;
    private final double maxX;
    private final double maxY;
    private final double maxZ;

    private static boolean validatePoints(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (minX >= maxX) {
            return false;
        }

        if (minY >= maxY) {
            return false;
        }

        if (minZ >= maxZ) {
            return false;
        }

        return true;
    }

    public Box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (!validatePoints(minX, minY, minZ, maxX, maxY, maxZ)) {
            throw new IllegalArgumentException("box points are incorrect");
        }

        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

    }

    public double getArea() {
        double width = this.maxX - this.minX;
        double height = this.maxY - this.minY;
        double length = this.maxZ - this.minZ;

        return width * height * length;
    }

    public Box offset(Vector3f vector3f) {
        return new Box(
                this.minX + vector3f.x,
                this.minY + vector3f.y,
                this.minZ + vector3f.z,
                this.maxX + vector3f.x,
                this.maxY + vector3f.y,
                this.maxZ + vector3f.z
        );
    }

    public Vector3d getCenter() {
        double x = this.maxX - this.minX;
        double y = this.maxY - this.minY;
        double z = this.maxZ - this.minZ;

        return new Vector3d(
                x * 0.5 + this.minX,
                y * 0.5 + this.minY,
                z * 0.5 + this.minZ
        );
    }

    public boolean collides(Box otherBox) {
        return this.maxX > otherBox.minX && this.minX < otherBox.maxX &&
                this.maxY > otherBox.minY && this.minY < otherBox.maxY &&
                this.maxZ > otherBox.minZ && this.minZ < otherBox.maxZ;
    }

    public boolean contains(Vector3d point) {
        return point.x >= minX && point.x <= maxX &&
                point.y >= minY && point.y <= maxY &&
                point.z >= minZ && point.z <= maxZ;
    }

    public static boolean collidesWith(Box[] otherBoxes) {
        for (int i = 0; i < otherBoxes.length; i++) {
            for (int j = i + 1; j < otherBoxes.length; j++) {
                if (otherBoxes[i].collides(otherBoxes[j])) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Double.compare(this.minX, box.minX) == 0 && Double.compare(this.minY, box.minY) == 0 && Double.compare(this.minZ, box.minZ) == 0 && Double.compare(this.maxX, box.maxX) == 0 && Double.compare(this.maxY, box.maxY) == 0 && Double.compare(this.maxZ, box.maxZ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMinZ() {
        return minZ;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMaxZ() {
        return maxZ;
    }
}
