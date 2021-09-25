package com.cybertek.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

// THIS AIN'T NO TEST CLASS SO WE CAN NOT EXTEND TESTBASE
// IT SIMPLY DOES NOT MAKE SENSE
public class WebOrderUtility {

    /**
     * A method for logging into Web Order practice site from login page
     *
     * @param// driverParam we don't have access to driver as we did in Test classes,
     * so we need to pass it as parameter when calling this method
     */
    public static void login() {

        // BELOW LINE WILL NOT WORK BECAUSE IT WILL OPEN NEW DRIVER EACH TIME
//        WebDriver driver = WebDriverFactory.getDriver("chrome");
        // enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    /**
     * A method for logging into using username password to Web Order practice site
     *
     * @param //driverParam we don't have access to driver as we did in Test classes,
     *                      so we need to pass it as parameter when calling this method
     * @param username      username for web order practice site
     * @param password      password for web order practice site
     */
    public static void login(String username, String password) {

        // BELOW LINE WILL NOT WORK BECAUSE IT WILL OPEN NEW DRIVER EACH TIME
//        WebDriver driver = WebDriverFactory.getDriver("chrome");
        // enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    /**
     * A method for logging out if user is logged in
     *
     * @param //driverParam WebDriver instance need to be passed from outside
     */
    public static void logout() {

        // logout link has id of ctl00_logout
        Driver.getDriver().findElement(By.id("ctl00_logout")).click();

    }

    /**
     * A method to check if we are at order page
     *
     * @param //driverParam WebDriver instance
     * @return true if header element is present false if not.
     */
    public static boolean isAtOrderPage() {
        // you can also check the url
        // you can check the title if it's different
        // whatever that makes sense
        // in here we decided to check one element
        boolean result = false;

        // locator for the header element of all order page
        //h2[normalize-space(.)='List of All Orders']
        //TODO : Try to replace this with WebDriverWait
        // since it will wait 10 second set by implicit wait if not found.
        try {
//            WebElement header = driverParam.findElement(By.xpath("//h2[normalize-space(.)='List of All Orders']"));
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']")));
            System.out.println("ELEMENT WAS IDENTIFIED ");
//            System.out.println("header.isDisplayed() = " + header.isDisplayed());
            result = true;
//        }catch (NoSuchElementException e){
        } catch (TimeoutException e) {
            System.out.println("you are not at the right page");
        }

        return result;
//        System.out.println("header.isDisplayed() = " + header.isDisplayed());

    }

    public static void openWebOrderApp() {
        Driver.getDriver().get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
    }

    public static boolean checkUserName(String username) {
        //boolean result = false;

        String afterLoginUsername = Driver.getDriver().findElement(By.xpath("//div[@class='login_info']")).getText();
        if (afterLoginUsername.contains(username)) {
            System.out.println("!!PASS- USRNMMATCH: User Name Verified");
            return true;
        } else {
            System.out.println("!!FAIL- USRNMMISMATCH: User Name Not Verified");
            return false;
        }
    }

    public static void selectSideBarTab(String tabName) {

        if (tabName.equalsIgnoreCase("View all orders")) {
            Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[1]/a")).click();
        }

        if (tabName.equalsIgnoreCase("View all products")) {
            Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[2]/a")).click();
        }

        if (tabName.equalsIgnoreCase("Order")) {
            Driver.getDriver().findElement(By.xpath("//ul[@id='ctl00_menu']/li[3]/a")).click();
        }
    }

    public static boolean checkAll() {
        //boolean result = false;
        WebElement checkAllBtn = Driver.getDriver().findElement(By.linkText("Check All"));
        checkAllBtn.click();
        String firstHalfXpath = "//tbody/tr[";
        String secondHalfXpath = "]/td/input[@type='checkbox']";
        int selected = 0;
        for (int i = 2; i < 10; i++) {
            String newXpath = firstHalfXpath + i + secondHalfXpath;
            if (Driver.getDriver().findElement(By.xpath(newXpath)).isSelected())
                selected++;
        }
        if (selected == 8) {
            System.out.println("All 8 checkboxes are selected");
            return true;
        } else {
            System.out.println("Something went wrong @checkAll method");
            return false;
        }
    }

    public static boolean uncheckAll() {
        WebElement uncheckAllBtn = Driver.getDriver().findElement(By.linkText("Uncheck All"));
        uncheckAllBtn.click();
        String firstHalfXpath = "//tbody/tr[";
        String secondHalfXpath = "]/td/input[@type='checkbox']";
        int notSelected = 0;
        for (int i = 2; i < 10; i++) {
            String newXpath = firstHalfXpath + i + secondHalfXpath;
            if (!Driver.getDriver().findElement(By.xpath(newXpath)).isSelected())
                notSelected++;
        }
        if (notSelected == 8) {
            System.out.println("All 8 checkboxes are un-selected");
            return true;
        } else {
            System.out.println("Something went wrong @unCheckAll method");
            return false;
        }
    }

    public static List<String> getAllProducts() {
        selectSideBarTab("view all products");



        WebElement firstCol = Driver.getDriver().findElement(By.xpath("//table/tbody/tr/td/div/table/tbody/tr[2]/td"));
        WebElement secondCol = Driver.getDriver().findElement(By.xpath("//table/tbody/tr/td/div/table/tbody/tr[3]/td"));
        WebElement thirdCol = Driver.getDriver().findElement(By.xpath("//table/tbody/tr/td/div/table/tbody/tr[4]/td"));

        //System.out.println(firstCol.getText());
        //System.out.println(secondCol.getText());
        //System.out.println(thirdCol.getText());

        String firstColumnText = firstCol.getText();
        String secondColumnText = secondCol.getText();
        String thirdColmnText = thirdCol.getText();

        List<String> list1 = new ArrayList<>();
        list1.add(firstColumnText);
        list1.add(secondColumnText);
        list1.add(thirdColmnText);

        System.out.println(list1);
        return list1;
    }


    public static int getUnitPriceFormForm(String productName){
        selectSideBarTab("order");

        WebElement dropdown = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));

        Select selectObj = new Select(dropdown);
        selectObj.selectByVisibleText(productName);

        Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE+"3");

        Driver.getDriver().findElement(By.xpath("//li/input[@type='submit']")).click();

        WebElement pricePerUnit = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice"));
        String priceString = pricePerUnit.getAttribute("value");


        int pricePerUnitOutput = Integer.parseInt(priceString);
        System.out.println("pricePerUnitOutput = " + pricePerUnitOutput);
        return pricePerUnitOutput;

    }

    /**
     * ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR-ERROR
     * @param productName
     * @param quantity
     * @return
     */

    public static int getDiscountFromForm(String productName, int quantity){
        selectSideBarTab("order");

        WebElement dropdown = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));

        Select selectObj = new Select(dropdown);
        selectObj.selectByVisibleText(productName);

        String s = String.valueOf(quantity);
        System.out.println(s);

        WebElement t1 = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        t1.sendKeys(Keys.BACK_SPACE);
        t1.sendKeys(s);

        Driver.getDriver().findElement(By.xpath("//li/input[@type='submit']")).click();

        WebElement discount = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount"));
        String discountString = discount.getAttribute("text");

        int discountStringNumVal = Integer.parseInt(discountString);
        System.out.println("discountStringNumVal = " + discountStringNumVal);
        return discountStringNumVal;

    }

    // so now we have Driver class that generate Single WebDriver instance
    // we can use it anywhere here in this method
    // without passing the WebDriver as parameter




}