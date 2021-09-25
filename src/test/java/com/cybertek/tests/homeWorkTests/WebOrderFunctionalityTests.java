package com.cybertek.tests.homeWorkTests;

import com.cybertek.utility.Driver;
import com.cybertek.utility.WebOrderUtility;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class WebOrderFunctionalityTests {

    @Test
    public void testCheckAllButton(){


        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

    }

    @Test
    public void testUncheckAllButton(){


        //TODO: Use the method you created earlier to check all checkboxes
        WebOrderUtility.checkAll();

        //TODO: assert it actual checked all the checkbox (you did this logic in method above so assertTrue this method call)
        assertTrue(WebOrderUtility.checkAll());

        WebOrderUtility.logout();
    }

    @Test
    public void testAllProductName(){

        /**
         * ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR - ERROR -
         */

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Go to view all product page
        WebOrderUtility.selectSideBarTab("view all products");

        //TODO: Verify List of all product header `present`

        WebElement header = Driver.getDriver().findElement(By.xpath("//h2"));
        String headerString = header.getText();

        assertEquals("List of Products",headerString);

        //TODO: get all product name from first column
        WebOrderUtility.getAllProducts();

        //TODO: `verify` they are : `MyMoney`, `FamilyAlbum` , `ScreenSaver`
        assertEquals("[MyMoney, FamilyAlbum, ScreenSaver]",WebOrderUtility.getAllProducts());





    }
}
