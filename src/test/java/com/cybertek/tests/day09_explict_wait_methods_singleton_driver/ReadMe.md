# Day 9

## Recap on [Day 8:](../day08_upload_actions_window_js/ReadMe.md)
- **File Upload**
    - `fileInputElement.sendKeys("the file location on your computer")`
- [BrowserUtil](../../utility/BrowserUtil.java) class
    - `waitFor(number of seconds)` method to avoid handling `InterruptedException`
- **`Actions` class**
    - built-in feature provided for handling mouse events and keyboard events
    - Creating instance
        - `Actions actions = new Actions(driver);`
    - common **mouse** events :
        - hover over : `moveToElement(element)`
        - drag and drop : `dragAndDrop(sourceElm, targetElm)`
        - click :
            - single click : `click(element)` ,
            - double click : `doubleClick(element)`,
            - right click : `contextClick(element)`
        - `clickAndHold(element)` , `release(element)`
    - common **keyboard** events :
        - type text
            - `sendKeys("text here")`
            - `sendKeys(element, "text here")`
        - hold down to modifier key `keydown(Keys.SHIFT)`
        - release the modifier key `keyUp(Keys.SHIFT)`
    - `perform()` method to trigger the actions sequence.

- **Windows and Tabs**
    - Selenium see tab or window same way -> window
    - Selenium can work with one window at a time
    - Selenium use `window handle` to recognize window and switch to it.
        - `getWindowHandle()` -> current window handle as String
        - `getWindowHandles()` -> all window handles as `Set<String>`
    - Switching to the windows using handle
        - `driver.switchTo().window(handleGoesHere)`

- **Running Javascript using `JavascriptExecutor`**
    - Getting `JavascriptExecutor` reference from `driver`
        - `JavascriptExecutor jse = (JavascriptExecutor) driver`
    - running javascript for scrolling window
        - `jse.executeScript("window.scrollBy(0,1000)") ;`
    - running javascript for scrolling the element into view
        - `jse.executeScript("arguments[0].scrollIntoView(true)" ,webElementHere );`

# Synchronization
A mechanism to make our selenium code and browser work together
as desired speed to avoid `NoSuchElementException`
or any speed related issue that can happen while running automation.

## Waiting fixed amount of time
We have used `Thread.sleep(5000)` to slow down the execution
which is useful in some situations
and yet code will move forward if element is already visible on screen
unless the time is complete which can slow down the test since there is no condition.

## Setting Global wait time : Implicit Wait
In selenium , by default it will wait for 0 second before it throws `NoSuchElementException`.

We learned `implicitWait` available in selenium to
change default behaviour set maximum timeout as below

```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
```

With above Implicit wait condition ,
selenium will wait at least 10 second before throwing `NoSuchElementException`.

If the element is found before that like in 2 seconds,
it will just move on instead of completing 10 seconds.

This will improve the speed since it does not have to finish 10 seconds if element found earlier.

## Using Custom Wait Condition : Explicit Wait
Another waiting technic is Explicit Wait.

Implicit wait has improved the speed compared to Thead.sleep.

However, there will be times we want to wait for certain conditions in any amount of time
and specifically in one or few places instead of globally.

This is where we can use Explicit Wait using `WebDriverWait` Class and `ExpectedConditions` Helper class.

Here is the imports for the classes.
```java
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
```
We can use static import ExpectedConditions static members so all conditions method are directly available in test methods.
```java
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
```
### Explicit wait examples 1 : titleIs
First we need to create `WebDriverWait` object by passing `driver` and desired wait time for condition.

`WebDriverWait` has single method `until` that accept `ExpectedConditions` as arguments.

There are lots of pre-written ExpectedConditions under a Helper Class `ExpectedConditions`.

Below is one example to wait for title to be "some desired title" maximum 5 seconds.

