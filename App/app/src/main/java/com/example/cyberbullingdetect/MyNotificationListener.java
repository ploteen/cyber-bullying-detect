package com.example.cyberbullingdetect;

import android.app.Notification;
import android.content.SharedPreferences;
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

import androidx.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyNotificationListener extends  NotificationListenerService {
    String urlStr = "http://121.136.228.184:30000";
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
        if (sbn.getPackageName().equals("com.kakao.talk") && text != null) {
            RequestThread thread = new RequestThread(text.toString());
            thread.start();
        }
        Log.d("123", "onNotificationPosted ~ " +
                " packageName: " + sbn.getPackageName() +
                " id: " + sbn.getId() +
                " postTime: " + sbn.getPostTime() +
                " title: " + title +
                " text : " + text +
                " subText: " + subText);
    }
    class RequestThread extends Thread {
        private String str;
        RequestThread(String str) {
            this.str = str;
        }
        @Override
        public void run() {
            try {
                URL url = new URL(urlStr);
                if(str == null)
                    return;
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(10000); // 10초 동안 기다린 후 응답이 없으면 종료
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("sentence", str);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("sentence").append("=").append(str);

                    OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                    PrintWriter writer = new PrintWriter(outStream);
                    writer.write(buffer.toString());
                    writer.flush();

                    int resCode = conn.getResponseCode();
                    if(resCode == HttpURLConnection.HTTP_OK){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line = null;
                        while(true){
                            line = reader.readLine();
                            if(line == null)
                                break;
                            // 있을때
                            Log.d("classfy return",line );
                            SharedPreferences classify = getSharedPreferences("classify", MODE_PRIVATE);
                            SharedPreferences.Editor editor = classify.edit();
                            int total = classify.getInt("total", 0);
                            int hate = classify.getInt("hate", 0 );
                            total += 1;
                            if(line.equals("1")) {
                                hate += 1;
                            }
                            editor.putInt("total", total);
                            editor.putInt("hate", hate);
                            editor.commit();
                        }
                        reader.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
