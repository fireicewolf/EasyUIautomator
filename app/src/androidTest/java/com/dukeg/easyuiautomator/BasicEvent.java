package com.dukeg.easyuiautomator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

public class BasicEvent {
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Get Apps' package name in Launcher
    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    //Open an app by its package name
    public void launch(String packageName, int timeout) {

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();

        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), timeout);

        // Launch the blueprint app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(807403520);

        // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), timeout);
    }

    //Time between every action
    public void wait(int sleep) {
        SystemClock.sleep(sleep);
    }

    //Open Notification bar
    public boolean openNotificationBar() {
        return mDevice.openNotification();
    }

    //Open Notification bar
    public boolean openQuickSettings() {
        return mDevice.openQuickSettings();
    }


    //Screen on(Doing nothing if screen is already on)
    public void screenOn() throws RemoteException {
        mDevice.wakeUp();
    }

    //Screen off(Doing nothing if screen is already on)
    public void screenOff() throws RemoteException {
        mDevice.sleep();
    }

}

