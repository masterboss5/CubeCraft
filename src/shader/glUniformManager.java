package shader;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL46;
import util.MathUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

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

    default void setUniform1fv(String location, FloatBuffer f1) {
        glUniform1fv(getLocation(location), f1);
    }

    default void setUniform2fv(String location, FloatBuffer f1) {
        glUniform2fv(getLocation(location), f1);
    }

    default void setUniform3fv(String location, FloatBuffer f1) {
        glUniform3fv(getLocation(location), f1);
    }

    default void setUniform4fv(String location, FloatBuffer f1) {
        glUniform4fv(getLocation(location), f1);
    }

    default void setUniform1iv(String location, IntBuffer i1) {
        GL20.glUniform1iv(getLocation(location), i1);
    }

    default void setUniform2iv(String location, IntBuffer i1) {
        GL20.glUniform2iv(getLocation(location), i1);
    }

    default void setUniform3iv(String location, IntBuffer i1) {
        GL20.glUniform3iv(getLocation(location), i1);
    }

    default void setUniform4iv(String location, IntBuffer i1) {
        GL20.glUniform4iv(getLocation(location), i1);
    }

    default void setUniformMatrix2fv(String location, boolean transpose, FloatBuffer f1) {
        glUniformMatrix2fv(getLocation(location), transpose, f1);
    }

    default void setUniformMatrix3fv(String location, boolean transpose, FloatBuffer f1) {
        glUniformMatrix3fv(getLocation(location), transpose, f1);
    }

    default void setUniformMatrix4fv(String location, boolean transpose, FloatBuffer f1) {
        glUniformMatrix4fv(getLocation(location), transpose, f1);
    }

    default void setUniformMatrix4f(String location, Matrix4f m1) {
        GL46.glUniformMatrix4fv(getLocation(location), false, MathUtils.matrix4fToFloatArray(m1));
    }
}
