package com.petclinic.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddOwnerTest extends BaseTest {

    @Test
    public void testAddOwnerPage() {
        driver.get("http://localhost:8080/owners/new");
        Assert.assertTrue(driver.getPageSource().contains("New Owner"));
    }

}
