package graphic;

public class UVCoordinates {
    // Top-left corner (0,1)
    float topLeftX = 0f;
    float topLeftY = 1f;

    // Top-right corner (1,1)
    float topRightX = 1f;
    float topRightY = 1f;

    // Bottom-left corner (0,0)
    float bottomLeftX = 0f;
    float bottomLeftY = 0f;

    // Bottom-right corner (1,0)
    float bottomRightX = 1.0f;
    float bottomRightY = 0.0f;

    public UVCoordinates() {
    }

    public UVCoordinates(float topLeftX, float topLeftY, float topRightX, float topRightY, float bottomLeftX, float bottomLeftY, float bottomRightX, float bottomRightY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.topRightX = topRightX;
        this.topRightY = topRightY;
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.bottomRightX = bottomRightX;
        this.bottomRightY = bottomRightY;
    }

    public float[] toFloatArray() {
        return new float[] {
                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomLeftX, bottomLeftY,
                bottomRightX, bottomRightY,

                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomLeftX, bottomLeftY,
                bottomRightX, bottomRightY,

                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomLeftX, bottomLeftY,
                bottomRightX, bottomRightY,

                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomLeftX, bottomLeftY,
                bottomRightX, bottomRightY,

                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomLeftX, bottomLeftY,
                bottomRightX, bottomRightY,

                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomLeftX, bottomLeftY,
                bottomRightX, bottomRightY
        };
    }

    public float getTopLeftX() {
        return topLeftX;
    }

    public void setTopLeftX(float topLeftX) {
        this.topLeftX = topLeftX;
    }

    public float getTopRightX() {
        return topRightX;
    }

    public void setTopRightX(float topRightX) {
        this.topRightX = topRightX;
    }

    public float getBottomLeftX() {
        return bottomLeftX;
    }

    public void setBottomLeftX(float bottomLeftX) {
        this.bottomLeftX = bottomLeftX;
    }

    public float getBottomRightX() {
        return bottomRightX;
    }

    public void setBottomRightX(float bottomRightX) {
        this.bottomRightX = bottomRightX;
    }

    public float getTopLeftY() {
        return topLeftY;
    }

    public void setTopLeftY(float topLeftY) {
        this.topLeftY = topLeftY;
    }

    public float getTopRightY() {
        return topRightY;
    }

    public void setTopRightY(float topRightY) {
        this.topRightY = topRightY;
    }

    public float getBottomLeftY() {
        return bottomLeftY;
    }

    public void setBottomLeftY(float bottomLeftY) {
        this.bottomLeftY = bottomLeftY;
    }

    public float getBottomRightY() {
        return bottomRightY;
    }

    public void setBottomRightY(float bottomRightY) {
        this.bottomRightY = bottomRightY;
    }
}
