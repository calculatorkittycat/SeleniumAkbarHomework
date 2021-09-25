package com.cybertek.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// THIS AIN'T NO TEST CLASS SO WE CAN NOT EXTEND TESTBASE
// IT SIMPLY DOES NOT MAKE SENSE
public class WebOrderUtility {

    /**
     * A method for logging into Web Order practice site from login page
     * @param driverParam we don't have access to driver as we did in Test classes,
     *                    so we need to pass it as parameter when calling this method
     */
    public static void login(WebDriver driverParam){

        // BELOW LINE WILL NOT WORK BECAUSE IT WILL OPEN NEW DRIVER EACH TIME
//        WebDriver driver = WebDriverFactory.getDriver("chrome");
        // enter username
        driverParam.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        driverParam.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
        // click login
        driverParam.findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    /**
     * A method for logging into using username password to Web Order practice site
     * @param driverParam we don't have access to driver as we did in Test classes,
     *                    so we need to pass it as parameter when calling this method
     * @param username username for web order practice site
     * @param password password for web order practice site
     */
    public static void login(WebDriver driverParam, String username ,String password){

        // BELOW LINE WILL NOT WORK BECAUSE IT WILL OPEN NEW DRIVER EACH TIME
//        WebDriver driver = WebDriverFactory.getDriver("chrome");
        // enter username
        driverParam.findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
        // enter password
        driverParam.findElement(By.id("ctl00_MainContent_password")) .sendKeys(password);
        // click login
        driverParam.findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    /**
     * A method for logging out if user is logged in
     * @param driverParam WebDriver instance need to be passed from outside
     */
    public static void logout(WebDriver driverParam){

        // logout link has id of ctl00_logout
        driverParam.findElement(By.id("ctl00_logout")).click();

    }

    /**
     * A method to check if we are at order page
     * @param driverParam WebDriver instance
     * @return true if header element is present false if not.
     */
    public static boolean isAtOrderPage(WebDriver driverParam){
        // you can also check the url
        // you can check the title if it's different
        // whatever that makes sense
        // in here we decided to check one element
        boolean result = false ;

        // locator for the header element of all order page
        //h2[normalize-space(.)='List of All Orders']
        //TODO : Try to replace this with WebDriverWait
        // since it will wait 10 second set by implicit wait if not found.
        try{
//            WebElement header = driverParam.findElement(By.xpath("//h2[normalize-space(.)='List of All Orders']"));
            WebDriverWait wait = new WebDriverWait(driverParam,2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']"))) ;
            System.out.println("ELEMENT WAS IDENTIFIED ");
//            System.out.println("header.isDisplayed() = " + header.isDisplayed());
            result = true ;
//        }catch (NoSuchElementException e){
        }catch (TimeoutException e){
            System.out.println("you are not at the right page");
        }

        return result ;
//        System.out.println("header.isDisplayed() = " + header.isDisplayed());

    }

    // so now we have Driver class that generate Single WebDriver instance
    // we can use it anywhere here in this method
    // without passing the WebDriver as parameter




}