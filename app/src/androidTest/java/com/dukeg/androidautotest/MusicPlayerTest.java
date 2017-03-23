package com.dukeg.androidautotest;


import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import com.dukeg.easyuiautomator.BasicEvent;
import com.dukeg.easyuiautomator.CheckEvent;
import com.dukeg.easyuiautomator.ClickEvent;
import com.dukeg.easyuiautomator.KeyEvent;
import com.dukeg.easyuiautomator.ScreenEvent;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class MusicPlayerTest {

    BasicEvent basic;
    KeyEvent key;
    ClickEvent click;
    ScreenEvent screen;
    CheckEvent check;

    @Test
    public void musicplayerTest() {
        basic.launch("com.android.music",5000);
        basic.wait(5000);
    }
}
