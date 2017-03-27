package com.dukeg.androidautotest;


import android.os.RemoteException;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import com.dukeg.easyuiautomator.BasicEvent;
import com.dukeg.easyuiautomator.CheckEvent;
import com.dukeg.easyuiautomator.ClickEvent;
import com.dukeg.easyuiautomator.KeyEvent;
import com.dukeg.easyuiautomator.ScreenEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 19)
public class ExampleTest {

    @Test
    public void cameraTest() {
        BasicEvent.launch("com.android.camera",5000);
        BasicEvent.wait(5000);
        ClickEvent.clickByObject(ClickEvent.findObjectType.byRes,"com.android.camera:id/shutter_button",5000, ClickEvent.clickType.shortClick);
        BasicEvent.wait(3000);
        KeyEvent.pressHome();
    }

    @Test
    public void musicplayerTest() {
        BasicEvent.launch("com.android.music",5000);
        BasicEvent.wait(5000);
        ClickEvent.clickByObject(ClickEvent.findObjectType.byRes,"com.android.music:id/playbar_play",5000, ClickEvent.clickType.shortClick);
        BasicEvent.wait(3000);
        KeyEvent.pressHome();
    }

}
