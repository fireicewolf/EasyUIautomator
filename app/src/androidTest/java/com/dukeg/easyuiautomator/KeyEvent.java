/**
 * Created by John Yu on 2017/3/8.
 * To make uiautomator easy bo be used, redefined some commands for it.
 */

package com.dukeg.easyuiautomator;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.InstrumentationUiAutomatorBridge;
import android.support.test.uiautomator.Tracer;
import android.support.test.uiautomator.UiDevice;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.accessibility.AccessibilityEvent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class KeyEvent {
    // Initialize UiDevice instance
    private static UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Long press key by keycode
    private static boolean longPressKeyCode(int keyCode, long PressTime) {
        try {
            Field mUiAutomationBridge = Class.forName("android.support.test.uiautomator.UiDevice").getDeclaredField("mUiAutomationBridge");
            mUiAutomationBridge.setAccessible(true);

            Object bridgeObj = mUiAutomationBridge.get(mDevice);
            Method injectInputEvent = Class.forName("android.support.test.uiautomator.UiAutomatorBridge")
                    .getDeclaredMethod("injectInputEvent", android.view.InputEvent.class, boolean.class);

            final long eventTime = SystemClock.uptimeMillis();
            android.view.KeyEvent downEvent = new android.view.KeyEvent(eventTime, eventTime, android.view.KeyEvent.ACTION_DOWN,
                    keyCode, 0, 0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                    InputDevice.SOURCE_KEYBOARD);

            if ((Boolean) injectInputEvent.invoke(bridgeObj, downEvent, true)) {
                SystemClock.sleep(PressTime);
                android.view.KeyEvent upEvent = new android.view.KeyEvent(eventTime, eventTime,
                        android.view.KeyEvent.ACTION_UP, keyCode, 0, 0,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                        InputDevice.SOURCE_KEYBOARD);
                if ((Boolean) injectInputEvent.invoke(bridgeObj, upEvent, true)) {
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Press Home key
    public static boolean pressHome() {
        return mDevice.pressHome();
    }

    public static boolean pressMenu() {
        return mDevice.pressMenu();
    }

    public static boolean pressBack() {
        return mDevice.pressBack();
    }

    /**
    public enum keyCode {
        power, volumeUp, volumeDown, volumeMute, home, back, menu, recentApps
    }

    public enum pressType {
        shortPress, longPress
    }

    //
    public static void pressKey(keyCode keyCode, pressType pressType, long PressTime) {
        switch (pressType) {
            case shortPress:
                switch (keyCode) {
                    case power:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_POWER);
                        break;
                    case volumeUp:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_UP);
                        break;
                    case volumeDown:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_DOWN);
                        break;
                    case volumeMute:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_MUTE);
                        break;
                    case home:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_HOME);
                        break;
                    case back:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_BACK);
                        break;
                    case menu:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_MENU);
                        break;
                    case recentApps:
                        mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_APP_SWITCH);
                        //mDevice.pressRecentApps();
                        break;
                    default:
                        break;
                }
            case longPress:
                switch (keyCode) {
                    case power:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_POWER, PressTime);
                        break;
                    case volumeUp:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_UP, PressTime);
                        break;
                    case volumeDown:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_DOWN, PressTime);
                        break;
                    case volumeMute:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_MUTE, PressTime);
                        break;
                    case home:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_HOME, PressTime);
                        break;
                    case back:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_BACK, PressTime);
                        break;
                    case menu:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_MENU, PressTime);
                        break;
                    case recentApps:
                        longPressKeyCode(android.view.KeyEvent.KEYCODE_APP_SWITCH, PressTime);
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }
    */
}
