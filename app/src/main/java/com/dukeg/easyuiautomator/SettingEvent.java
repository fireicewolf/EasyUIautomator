package com.dukeg.easyuiautomator;

import android.app.Instrumentation;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;

public class SettingEvent {
    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    private Context context = instrumentation.getTargetContext().getApplicationContext();

    private WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    public void wifiON() {
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
    }

    public void wifiOFF() {
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        }
    }


}