```java
WebDriverWait wait = new WebDriverWait(driver,5);
wait.until(ExpectedConditions.titleIs("some desired title"));
```
Above code will :
- wait for the title to be `some desired title`
- does not depend on global setting by implicit wait
- only apply to one situation
- will continue to wait 5 second until title match
- will throw `TimeoutException` if condition down not match in 5 seconds.

Here is the [full practice code](ExplicitWaitTest.java) we did.

### Explicit wait examples 2 : elementToBeClickable
```java
WebDriverWait wait = new WebDriverWait(driver,5);
wait.until(ExpectedConditions.elementToBeClickable(By.id("someId"));
```
Above code will :
- Wait for 5 seconds using locator provided
- Will continue to wait 5 second until element is clickable
- Does not depend on global setting by implicit wait
- Only apply to one situation

Here is the [full practice code](ExplicitWait2Test.java) we did.

### Explicit wait example 3 : visibilityOfElementLocated
```java
WebDriverWait wait = new WebDriverWait(driver, 7);
// wait for visibility of image with below locator
wait.until( visibilityOfElementLocated(By.xpath("//img[@alt='square pants']")) ) ;
```
### Explicit wait example 4 : textToBe
```java
WebDriverWait wait = new WebDriverWait(driver, 7);
// wait for alert area text to be "Done!"
wait.until( textToBe( By.id("alert"),"Done!") );
```
Here is the [full practice code](ExplicitWaitTest.java) we did.

Here is the list of other `ExpectedConditions`

```java
ExpectedConditions.titleIs("something")
ExpectedConditions.titleContains("something")
ExpectedConditions.urlToBe("some url")
ExpectedConditions.urlContains("some url")
ExpectedConditions.elementToBeClickable(By.id("someId")) 
ExpectedConditions.visibilityOf(aWebElementGoesHere)
ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("someId"))
ExpectedConditions.invisibilityOf(aWebElementGoesHere)
ExpectedConditions.invisibilityOfElementLocated(By.id("someID"))
ExpectedConditions.textToBe(By.id("someId"),"some text")
ExpectedConditions.invisibilityOfElementWithText(By.id("someId"),"some text")
ExpectedConditions.attributeToBe(By.id("someid"),"aAttribute","expectedValue")
ExpectedConditions.frameToBeAvailableAndSwitchToIt("frameNameOrId")
// and more
```

Here is the [javadoc page](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) for `ExpectedConditions`


# Writing Methods to organize code

Here is [the Web Order Practice Site](http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx).

We wrote a simple code to navigate here and login as below :

```java
driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
// enter username
driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
// enter password
driver.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
// click login
driver.findElement(By.id("ctl00_MainContent_login_button")).click();
```

Now our objective is to create a method for logging in,
so we can reuse login method everywhere.
1. Create a class called `WebOrderUtility` for organizing WebOrder app reusable code
2. Create `login` method and get 3 steps to log in from above code.

Here is the initial attempt :

```java
public class WebOrderUtility {
  
    public static void login(){
      // enter username
      driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
      // enter password
      driver.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
      // click login
      driver.findElement(By.id("ctl00_MainContent_login_button")).click();
  }
}
```

However, we immediately realized `driver` does not compile
since `WebOrderUtility` does not extend TestBase.

It seemed like immediate fix by extending `TestBase` class

But it does not make sense. because `WebOrderUtility` **IS-NOT-A** Test.
so it's out of question.

We have `WebDriverFactory` class with method `getDriver` to generate `WebDriver` instance.
However, everytime the method is called it will create new object,
and it will open up new browser window different from `TestBase`.
Si it's not ideal either

> Thought: what if we have another utility class that only generate single WebDriver instance? <br>
> Hold that thought, we will come back to it.

We will need a way to pass that driver into the method,
and in java we can use method parameter just like any other time.

So we decided to pass the `WedDriver driver` as method parameter
and pass it from the @Test method steps.

