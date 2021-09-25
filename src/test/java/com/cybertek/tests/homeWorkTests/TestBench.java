package com.cybertek.tests.homeWorkTests;

import com.cybertek.utility.WebOrderUtility;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class TestBench {

    @Test
    public void TestBench1 (){

        WebOrderUtility.openWebOrderApp();

        WebOrderUtility.login("Tester","test");

        WebOrderUtility.checkUserName("Test");

        WebOrderUtility.selectSideBarTab("view all orders");

        WebOrderUtility.checkAll();

        WebOrderUtility.uncheckAll();

        WebOrderUtility.getAllProducts();

        WebOrderUtility.getUnitPriceFormForm("FamilyAlbum");

        WebOrderUtility.getDiscountFromForm("ScreenSaver",50);

        WebOrderUtility.calculateTotal("MyMoney",50);

        WebOrderUtility.getExpectedDiscount("MyMoney",50);

        WebOrderUtility.enterAddressInfo();

        WebOrderUtility.enterPaymentInfo();

    }
}
