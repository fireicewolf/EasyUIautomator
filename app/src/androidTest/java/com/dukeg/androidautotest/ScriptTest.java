package com.dukeg.androidautotest;


import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import com.dukeg.easyuiautomator.BasicEvent;
import com.dukeg.easyuiautomator.CheckEvent;
import com.dukeg.easyuiautomator.KeyEvent;
import com.dukeg.easyuiautomator.SettingEvent;
import com.dukeg.easyuiautomator.TouchEvent;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 19)
public class ScriptTest {
    private BasicEvent basic = new BasicEvent();
    private KeyEvent key = new KeyEvent();
    private TouchEvent touch = new TouchEvent();
    private CheckEvent check = new CheckEvent();
    private SettingEvent setting = new SettingEvent();

//    @Before

    @Test
    public void wifiOnOff() {
//        setting.wifiON();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.wifiOFF();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.bluetoothON();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.bluetoothOFF();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.locationON_MODE_HIGH_ACCURACY();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.locationOFF();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//
//        setting.locationON_MODE_BATTERY_SAVING();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.locationOFF();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//
//        setting.locationON_MODE_DEVICE_ONLY();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);
//        setting.locationOFF();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);

        setting.mobileDataON();
        basic.wait(2000);
        basic.takeScreenshot("test");
        basic.wait(2000);
        basic.takeScreenshot();
//        setting.mobileDataOFF();
//        basic.wait(2000);
//        basic.takeScreenshot();
//        basic.wait(2000);

    }
}
