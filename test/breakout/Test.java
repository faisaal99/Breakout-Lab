package breakout;

import breakout.model.Ball;
import breakout.model.Brick;

import static java.lang.System.out;

/**
 * Here you should write your tests (not much logic in this lab though ...)
 *
 * Right click and run ...
 */
public class Test {

    public static void main(String[] args) {
        new Test().test();
    }

    void test() {

        Ball b = new Ball(5,5); // Create object (just an example)

        Brick br = new Brick(4, 5);
        boolean x = b.getAABB().isColliding(br.getAABB());
        out.println("isColliding: " + x);

        //out.println( b.doIt() == 5);     // Call methods and check
        //out.println( b.doOther() == 7);

        // Create other object  ...
        // .. call methods to test.



    }


}
