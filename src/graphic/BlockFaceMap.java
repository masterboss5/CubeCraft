package graphic;

import java.util.Arrays;

public record BlockFaceMap(
        float[] top,
        float[] bottom,
        float[] front,
        float[] back,
        float[] left,
        float[] right
) {
    public static BlockFaceMap create(float[] points) {
        if (points.length != 72 && points.length != 0) {
            throw new IllegalArgumentException("Expected 6 faces × 4 vertices × 3 floats = 72 floats");
        }

        if (points.length == 0) {
            return new BlockFaceMap(new float[] {}, new float[] {}, new float[] {}, new float[] {}, new float[] {}, new float[] {});
        }

        int stride = 12;

        float[] top = Arrays.copyOfRange(points, 0 * stride, 1 * stride);
        float[] bottom = Arrays.copyOfRange(points, 1 * stride, 2 * stride);
        float[] front = Arrays.copyOfRange(points, 2 * stride, 3 * stride);
        float[] back = Arrays.copyOfRange(points, 3 * stride, 4 * stride);
        float[] left = Arrays.copyOfRange(points, 4 * stride, 5 * stride);
        float[] right = Arrays.copyOfRange(points, 5 * stride, 6 * stride);

        return new BlockFaceMap(top, bottom, front, back, left, right);
    }

    public BlockFaceMap(float[] top, float[] bottom, float[] front, float[] back, float[] left, float[] right) {
        this.top = top;
        this.bottom = bottom;
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
    }

    private static float[] centerPointsTo0(float[] points) {
        float[] copy = Arrays.copyOf(points, points.length);

        for (int i = 0; i < copy.length; i += 3) {
            copy[i] = copy[i] + 0.5f;
            copy[i + 1] = copy[i + 1] + 0.5f;
            copy[i + 2] = copy[i + 2] + 0.5f;
        }

        return copy;
    }

    public float[] getTopFaceCenteredTo0() {
        return centerPointsTo0(this.top());
    }

    public float[] getBottomFaceCenteredTo0() {
        return centerPointsTo0(this.bottom());
    }

    public float[] getFrontFaceCenteredTo0() {
        return centerPointsTo0(this.front());
    }

    public float[] getBackFaceCenteredTo0() {
        return centerPointsTo0(this.back());
    }

    public float[] getLeftFaceCenteredTo0() {
        return centerPointsTo0(this.left());
    }

    public float[] getRightFaceCenteredTo0() {
        return centerPointsTo0(this.right());
    }
}