package com.dukeg.easyuiautomator;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Method;


public class SettingEvent {
    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    private Context context = instrumentation.getTargetContext().getApplicationContext();

    private WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private TelephonyManager mTelephonyManager = (TelephonyManager) context.
            getSystemService(Context.TELEPHONY_SERVICE);

    private AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


    //Control display rotation on or off.
    public void freezeRotation() {
        try {
            mDevice.freezeRotation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unfreezeRotation() {
        try {
            mDevice.unfreezeRotation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //Control system display auto brightness
    private int getScreenMode() {
        int mode = -1;
        try {
            mode = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return mode;
    }

    public void autoBrightnessON() {
        if (getScreenMode() == 0) {
            try {
                Settings.System.putInt(context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE, 1);
                Uri uri = Settings.System.getUriFor("screen_brightness_mode");
                context.getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void autoBrightnessOFF() {
        if (getScreenMode() == 1) {
            try {
                Settings.System.putInt(context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
                Uri uri = Settings.System.getUriFor("screen_brightness_mode");
                context.getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param level show be 0~255
     */

    public void setBrightness(int level) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, level);
        Uri uri = Settings.System.getUriFor("screen_brightness");
        context.getContentResolver().notifyChange(uri, null);
    }

    public enum VolumeType {
        alarm, media, notification, ringtone, system, call
    }


    private void getVolumeToast(String volumeType, int maxVolume) {
        Looper.prepare();
        Toast getVolumeToast = Toast.makeText(context, volumeType + " max volume is :" + maxVolume,
                Toast.LENGTH_LONG);
        getVolumeToast.show();
        Looper.loop();
    }

    public void getMaxVolume(VolumeType volumeType) {
        switch (volumeType) {
            case alarm:
                int alarm = 4;
                int alarmMaxVolume = mAudioManager.getStreamMaxVolume(alarm);
                getVolumeToast("Alarm", alarmMaxVolume);
                break;
            case media:
                int media = 3;
                int mediaMaxVolume = mAudioManager.getStreamMaxVolume(media);
                getVolumeToast("Media", mediaMaxVolume);
                break;
            case notification:
                int notification = 5;
                int notificationMaxVolume = mAudioManager.getStreamMaxVolume(notification);
                getVolumeToast("Notification", notificationMaxVolume);
                break;
            case ringtone:
                int ringtone = 2;
                int ringtoneMaxVolume = mAudioManager.getStreamMaxVolume(ringtone);
                getVolumeToast("Ringtone", ringtoneMaxVolume);
                break;
            case system:
                int system = 1;
                int systemMaxVolume = mAudioManager.getStreamMaxVolume(system);
                getVolumeToast("System", systemMaxVolume);
                break;
            case call:
                int call = 0;
                int callMaxVolume = mAudioManager.getStreamMaxVolume(call);
                getVolumeToast("Call", callMaxVolume);
                break;
            default:
                break;
        }


    }

    /**
     * @param volumeType list 6 type volumes.
     * @param level      level should be 0~15.
     */
    public void setVolume(VolumeType volumeType, int level) {
        int FLAG_SHOW_UI = 1;

        switch (volumeType) {
            case alarm:
                int alarm = 4;
                mAudioManager.setStreamVolume(alarm, level, FLAG_SHOW_UI);
                break;
            case media:
                int media = 3;
                mAudioManager.setStreamVolume(media, level, FLAG_SHOW_UI);
                break;
            case notification:
                int notification = 5;
                mAudioManager.setStreamVolume(notification, level, FLAG_SHOW_UI);
                break;
            case ringtone:
                int ringtone = 2;
                mAudioManager.setStreamVolume(ringtone, level, FLAG_SHOW_UI);
                break;
            case system:
                int system = 1;
                mAudioManager.setStreamVolume(system, level, FLAG_SHOW_UI);
                break;
            case call:
                int call = 0;
                mAudioManager.setStreamVolume(call, level, FLAG_SHOW_UI);
                break;
            default:
                break;
        }
    }

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


}
