package com.example.richardbrown_c196.Activities;

import android.widget.Toast;
import android.content.Intent;
import android.content.Context;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import com.example.richardbrown_c196.R;
import android.content.BroadcastReceiver;
import androidx.core.app.NotificationCompat;


public class MyReceiver extends BroadcastReceiver {
    String notifyChannel="test";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context, notifyChannel);
        Notification notification = new NotificationCompat.Builder(context, notifyChannel).setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("NotifyTest").build();
        NotificationManager manager = (NotificationManager)  context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notificationID++, notification);

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
    }

    private void createNotificationChannel(Context context, String NOTIFYCHANNEL) {
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(NOTIFYCHANNEL, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}