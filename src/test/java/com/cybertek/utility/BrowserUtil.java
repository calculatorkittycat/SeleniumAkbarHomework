package com.cybertek.utility;

public class BrowserUtil {

    /**
     * A method to pause the thread certain seconds
     * @param seconds
     */
    public static void waitFor(int seconds){

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}