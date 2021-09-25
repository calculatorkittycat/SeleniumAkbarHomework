package com.cybertek.tests.day09_explict_wait_methods_singleton_driver;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import com.cybertek.utility.WebOrderUtility;
import org.junit.jupiter.api.Test;

public class WebOrderTest extends TestBase {

    @Test
    public void testLogin(){

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");

//        // enter username
//        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
//        // enter password
//        driver.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
//        // click login
//        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

//        WebOrderUtility.login(driver);

        WebOrderUtility.login(driver, "Tester","test");

        System.out.println("is at order page  " +   WebOrderUtility.isAtOrderPage(driver)    );

        BrowserUtil.waitFor(2);

        WebOrderUtility.logout(driver);

        BrowserUtil.waitFor(2);

        WebOrderUtility.login(driver,"bla","bla");

        System.out.println("is at order page" +   WebOrderUtility.isAtOrderPage(driver)    );

        BrowserUtil.waitFor(4);

        // Create a class called WebOrderUtility
        // Create a static void method called
        // login(username, password) accept 2 string for username password
    }

}