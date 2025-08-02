package util;

import java.util.stream.IntStream;

public class ArrayUtils {
    public static Float[] convertPrimitiveFloatToWrapperFloat(float[] floats) {
        if (floats == null) return null;

        return IntStream.range(0, floats.length)
                .mapToObj(i -> floats[i])
                .toArray(Float[]::new);
    }

    public static float[] convertWrapperFloatToPrimitiveFloat(Float[] floats) {
        if (floats == null) return null;

        float[] result = new float[floats.length];
        for (int i = 0; i < floats.length; i++) {
            if (floats[i] != null) {
                result[i] = floats[i];
            } else {
                result[i] = 0.0f;
            }
        }

        return result;
    }

    public static float[] convertListFloatToPrimitiveFloatArray(java.util.List<Float> floatList) {
        if (floatList == null) return null;

        float[] result = new float[floatList.size()];
        for (int i = 0; i < floatList.size(); i++) {
            Float value = floatList.get(i);
            result[i] = (value != null) ? value : 0.0f;
        }

        return result;
    }
}
