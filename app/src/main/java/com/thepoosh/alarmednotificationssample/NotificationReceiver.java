package com.thepoosh.alarmednotificationssample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";
    private static final int NOTIFICATION_ID = 1975;
    private static final int REQUEST_CODE = 1983;

    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification = createNotification(context);

        sendNotificationToNotificationTrey(context, notification);
    }

    private void sendNotificationToNotificationTrey(Context context, Notification notification) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notification);
    }

    private Notification createNotification(Context context) {
        return new Notification.Builder(context)
                       .setContentTitle("This is a title")
                       .setContentText("This is a subtitle")
                       .setSmallIcon(R.mipmap.ic_launcher)
                       // these are the required fields for a notification
                       .setTicker("this is the text that floats around the notification trey")
                       .setContentIntent(getPendingIntent(context))
                       // dismiss notification onCLick
                       .setAutoCancel(true)
                       .build();
    }

    private PendingIntent getPendingIntent(Context context) {
        Intent target = new Intent(context, MainActivity.class);
        /** needed because starting an {@link android.app.Activity} from a non-ui context **/
        target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        return PendingIntent.getActivity(context, REQUEST_CODE,target, PendingIntent.FLAG_UPDATE_CURRENT);
    }


}
