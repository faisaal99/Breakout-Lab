package breakout.collision;

// Used for collision detection
public class AABB {

    private double x, y;
    private final double maxX, maxY;

    public AABB(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        maxX = x + width;
        maxY = y + height;
    }

    public boolean isColliding(AABB obj) {
        return (x <= obj.maxX && maxX >= obj.x) &&
               (y <= obj.maxY && maxY >= obj.y);
    }
}