Here is the attempt 2 :
```java
public class WebOrderUtility {
    // in order to differentiate parameter we changed the name driver to driverParam
    public static void login(WebDriver driverParam){
      // enter username
      driverParam.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
      // enter password
      driverParam.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
      // click login
      driverParam.findElement(By.id("ctl00_MainContent_login_button")).click();
  }
}
```

Now, in test method we can simply call it as below
```java
@Test
public void testLogin(){

  driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
  WebOrderUtility.login(driver) ; 

}
```

Looking good !

Now let's create overloaded `login` method, so we can pass any username and password.

```java
public class WebOrderUtility {
    // in order to differentiate parameter we changed the name driver to driverParam
    public static void login(WebDriver driverParam,String username, String password){
      // enter username
      driverParam.findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
      // enter password
      driverParam.findElement(By.id("ctl00_MainContent_password")) .sendKeys(password);
      // click login
      driverParam.findElement(By.id("ctl00_MainContent_login_button")).click();
  }
}
```

Now , in test class we can just call the method by passing username, password for both positive and nagative scenarios.

```java
@Test
public void testLogin(){

  driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
  WebOrderUtility.login(driver,"Tester","test") ; 

}
```

Let's go ahead and create another utility method to `logout`.

logging out is simply just clicking on logout link as below.

```java
public static void logout(WebDriver driverParam){
    // logout link has id of ctl00_logout
    driverParam.findElement(By.id("ctl00_logout")).click();
}
```
Now we can call it as below.
```java
@Test
public void testLogin(){
  // isn't it this line repeating a lot and really long? 
  // try to created a method called openWebOrderApp().
  driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
  WebOrderUtility.login(driver,"Tester","test") ;
  WebOrderUtility.logout(driver);
}
```

Once we log in with correct credentials ,
you will be on List of All Orders page.
Now let's create a method to check whether we are at this page.

Few ways we can accomplish this right after login method
- check if the URL has changed to List of All Orders page url
- check if the title has changed (in this case it did not)
- check if any element on that page is present like `List of All Orders` header.

We will try with 3rd from above list.

Here is first attempt to create a method called `isAtOrderPage`.
It should return true if we successfully locate header element , false if not.


```java
public static boolean isAtOrderPage(WebDriver driverParam){

        boolean result = false ; // initially set to false and will only be true if element found
        // locator for the header element of all order page
        // normalize-space( text() ) or normalize-space(.) means trim the text 
        //h2[normalize-space(.)='List of All Orders']
        try{
            // if element is not found , it will directly jump to catch section
            WebElement header = driverParam.findElement(By.xpath("//h2[normalize-space(.)='List of All Orders']"));
            System.out.println("header.isDisplayed() = " + header.isDisplayed());
            result = true ; // if code come to this line it means element is found. 
        }catch (NoSuchElementException e){
            System.out.println("NO Such element! you are not at the right page");
        }
        // return the final result according to presense of the element
        return result ;
    }
```

So now if we are successfully able to login , above methods should return true.

```java
 WebOrderUtility.login(driver, "Tester","test");
 System.out.println("is at order page  " +   WebOrderUtility.isAtOrderPage(driver)    );
 assertTrue( WebOrderUtility.isAtOrderPage(driver) ) ;  
 WebOrderUtility.logout(driver);

 WebOrderUtility.login(driver,"bla","bla");
 System.out.println("is at order page" +   WebOrderUtility.isAtOrderPage(driver)    );
 assertFalse( WebOrderUtility.isAtOrderPage(driver) ) ;  
```

Above code works well,
However, since we have implicit wait set to 10 seconds ,
we will have to wait for 10 seconds timeout if element not found.

This is good time to use our explicit wait knowledge we learned earlier.

So instead of waiting for `NoSuchElementException` , we can use `WebDriverWait` as below.

Now instead of waiting for 10 seconds, we only wait for maximum 2 seconds.
And instead of catching `NoSuchElementException` now we can catch `TimeoutException`.

```java
WebDriverWait wait = new WebDriverWait(driverParam,2);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']"))) ;
```

Here is the full method to demonstrate the change.

