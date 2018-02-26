package com.akashdubey.serviceunbound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.StatFs;
import android.support.annotation.Nullable;


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
