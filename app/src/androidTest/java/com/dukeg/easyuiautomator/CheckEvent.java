package com.dukeg.easyuiautomator;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

public class CheckEvent {
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Check screen on(return ture) or off(return false)
    public boolean isScreenOn() throws RemoteException {
        return mDevice.isScreenOn();
    }

    //Check whether the object is exsit
    public boolean isObjectExsitByRes(String resourceID) {
        //UiObject testObject = new UiObject(new UiSelector().resourceId(resourceID));
        //testObject.exsit();
        return mDevice.wait(Until.hasObject((By.res(resourceID))), 5000);
    }
}
