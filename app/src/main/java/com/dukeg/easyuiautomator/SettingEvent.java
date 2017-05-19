package com.dukeg.easyuiautomator;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;


public class SettingEvent {
    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    private Context context = instrumentation.getTargetContext().getApplicationContext();

    private WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private TelephonyManager mTelephonyManager = (TelephonyManager) context.
            getSystemService(Context.TELEPHONY_SERVICE);

    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


    //Control bluetooth on or off.
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

    //Control bluetooth on or off.
    public void bluetoothON() {
        if (mBluetoothAdapter.disable()) {
            mBluetoothAdapter.enable();
        }
    }

    public void bluetoothOFF() {
        if (mBluetoothAdapter.enable()) {
            mBluetoothAdapter.disable();
        }
    }

    //Change location mode need system uid.
    public void locationON_MODE_HIGH_ACCURACY() {
        int state = Settings.Secure.getInt(context.getContentResolver(),
                Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_HIGH_ACCURACY);
        if (state != Settings.Secure.LOCATION_MODE_HIGH_ACCURACY) {
            Settings.Secure.putInt(context.getContentResolver(), //By system signed
                    Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_HIGH_ACCURACY);
        }
    }

    public void locationON_MODE_BATTERY_SAVING() {
        int state = Settings.Secure.getInt(context.getContentResolver(),
                Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_BATTERY_SAVING);
        if (state != Settings.Secure.LOCATION_MODE_BATTERY_SAVING) {
            Settings.Secure.putInt(context.getContentResolver(), //By system signed
                    Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_BATTERY_SAVING);
        }
    }

    public void locationON_MODE_DEVICE_ONLY() {
        int state = Settings.Secure.getInt(context.getContentResolver(),
                Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_SENSORS_ONLY);
        if (state != Settings.Secure.LOCATION_MODE_SENSORS_ONLY) {
            Settings.Secure.putInt(context.getContentResolver(), //By system signed
                    Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_SENSORS_ONLY);
        }
    }

    public void locationOFF() {
        int state = Settings.Secure.getInt(context.getContentResolver(),
                Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
        if (state != Settings.Secure.LOCATION_MODE_OFF) {
            Settings.Secure.putInt(context.getContentResolver(), //By system signed
                    Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
        }
    }

    //Change mobile status need system uid.
    public void mobileDataON() {
        Method setMobileDataEnable;
        try {
            setMobileDataEnable = mTelephonyManager.getClass().getMethod("setDataEnabled", boolean.class);
            setMobileDataEnable.invoke(mTelephonyManager, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mobileDataOFF() {
        Method setMobileDataEnable;
        try {
            setMobileDataEnable = mTelephonyManager.getClass().getMethod("setDataEnabled", boolean.class);
            setMobileDataEnable.invoke(mTelephonyManager, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void freezeRotation() throws RemoteException {
        mDevice.freezeRotation();
    }

    public void unfreezeRotation() throws RemoteException {
        mDevice.unfreezeRotation();
    }


}
