package by.PazharskiYury.priority;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Priority1 {

    @Test(priority = 7)
    public void a() {
        assertTrue(true);
    }

    @Test(priority = 6)
    public void b() {
        assertTrue(true);
    }

    @Test(priority = 5)
    public void c() {
        assertTrue(true);
    }

    @Test(priority = 4)
    public void d() {
        assertTrue(true);
    }

    @Test(priority = 3)
    public void e() {
        assertTrue(true);
    }

    @Test(priority = 2)
    public void f() {
        assertTrue(true);
    }

    @Test(priority = 1)
    public void g() {
        assertTrue(true);
    }

}