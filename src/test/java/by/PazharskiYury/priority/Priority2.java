package by.PazharskiYury.priority;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Priority2 {

    @Test(dependsOnMethods = "b", alwaysRun = true)
    public void a() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "c", alwaysRun = true)
    public void b() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "d", alwaysRun = true)
    public void c() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "e", alwaysRun = true)
    public void d() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "f", alwaysRun = true)
    public void e() {
        assertTrue(true);
    }

    @Test(dependsOnMethods = "g", alwaysRun = true)
    public void f() {
        assertTrue(true);
    }

    @Test(alwaysRun = true)
    public void g() {
        assertTrue(true);
    }

}