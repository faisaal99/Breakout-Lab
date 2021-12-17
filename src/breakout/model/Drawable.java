package breakout.model;

public abstract class Drawable implements IPositionable {

    protected double x, y;
    protected double dx, dy;
    final double width, height;

    public Drawable(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        dx = 0;
        dy = 0;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    // region GETTERS AND SETTERS

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }

    public double getDx() { return dx; }
    public double getDy() { return dy; }

    // endregion

    public void move() {
        x += dx;
        y += dy;
    }
}
