package com.petclinic.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OwnersPageTest extends BaseTest {

    @Test
    public void testOwnersPage() {
        driver.findElement(By.linkText("Owners")).click();
        Assert.assertTrue(driver.getPageSource().contains("Owners"));
    }
}
