package com.cybertek.tests.homeWorkTests;

import com.cybertek.utility.Driver;
import com.cybertek.utility.WebOrderUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WebOrderFunctionalityTests {



    @Test
    public void testCheckAllButton(){


        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Use the method you created earlier to check all checkbox
        WebOrderUtility.checkAll();

        //TODO: Assert it actual checked all the checkbox (you did this logic in method above so assertTrue this method call)
        assertTrue(WebOrderUtility.checkAll());

    }

    @Test
    public void testUncheckAllButton(){

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Use the method you created earlier to uncheck all checkbox
        WebOrderUtility.uncheckAll();

        //TODO: assert it actual unchecked all the checkbox (you did this logic in method above so assertTrue this method call)
        assertTrue(WebOrderUtility.uncheckAll());

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

        List<String> actualList = WebOrderUtility.getAllProducts();
        String[] expectedArray={"MyMoney","FamilyAlbum","ScreenSaver"};

        //TODO: `verify` they are : `MyMoney`, `FamilyAlbum` , `ScreenSaver`

        List<String> expectedList = Arrays.asList(expectedArray);
        assertEquals(expectedList,actualList);

        WebOrderUtility.logout();

    }


    @Test
    public void testProductInformation_Price(){

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Go to view all product page
        WebOrderUtility.selectSideBarTab("order");

        //TODO: Select `MyMoney` from the dropdown
        WebElement dropDownElm = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select selectObj = new Select(dropDownElm);
        selectObj.selectByVisibleText("MyMoney");

        //TODO: `Verify` the `Price per unit:` inputbox value has changed to `100`
        WebElement pricePerUnit = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice"));
        String priceString = pricePerUnit.getAttribute("value");
        assertEquals("100",priceString);

    }


    @Test
    public void testProductInformation_Discount(){

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Go to view all product page
        WebOrderUtility.selectSideBarTab("order");

        //TODO: Select `MyMoney` from the dropdown
        WebElement dropDownElm = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select selectObj = new Select(dropDownElm);
        selectObj.selectByVisibleText("MyMoney");

        //TODO: Enter 10 in `quantity` inputbox
        WebElement quantity = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        quantity.sendKeys(Keys.BACK_SPACE+"10");

        //TODO: Click `calculate`
        Driver.getDriver().findElement(By.xpath("//li/input[@type='submit']")).click();

        //TODO: Verify the `Discount` inputbox value has changed to expected discount
        WebElement discount = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount"));
        String priceString = discount.getAttribute("value");
        assertEquals("8",priceString);

        //TODO: Select `FamilyAlbum` from the dropdown
        selectObj.selectByVisibleText("FamilyAlbum");

        //TODO: Verify the `Discount` input-box value has changed to expected discount
        WebElement discount1 = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount"));
        String priceString1 = discount1.getAttribute("value");
        assertEquals("15",priceString1);

        //TODO: Select `ScreenSaver` from the dropdown
        selectObj.selectByVisibleText("ScreenSaver");

        //TODO: Verify the Discount input-box value has changed to expected discount
        WebElement discount2 = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount"));
        String priceString2 = discount2.getAttribute("value");
        assertEquals("10",priceString2);

        WebOrderUtility.logout();
    }

    @Test
    public void testProductInformation_CalculationLessThan10(){

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Go to view all product page
        WebOrderUtility.selectSideBarTab("order");

        //TODO: Select `MyMoney` from the dropdown
        WebElement dropDownElm = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select selectObj = new Select(dropDownElm);
        selectObj.selectByVisibleText("MyMoney");

        //TODO: Enter 9 in `quantity` inputbox
        WebElement quantity = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        quantity.sendKeys(Keys.BACK_SPACE+"9");

        //TODO: Click `calculate`
        Driver.getDriver().findElement(By.xpath("//li/input[@type='submit']")).click();

        //TODO: Get the actual result from `Total` input-box
        WebElement total = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));
        String totalString = total.getAttribute("value");
        assertEquals("900",totalString);

        //TODO: Verify the `Total` match the expected result `9*100`
        int a = (9*100);
        String nineTimes100 = String.valueOf(a);
        assertEquals(nineTimes100,totalString);

        //TODO: Select `FamilyAlbum` from the dropdown
        selectObj.selectByVisibleText("FamilyAlbum");

        //TODO: Verify the `Total` match the expected result `9*80`
        WebElement total1 = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));
        String totalString1 = total1.getAttribute("value");
        int b = (9*80);
        String nineTimesEighty = String.valueOf(b);
        assertEquals(nineTimesEighty,totalString1);

        //TODO: Select `ScreenSaver` from the dropdown
        selectObj.selectByVisibleText("ScreenSaver");

        //TODO: Verify the `Total` match the expected result `9*20`
        WebElement total2 = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));
        String totalString2 = total2.getAttribute("value");
        int c = (9*20);
        String nineTimesTwenty = String.valueOf(c);
        assertEquals(nineTimesTwenty,totalString2);

    }

    @Test
    public void testProductInformation_Calculation10orGreater(){

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Go to view all product page
        WebOrderUtility.selectSideBarTab("order");

        //TODO: Select `MyMoney` from the dropdown
        WebElement dropDownElm = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select selectObj = new Select(dropDownElm);
        selectObj.selectByVisibleText("MyMoney");

        //TODO: Enter 10 in `quantity` inputbox
        WebElement quantity = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        quantity.sendKeys(Keys.BACK_SPACE+"10");

        //TODO: Click `calculate`
        Driver.getDriver().findElement(By.xpath("//li/input[@type='submit']")).click();

        //TODO: Get the actual result from `Total` input-box
        WebElement total = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));
        String totalString = total.getAttribute("value");
        assertEquals("920",totalString);

        //TODO: `Verify` the `Total` match the expected result `(1-0.08) * 10*100`
        int a = 1;
        double b = 0.08;
        double c = a-b;
        int d = (10*100);
        double e = c * d;

        int intValue = (int)e;
        String equation = String.valueOf(intValue);
        assertEquals(equation,totalString);

        //TODO: Select `FamilyAlbum` from the dropdown
        selectObj.selectByVisibleText("FamilyAlbum");

        //TODO: `Verify` the `Total` match the expected result `(1-0.15) * 10*80`
        String totalString1 = total.getAttribute("value");
        int a1 = 1;
        double b1 = 0.15;
        double c1 = a1 - b1;
        int d1 = (10*80);
        double e1 = c1 * d1;

        int intValue1 = (int)e1;
        String equation1 = String.valueOf(intValue1);
        assertEquals(equation1,totalString1);

        //TODO: Select `ScreenSaver` from the dropdown
        selectObj.selectByVisibleText("ScreenSaver");

        //TODO: `Verify` the `Total` match the expected result `(1-0.1) * 10*20`
        String totalString2 = total.getAttribute("value");

        int a2 = 1;
        double b2 = 0.1;
        double c2 = a2 - b2;
        int d2 = (10*20);
        double e2 = c2 * d2;

        int intValue2 = (int)e2;
        String equation2 = String.valueOf(intValue2);
        assertEquals(equation2,totalString2);

    }

    @Test
    public void testOrderFlow(){

        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: login with correct credentials
        WebOrderUtility.login();

        //TODO: Go to view all product page
        WebOrderUtility.selectSideBarTab("order");

        //TODO: Select `MyMoney` from the dropdown
        WebElement dropDownElm = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select selectObj = new Select(dropDownElm);
        selectObj.selectByVisibleText("MyMoney");

        //TODO: Enter 10 in `quantity` inputbox
        WebElement quantity = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        quantity.sendKeys(Keys.BACK_SPACE+"10");

        //TODO: Click `calculate`
        Driver.getDriver().findElement(By.xpath("//li/input[@type='submit']")).click();

        //TODO: Enter all address information
        WebOrderUtility.enterAddressInfo();

        //TODO: Enter all payment information
        WebOrderUtility.enterPaymentInfo();

        //TODO: Click process to submit
        //TODO: Verify success message has displayed
        WebOrderUtility.submitAndVerify();

    }






}
