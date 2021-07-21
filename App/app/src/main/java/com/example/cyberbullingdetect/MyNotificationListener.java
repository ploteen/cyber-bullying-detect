package com.example.cyberbullingdetect;

import android.app.Notification;
import android.graphics.drawable.Icon;
import android.service.notification.NotificationListenerService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

public class MyNotificationListener extends  NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        Notification notification = sbn.getNotification();
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);
        Icon smallIcon = notification.getSmallIcon();
        Icon largeIcon = notification.getLargeIcon();

        Log.d("123", "onNotificationPosted ~ " +
                " packageName: " + sbn.getPackageName() +
                " id: " + sbn.getId() +
                " postTime: " + sbn.getPostTime() +
                " title: " + title +
                " text : " + text +
                " subText: " + subText);
    }
}
