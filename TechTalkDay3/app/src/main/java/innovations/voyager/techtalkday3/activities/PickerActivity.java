package innovations.voyager.techtalkday3.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import innovations.voyager.techtalkday3.R;


public class PickerActivity extends Activity {

    private Button goToBoundServiceActivityButton;
    private Button goToUnboundServiceActivityButton;
    private Button goToWebRequestActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        goToBoundServiceActivityButton = (Button) findViewById(R.id.go_to_bound_service_activity_button);
        goToUnboundServiceActivityButton = (Button) findViewById(R.id.go_to_unbound_service_activity_button);
        goToWebRequestActivityButton = (Button) findViewById(R.id.go_to_web_request_activity_button);
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        goToBoundServiceActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBoundServiceActivityIntent = new Intent(getApplicationContext(), BoundServiceActivity.class);
                startActivity(intentBoundServiceActivityIntent);
            }
        });
        goToUnboundServiceActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUnboundServiceActivityIntent = new Intent(getApplicationContext(), UnboundServiceActivity.class);
                startActivity(intentUnboundServiceActivityIntent);
            }
        });
        goToWebRequestActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentWebRequestActivityIntent = new Intent(getApplicationContext(), WebRequestActivity.class);
                startActivity(intentWebRequestActivityIntent);
            }
        });
    }

}
