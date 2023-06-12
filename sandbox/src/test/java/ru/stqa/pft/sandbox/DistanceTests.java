package ru.stqa.pft.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DistanceTests {
    @Test
    public void testPoint() {
        Point p1 = new Point (5,9);
        Point p2 = new Point (4,3);
        Assert.assertEquals(p1.distance(p2),6.082762530298219);
    }
    @Test
    public void testSame() {
        Point p1 = new Point (6,6);
        Point p2 = new Point (6,6);
        Assert.assertEquals(p1.distance(p2),0);

    }
    @Test
    public void testFractionalValues() {
        Point p1 = new Point (6.2,2.7);
        Point p2 = new Point (3.7,7.8);
        Assert.assertEquals(p1.distance(p2),5.679788728465171);
    }
}