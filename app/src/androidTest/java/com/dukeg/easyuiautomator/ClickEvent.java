package com.dukeg.easyuiautomator;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

public class ClickEvent {
    private static UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Click once action in pixels
    public static void click(int x, int y) {
        mDevice.click(x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920);
    }

    //Multi Click action in pixels
    public static void multiClick(int x, int y, long interval, int times) {
        long timeout = Configurator.getInstance().getActionAcknowledgmentTimeout();
        Configurator.getInstance().setActionAcknowledgmentTimeout(interval);
        for (int i = 0; i < times; i++) {
            mDevice.click(x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920);
        }
        Configurator.getInstance().setActionAcknowledgmentTimeout(timeout);
    }

    //Long Click action in pixels
    public static void longClick(int x, int y, int timeLength) {
        int steps = timeLength / 5;
        mDevice.swipe(x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920,
                x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920, steps);
    }

    public enum findObjectType {
        byRes, byText, byDesc
    }

    public enum clickType {
        shortClick, longClick
    }

    //Click element by Object(Resource ID, Text or Description), include click once, long click and multi click.
    public static void clickByObject(findObjectType fType, String content, long timeout, clickType cType) {
        switch (cType) {
            case shortClick:
                switch (fType) {
                    case byRes:
                        mDevice.wait(Until.findObject(By.res(content)), timeout).click();
                        break;
                    case byText:
                        mDevice.wait(Until.findObject(By.text(content)), timeout).click();
                        break;
                    case byDesc:
                        mDevice.wait(Until.findObject(By.desc(content)), timeout).click();
                        break;
                    default:
                        break;
                }
            case longClick:
                switch (fType) {
                    case byRes:
                        mDevice.wait(Until.findObject(By.res(content)), timeout).longClick();
                        break;
                    case byText:
                        mDevice.wait(Until.findObject(By.text(content)), timeout).longClick();
                        break;
                    case byDesc:
                        mDevice.wait(Until.findObject(By.desc(content)), timeout).longClick();
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    //Multi Click element by object
    public static void multiClickByObject(findObjectType fType, String content, long timeout, int touchTimes, long interval) {
        long touchTimeout = Configurator.getInstance().getActionAcknowledgmentTimeout();
        Configurator.getInstance().setActionAcknowledgmentTimeout(interval);
        for (int i = 0; i < touchTimes; i++) {
            switch (fType) {
                case byRes:
                    mDevice.wait(Until.findObject(By.res(content)), timeout).click();
                    break;
                case byText:
                    mDevice.wait(Until.findObject(By.text(content)), timeout).click();
                    break;
                case byDesc:
                    mDevice.wait(Until.findObject(By.desc(content)), timeout).click();
                    break;
                default:
                    break;
            }
        }
        Configurator.getInstance().setActionAcknowledgmentTimeout(touchTimeout);
    }
}
