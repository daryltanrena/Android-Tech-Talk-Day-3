package innovations.voyager.techtalkday3.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import innovations.voyager.techtalkday3.R;

public class SoundUnboundService extends Service {
    private static final String LOG_TAG = "SoundUnboundService";
    public static final String TAG_BROADCAST = "TAG_BROADCAST_SOUND_UNBOUND_SERVICE";
    public static final String BROADCAST_UNBOUND_SERVICE_INTENT_ACTION_KEY = "BROADCAST_UNBOUND_SERVICE_INTENT_ACTION_KEY";
    public static final String PAUSE_AUDIO_INTENT_ACTION_VALUE = "PAUSE_AUDIO_INTENT_ACTION_VALUE";
    public static final String RESTART_AUDIO_INTENT_ACTION_VALUE = "RESTART_AUDIO_INTENT_ACTION_VALUE";
    private MediaPlayer audioMediaPlayer;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        audioMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mashup);
        IntentFilter intentFilter = new IntentFilter(SoundUnboundService.TAG_BROADCAST);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(PAUSE_AUDIO_INTENT_ACTION_VALUE.equals(intent.getStringExtra(BROADCAST_UNBOUND_SERVICE_INTENT_ACTION_KEY))) {
                    audioMediaPlayer.pause();
                } else if(RESTART_AUDIO_INTENT_ACTION_VALUE.equals(intent.getStringExtra(BROADCAST_UNBOUND_SERVICE_INTENT_ACTION_KEY))) {
                    audioMediaPlayer.stop();
                    audioMediaPlayer.prepareAsync();
                }
            }
        };
        this.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.v(LOG_TAG, "in onStartCommand");
        audioMediaPlayer.start();
        // START_STICKY: upon death of service, the service WILL recreate itself (intent IS NOT redelivered)
//        return START_STICKY;
        // START_NOT_STICKY: upon death of service the service, the service WILL NOT recreate itself
        return START_NOT_STICKY;
        // START_REDELIVER_INTENT: upon death of service, the service WILL recreate itself (intent IS redelivered)
//        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy(){
        audioMediaPlayer.release();
        this.unregisterReceiver(this.mBroadcastReceiver);
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
    }

}