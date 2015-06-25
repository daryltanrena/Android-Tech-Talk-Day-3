package innovations.voyager.techtalkday3.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import innovations.voyager.techtalkday3.R;

public class SoundBoundService extends Service {
    private static final String LOG_TAG = "SoundBoundService";
    MediaPlayer audioMediaPlayer;
    private IBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        audioMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mashup);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        audioMediaPlayer.release();
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
//        mChronometer.stop();
    }

    public void startAudioPlay() {
        audioMediaPlayer.start();
    }

    public void stopAudioPlay() {
        audioMediaPlayer.stop();
    }

    public class MyBinder extends Binder {
        SoundBoundService getService() {
            return SoundBoundService.this;
        }
    }
}