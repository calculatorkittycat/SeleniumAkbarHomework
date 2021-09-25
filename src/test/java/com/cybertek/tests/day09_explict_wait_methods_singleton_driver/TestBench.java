package com.cybertek.tests.day09_explict_wait_methods_singleton_driver;

import com.cybertek.utility.WebOrderUtility;
import org.junit.jupiter.api.Test;

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

    }
}
