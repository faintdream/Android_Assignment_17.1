package com.akashdubey.serviceunbound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button play,stop;
    Intent startIntent,stopIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.playBtn);
        stop=findViewById(R.id.stopBtn);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.playBtn:
                startIntent=new Intent(MainActivity.this,SongPlayerService.class);
                startService(startIntent);
            break;

            case R.id.stopBtn:

                stopIntent=new Intent(MainActivity.this,SongPlayerService.class);
                stopService(stopIntent);
                break;

        }
    }
}
