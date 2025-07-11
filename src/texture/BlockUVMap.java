package texture;

import java.util.Arrays;

public record BlockUVMap(
        float[] topUV,
        float[] bottomUV,
        float[] frontUV,
        float[] backUV,
        float[] leftUV,
        float[] rightUV
) {
    public static BlockUVMap fromPacked(float[] uvCoordinates) {
        if (uvCoordinates.length != 48 && uvCoordinates.length != 0) {
            throw new IllegalArgumentException("block UV must have 48 floats, 24 points");
        }

        float[] emptyUV = new float[] {0, 0, 0, 0, 0, 0, 0, 0};

        if (uvCoordinates.length == 0) {
            return new BlockUVMap(
                    emptyUV,
                    emptyUV,
                    emptyUV,
                    emptyUV,
                    emptyUV,
                    emptyUV
            );
        }

        int pointsPerFace = 8;

        float[] topUV = Arrays.copyOfRange(uvCoordinates, 0 * pointsPerFace, 1 * pointsPerFace);
        float[] bottomUV = Arrays.copyOfRange(uvCoordinates, 1 * pointsPerFace, 2 * pointsPerFace);
        float[] frontUV = Arrays.copyOfRange(uvCoordinates, 2 * pointsPerFace, 3 * pointsPerFace);
        float[] backUV = Arrays.copyOfRange(uvCoordinates, 3 * pointsPerFace, 4 * pointsPerFace);
        float[] leftUV = Arrays.copyOfRange(uvCoordinates, 4 * pointsPerFace, 5 * pointsPerFace);
        float[] rightUV = Arrays.copyOfRange(uvCoordinates, 5 * pointsPerFace, 6 * pointsPerFace);

        return new BlockUVMap(topUV, bottomUV, frontUV, backUV, leftUV, rightUV);
    }
}
