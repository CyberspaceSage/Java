package ru.stqa.pft.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DistanceTests {
    @Test
    public void testPoint() {
        Point sq = new Point (6,6,7,2);
        Assert.assertEquals(sq.distance(), 4.123105625617661);
    }
    @Test
    public void testSame() {
        Point sq = new Point (7,7,7,7);
        Assert.assertEquals(sq.distance(), 0);

    }
    @Test
    public void testFractionalValues() {
        Point sq = new Point (1.3,5.7,6.2,2.7);
        Assert.assertEquals(sq.distance(),  5.745432968889291);
    }
}