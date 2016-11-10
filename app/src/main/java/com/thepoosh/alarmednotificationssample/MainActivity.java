package com.thepoosh.alarmednotificationssample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                finish();
            }
        });
    }

    private void setAlarm() {
        PendingIntent action = getPendingIntent();
        long when = System.currentTimeMillis() + 15 * DateUtils.SECOND_IN_MILLIS;
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.set(AlarmManager.RTC, when, action);
    }

    private PendingIntent getPendingIntent() {
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        return PendingIntent.getBroadcast(this, REQUEST_CODE, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
