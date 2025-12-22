package com.petclinic.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VetsPageTest extends BaseTest {

    @Test
    public void testVetsPage() {
        driver.get("http://localhost:8080/vets");
        Assert.assertTrue(driver.getPageSource().contains("Veterinarians"));
    }

}
