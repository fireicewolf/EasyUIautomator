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
    public void script() {
        setting.airplaneModeON();
        basic.wait(3000);
        basic.takeScreenshot();
        basic.wait(3000);
        setting.airplaneModeOFF();
        basic.wait(3000);
        basic.takeScreenshot();
        basic.wait(3000);
    }
}
