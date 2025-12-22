package com.petclinic.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindOwnerTest extends BaseTest {

    @Test
    public void testFindOwnersPage() {
        driver.get("http://localhost:8080/owners/find");
        Assert.assertTrue(driver.getPageSource().contains("Find Owners"));
    }

}
