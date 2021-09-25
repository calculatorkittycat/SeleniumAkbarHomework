package com.cybertek.tests.homeWorkTests;

import com.cybertek.utility.Driver;
import com.cybertek.utility.WebOrderUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;
public class WebOrderLoginTests {

    @Test
    public void testLoginWithCorrectCredentials() {


        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: Login with correct credentials
        WebOrderUtility.login();

        //TODO: Verify you are at `all order page`
        String expectedResult = Driver.getDriver().getCurrentUrl();
        assertEquals("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx", expectedResult);

        //TODO: Verify Welcome message beside `logout` link contains the login `username`
        WebOrderUtility.checkUserName("Tester");

        //TODO: Logout
        WebOrderUtility.logout();
    }

    @Test
    public void testLoginWithInvalidCredentials(){


        //TODO: Go to login page
        WebOrderUtility.openWebOrderApp();

        //TODO: Login with invalid credentials
        WebOrderUtility.login("ha","haha");

        //TODO: Verify you are still at login page
        String expectedResult = Driver.getDriver().getCurrentUrl();

        assertEquals("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx",expectedResult);

        //TODO: Verify the error message `Invalid Login or Password.` displayed on the screen
        WebElement invalidCredentialsMessage = Driver.getDriver().findElement(By.xpath("//span"));
        String expectedResultErrorMessage = invalidCredentialsMessage.getText();

        assertEquals("Invalid Login or Password.",expectedResultErrorMessage);
    }











}
