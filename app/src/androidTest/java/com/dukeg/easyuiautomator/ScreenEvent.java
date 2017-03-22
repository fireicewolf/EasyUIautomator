package com.dukeg.easyuiautomator;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

public class ScreenEvent {
    private static UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Screen on(if it's already on, doing nothing)
    public static void screenOn() throws RemoteException {
        mDevice.wakeUp();
    }

    //Screen off(if it's already off, doing nothing)
    public static void screenOff() throws RemoteException {
        mDevice.sleep();
    }

    //Swipe actions in pixels(5ms per step)
    public static void swipe(int startX, int startY, int endX, int endY, int steps) {
        mDevice.swipe(startX, startY, endX, endY, steps);
    }

    //Drag actions in pixels
    public static void drag(int startX, int startY, int endX, int endY, int steps) {
        mDevice.drag(startX, startY, endX, endY, steps);
    }

    //Scroll up screen
    public static void scrollUp() {
        mDevice.swipe(540 * mDevice.getDisplayWidth() / 1080, 1440 * mDevice.getDisplayHeight() / 1920, 540 * mDevice.getDisplayWidth() / 1080, 480 * mDevice.getDisplayHeight() / 1920, 100);
    }

    //Scroll down screen
    public static void scrollDown() {
        mDevice.swipe(540 * mDevice.getDisplayWidth() / 1080, 480 * mDevice.getDisplayHeight() / 1920, 540 * mDevice.getDisplayWidth() / 1080, 1440 * mDevice.getDisplayHeight() / 1920, 100);
    }

    //Scroll left screen
    public static void scrollLeft() {
        mDevice.swipe(810 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 270 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 100);
    }

    //Scroll right screen
    public static void scrollRight() {
        mDevice.swipe(270 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 810 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 100);
    }

}
