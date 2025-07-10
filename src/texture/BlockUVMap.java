package texture;

public record BlockUVMap(
        float[] topUV,
        float[] bottomUV,
        float[] frontUV,
        float[] backUV,
        float[] leftUV,
        float[] rightUV
) {}
