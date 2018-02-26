package com.akashdubey.serviceunbound;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;


public class SongPlayerService extends Service {
    MediaPlayer mp;
    static int position = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.song);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //called when player is being resumed
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(position!=0){
            mp.start();
            mp.seekTo(position);
            position=0;
        }else{
            mp.start();
            mp.setLooping(true);
        }

        NotificationCompat.Builder  builder= new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("My Media Player")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText("Song is being play - know more, click here ")
                .setAutoCancel(true);

        Intent goToMain=new Intent(this,MainActivity.class);
        TaskStackBuilder taskStackBuilder= TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(goToMain);

        PendingIntent pendingIntent= taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
        return super.onStartCommand(intent, flags, startId);

    }

    //called when player is being stopped
    @Override
    public void onDestroy() {
        super.onDestroy();
        position=mp.getCurrentPosition();
        mp.stop();
    }
}
