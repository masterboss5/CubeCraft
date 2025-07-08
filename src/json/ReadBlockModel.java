package json;

public record ReadBlockModel(float[] vertices, int[] indices, float[] uv, String shader_program, String texture, String top, String bottom, String front, String back, String left, String right) {
}
