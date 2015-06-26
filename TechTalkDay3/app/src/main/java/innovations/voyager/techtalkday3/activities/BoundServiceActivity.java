package innovations.voyager.techtalkday3.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;

import innovations.voyager.techtalkday3.R;
import innovations.voyager.techtalkday3.services.SoundBoundService;


public class BoundServiceActivity extends Activity {

    private ImageButton audioPauseImageButton;
    private ImageButton audioPlayImageButton;
    private ImageButton audioStopImageButton;
    private SoundBoundService mBoundService;
    private boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        audioPauseImageButton = (ImageButton) findViewById(R.id.audioPauseImageView);
        audioPlayImageButton = (ImageButton) findViewById(R.id.audioPlayImageView);
        audioStopImageButton = (ImageButton) findViewById(R.id.audioStopImageView);
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        audioPauseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mServiceBound) {
                    mBoundService.pauseAudioPlay();
                }
            }
        });
        audioPlayImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mServiceBound) {
                    mBoundService.startAudioPlay();
                }
            }
        });
        audioStopImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mServiceBound) {
                    mBoundService.stopAudioPlay();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startAndBindToSoundBoundService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    private void startAndBindToSoundBoundService() {
        Intent intent = new Intent(this, SoundBoundService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SoundBoundService.MyBinder myBinder = (SoundBoundService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };

}
