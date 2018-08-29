package cls.com.todoapp.Recievers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import cls.com.todoapp.R;

/**
 * Created by CLS on 8/29/2018.
 */

public class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("alarm reciever","recieved");
        String name=intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        CreateNotification(context,name,desc);
    }

    public void CreateNotification(Context context,String name,String desc){
        int requestCode=10;
        final Intent emptyIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarm2=Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.plucky);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(name)
                        .setContentText(desc)
                        .setContentIntent(pendingIntent)
                        //RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                        .setSound(alarm2);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestCode, mBuilder.build());
    }
}
