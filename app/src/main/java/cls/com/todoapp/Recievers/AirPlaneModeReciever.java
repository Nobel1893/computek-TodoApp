package cls.com.todoapp.Recievers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import cls.com.todoapp.R;

/**
 * Created by CLS on 8/29/2018.
 */

public class AirPlaneModeReciever extends BroadcastReceiver {

    int requestCode=10;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("screen on Reciever","screen is now on");
        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarm2=Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.plucky);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("todo list")
                        .setContentText("user changed airplane momde!")
                        .setContentIntent(pendingIntent)
                        //RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                         .setSound(alarm2);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestCode, mBuilder.build());
    }
}