```java
public static boolean isAtOrderPage(WebDriver driverParam){

    boolean result = false ; // initially set to false and will only be true if element found

    try{
        WebDriverWait wait = new WebDriverWait(driverParam,2); // wait 2 seconds max
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']"))) ;
        result = true ; // if code come to this line it means element is found. 
    }catch (TimeoutException e){
        System.out.println("you are not at the right page!!");
    }
        // return the final result according to presense of the element
      return result ;
}
```

## Creating Driver utility
We have made good progress on our selenium utility methods to simplify test code.

One downside we have observed is :
- having to pass `WebDriver driver` parameters in each and every utility method we created.
- no other way to easily handle it since only TestBase have driver defined.
- need an ability to use same WebDriver instance everywhere.
- need to avoid creating new WebDriver instance accidentally (like WebDriverFactory).

In java , there is a design pattern known as `Singleton`.
It is a design pattern to create a class that only allow creation of single object.
If more object creation attempted , it will just return same object.

It's usually achieved in below 3 steps :
- make constructor private (so no new keyword can be used outside class)
- creating private static field (so only one value assigned)
- public getter for private static field with below condition
    - check if private static field is null or not
        - if not --> create new object and assign it to static field
        - if yes --> return same static field that hold the object.

Inspired by above singleton pattern (and **without digging too deep into the pattern itself**),

Let's take a look at how we can re-create our WebDriverFactory to only generate one object.

```java
public class WebDriverFactory {
    
    public static WebDriver getDriver(String browserName){
        WebDriver driver ;
        switch (browserName.toLowerCase() ){
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
                // other browsers omitted
            default:
                driver = null ;
                System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
        }
        driver.manage().window().maximize();
        return driver ;
    }
}
```

Above class , return new `WebDriver` instance each time being called.

All we need to do is, tweak it little by following singleton way :
- add private constructor
- add private static filed
- add public getter with conditional object creation.
- for the sake of simplicity we will just create ChromeDriver

Instead of modifying existing Factory class lets create new class called `Driver`

```java
public class Driver {
    
    private static WebDriver obj ;
    
    private Driver(){ }

    public static WebDriver getDriver(){

        if(obj == null){
            WebDriverManager.chromedriver().setup();
            obj = new ChromeDriver();
            obj.manage().window().maximize();
//            System.out.println("One and only created for the first time");
            return obj ;
        }else{
//            System.out.println("You have it just use existing one");
            return obj ;
        }
    }

}
```

Now , We have the Driver Utility class that give us single `WebDriver` instance.

Next step will be , use it in `TestBase` and `WebOrderUtility` to see the benefit it brings.

Our objective is we want to use same `WebDriver` instance everywhere.

First lets see how we can modify `TestBase`

```java
import com.cybertek.utility.Driver;

public abstract class TestBase {

  protected WebDriver driver;

  @BeforeEach
  public void setupWebDriver() {

    driver = Driver.getDriver();  //WebDriverFactory.getDriver("chrome");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterEach
  public void closeBrowser() {
    driver.quit();
  }


}
```

We will keep the rest of the part, so existing tests can run without modification.

Now, lets take a look at how we can modify `WebOrderUtility` to reduce extra param.

```java
package com.cybertek.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebOrderUtility {

    public static void login(){
      
      Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
      Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
      Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();
    }

    public static void login(String username ,String password){

      Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
      Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys(password);
      Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();
    }

    public static void logout(){
      Driver.getDriver().findElement(By.id("ctl00_logout")).click();
    }

    public static boolean isAtOrderPage(){
        boolean result = false ;
       try{
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(),2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']"))) ;
            System.out.println("ELEMENT WAS IDENTIFIED ");
            result = true ;
        }catch (TimeoutException e){
            System.out.println("NO Such element! you are not at the right page");
        }
        return result ;
    }

}

```

NO MORE FORCED DRIVER PARAM!! `Driver.getDriver()` INSTEAD !  NEAT!!
