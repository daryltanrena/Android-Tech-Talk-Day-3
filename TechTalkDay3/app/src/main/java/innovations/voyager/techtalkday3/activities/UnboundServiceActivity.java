package innovations.voyager.techtalkday3.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import innovations.voyager.techtalkday3.R;
import innovations.voyager.techtalkday3.services.SoundUnboundService;


public class UnboundServiceActivity extends Activity {

    private ImageButton audioPauseImageButton;
    private ImageButton audioPlayImageButton;
    private ImageButton audioStopImageButton;

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
                buildAndSendUnboundServiceBroadcast(SoundUnboundService.PAUSE_AUDIO_INTENT_ACTION_VALUE);
//                Intent unboundServiceActivityIntent = new Intent(getApplicationContext(), UnboundServiceActivity.class);
//                stopService(unboundServiceActivityIntent);
            }
        });
        audioPlayImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent unboundServiceActivityIntent = new Intent(getApplicationContext(), SoundUnboundService.class);
                startService(unboundServiceActivityIntent);
            }
        });
        audioStopImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildAndSendUnboundServiceBroadcast(SoundUnboundService
                        .STOP_AUDIO_INTENT_ACTION_VALUE);
            }
        });
    }

    private void buildAndSendUnboundServiceBroadcast(String actionType) {
        Intent unboundServiceBroadcastIntent = new Intent(SoundUnboundService.TAG_BROADCAST);
        unboundServiceBroadcastIntent.putExtra(SoundUnboundService.BROADCAST_UNBOUND_SERVICE_INTENT_ACTION_KEY, actionType);
        sendBroadcast(unboundServiceBroadcastIntent);
    }
}
