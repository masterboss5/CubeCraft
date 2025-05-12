package shader;

import org.lwjgl.opengl.GL46;

public interface glUniformManager {
    int getProgramID();

    default int getLocation(String location) {
        return GL46.glGetUniformLocation(this.getProgramID(), location);
    }

    default void setUniform1f(String location, float f1) {
        GL46.glUniform1f(getLocation(location), f1);
    }

    default void setUniform2f(String location, float f1, float f2) {
        GL46.glUniform2f(getLocation(location), f1, f2);
    }

    default void setUniform3f(String location, float f1, float f2, float f3) {
        GL46.glUniform3f(getLocation(location), f1, f2, f3);
    }

    default void setUniform4f(String location, float f1, float f2, float f3, float f4) {
        GL46.glUniform4f(getLocation(location), f1, f2, f3, f4);
    }

    default void setUniform1i(String location, int i1) {
        GL46.glUniform1i(getLocation(location), i1);
    }

    default void setUniform2i(String location, int i1, int i2) {
        GL46.glUniform2i(getLocation(location), i1, i2);
    }

    default void setUniform3i( String location, int i1, int i2, int i3) {
        GL46.glUniform3i(getLocation(location), i1, i2, i3);
    }

    default void setUniform4i(String location, int i1, int i2, int i3, int i4) {
        GL46.glUniform4i(getLocation(location), i1, i2, i3, i4);
    }
}
