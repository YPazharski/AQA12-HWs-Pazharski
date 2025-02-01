package by.PazharskiYury.lesson16.parallelismus;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ParallelClass1 {

    @Test
    public void parallel1() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel2() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel3() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel4() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel5() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

}