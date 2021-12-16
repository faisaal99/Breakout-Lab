package breakout.collision;

import breakout.model.IPositionable;

// Used for collision detection
public class AABB {

    private AABB() {
    }

    // Takes two IPositionables and checks if they collide
    public static boolean isCollidingWithWall(IPositionable p1, IPositionable p2) {
        double p1MinX = p1.getX();
        double p1MinY = p1.getY();
        double p2MinX = p2.getX();
        double p2MinY = p2.getY();

        double p1Width = p1.getWidth();
        double p1Height = p1.getHeight();
        double p2Width = p2.getWidth();
        double p2Height = p2.getHeight();

        double p1MaxX = p1MinX + p1Width;
        double p1MaxY = p1MinY + p1Height;
        double p2MaxX = p2MinX + p2Width;
        double p2MaxY = p2MinY + p2Height;

        return (p1MinX <= p2MaxX && p1MaxX >= p2MinX) &&
               (p1MinY <= p2MaxY && p1MaxY >= p2MinY);
    }
}
