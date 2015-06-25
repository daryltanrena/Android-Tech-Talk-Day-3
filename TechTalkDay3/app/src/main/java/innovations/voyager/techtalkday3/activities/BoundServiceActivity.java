package innovations.voyager.techtalkday3.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import innovations.voyager.techtalkday3.R;


public class BoundServiceActivity extends Activity {

    private ImageButton audioPauseImageButton;
    private ImageButton audioPlayImageButton;
    private ImageButton audioRestartImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        audioPauseImageButton = (ImageButton) findViewById(R.id.audioPauseImageView);
        audioPlayImageButton = (ImageButton) findViewById(R.id.audioPlayImageView);
        audioRestartImageButton = (ImageButton) findViewById(R.id.audioRestartImageView);
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        audioPauseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        audioPlayImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        audioRestartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
