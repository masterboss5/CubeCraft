package graphic;

public class UVCoordinates {
    float topLeftX = 0.0f;
    float topRightX = 1.0f;
    float bottomLeftX = 0.0f;
    float bottomRightX = 1.0f;

    float topLeftY = 1.0f;
    float topRightY = 1.0f;
    float bottomLeftY = 0.0f;
    float bottomRightY = 0.0f;

    public UVCoordinates() {
    }

    public UVCoordinates(float topLeftX, float topRightX, float bottomLeftX, float bottomRightX, float topLeftY, float topRightY, float bottomLeftY, float bottomRightY) {
        this.topLeftX = topLeftX;
        this.topRightX = topRightX;
        this.bottomLeftX = bottomLeftX;
        this.bottomRightX = bottomRightX;
        this.topLeftY = topLeftY;
        this.topRightY = topRightY;
        this.bottomLeftY = bottomLeftY;
        this.bottomRightY = bottomRightY;
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