package com.petclinic.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("PetClinic") || title.contains("Spring Framework PetClinic"));
    }

}
