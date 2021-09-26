package com.cybertek.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * We wanted to have a class with that only return Single object
 * no matter how many time you asked for object
 * so we are creating this class with technic we learned from Singleton pattern
 */
public class Driver {

    private static WebDriver obj ;

    private Driver(){ }

    public static WebDriver getDriver(){

        if(obj == null){
            WebDriverManager.chromedriver().setup();
            obj = new ChromeDriver();
            //obj.manage().window().maximize();
            obj.manage().window().setPosition(new Point(1750,0));
//            System.out.println("One and only created for the first time");
            return obj ;
        }else{
//            System.out.println("You have it just use existing one");
            return obj ;

        }

    }

}