package io;

public class MousePosition {
    double x;
    double y;
    double prevX;
    double prevY;
    double deltaX;
    double deltaY;

    public double getX() {
        return x;
    }

    protected void setX(double x) {
        this.x = x;
    }

    public double getDeltaY() {
        return deltaY;
    }

    protected void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public double getY() {
        return y;
    }

    protected void setY(double y) {
        this.y = y;
    }

    public double getDeltaX() {
        return deltaX;
    }

    protected void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getPrevX() {
        return prevX;
    }

    public void setPrevX(double prevX) {
        this.prevX = prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    public void setPrevY(double prevY) {
        this.prevY = prevY;
    }

    public void resetDelta() {
        this.setDeltaX(0);
        this.setDeltaY(0);
    }
}
