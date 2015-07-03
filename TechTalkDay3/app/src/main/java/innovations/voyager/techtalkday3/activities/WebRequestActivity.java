package innovations.voyager.techtalkday3.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import innovations.voyager.techtalkday3.R;
import innovations.voyager.techtalkday3.utils.WebUtil;
import innovations.voyager.techtalkday3.services.WebRequestIntentService;


public class WebRequestActivity extends Activity {

    private EditText hostEditText;
    private EditText cityEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button getButton;
    private Button getCityWeatherButton;
    private Button getCoordinatesWeatherButton;
    private Button parseJsonButton;
    private TextView rawJsonResultTextView;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hostEditText = (EditText) findViewById(R.id.host_api_edit_text);
        cityEditText = (EditText) findViewById(R.id.city_api_edit_text);
        latitudeEditText = (EditText) findViewById(R.id.latitude_api_edit_text);
        longitudeEditText = (EditText) findViewById(R.id.longitude_api_edit_text);
        getButton = (Button) findViewById(R.id.get_api_button);
        getCityWeatherButton = (Button) findViewById(R.id.get_city_api_button);
        getCoordinatesWeatherButton = (Button) findViewById(R.id.get_coordinates_api_button);
        parseJsonButton = (Button) findViewById(R.id.parse_result_button);
        rawJsonResultTextView = (TextView) findViewById(R.id.raw_result_text_view);
        setOnclickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(WebRequestIntentService.TAG_BROADCAST);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(WebRequestIntentService.GET_WEATHER_API_ACTION.equals(intent.getStringExtra(WebRequestIntentService.RESPONSE_ACTION_KEY_INTENT_EXTRA))) {
                    showRawJsonResult(intent.getStringExtra(WebRequestIntentService.RESPONSE_GET_WEATHER_KEY_INTENT_EXTRA));
                }
            }
        };
        this.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(this.mBroadcastReceiver);
    }

    private void setOnclickListeners() {
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeGetAsynchronously(false);
            }
        });
        getCityWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeGetCityWeatherAsynchronously(false);
            }
        });
        getCoordinatesWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeGetCoordinatesWeatherAsynchronously(false);
            }
        });
        parseJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void executeGetAsynchronously(boolean isAsyncTaskMethod) {
        String resultingRequestUrl = hostEditText.getText().toString();
        if(isAsyncTaskMethod) {
            // asynctask method
            new GetAPI().execute(new String[] {resultingRequestUrl});
        } else {
            // intent service method
            buildGetWeatherIntentServiceAndStart(resultingRequestUrl);
        }
    }

    private void executeGetCityWeatherAsynchronously(boolean isAsyncTaskMethod) {
        String resultingRequestUrl = hostEditText.getText().toString()+ "/data/2.5/weather?q=" + cityEditText.getText().toString();
        if(isAsyncTaskMethod) {
            // asynctask method
            new GetAPI().execute(new String[] {resultingRequestUrl});
        } else {
            // intent service method
            buildGetWeatherIntentServiceAndStart(resultingRequestUrl);
        }
    }

    private void executeGetCoordinatesWeatherAsynchronously(boolean isAsyncTaskMethod) {
        String resultingRequestUrl = hostEditText.getText().toString() + "/data/2.5/weather?lat=" + latitudeEditText.getText().toString() + "&lon=" + longitudeEditText.getText().toString() + "&mode=json";
        if(isAsyncTaskMethod) {
            // asynctask method
            new GetAPI().execute(new String[] {resultingRequestUrl});
        } else {
            // intent service method
            buildGetWeatherIntentServiceAndStart(resultingRequestUrl);
        }
    }

    private class GetAPI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return WebUtil.getResponseHttp(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            showRawJsonResult(result);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private void buildGetWeatherIntentServiceAndStart(String url) {
        Intent webRequestIntentService = new Intent(getApplicationContext(), WebRequestIntentService.class);
        webRequestIntentService.setAction(WebRequestIntentService.GET_WEATHER_API_ACTION);
        webRequestIntentService.putExtra(WebRequestIntentService
                .GET_WEATHER_API_URL_KEY_INTENT_EXTRA, url);
        startService(webRequestIntentService);
    }

    private void showRawJsonResult(String result) {
        if(TextUtils.isEmpty(result)) {
            rawJsonResultTextView.setText("Request Failed");
        } else {
            rawJsonResultTextView.setText(result);
        }
    }
}
