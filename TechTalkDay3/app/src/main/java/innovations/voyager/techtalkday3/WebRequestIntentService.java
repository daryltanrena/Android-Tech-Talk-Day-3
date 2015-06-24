package innovations.voyager.techtalkday3;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class WebRequestIntentService extends IntentService {

    public static final String TAG_BROADCAST = "TAG_WEB_REQUEST_INTENT_SERVICE";
    public static final String GET_WEATHER_API_ACTION = "GET_WEATHER_API_ACTION";
    public static final String GET_WEATHER_API_URL_KEY_INTENT_EXTRA = "GET_WEATHER_API_URL_KEY_INTENT_EXTRA";
    public static final String RESPONSE_GET_WEATHER_KEY_INTENT_EXTRA = "RESPONSE_GET_WEATHER_KEY_INTENT_EXTRA";
    public static final String RESPONSE_ACTION_KEY_INTENT_EXTRA = "RESPONSE_ACTION_KEY_INTENT_EXTRA";

    public WebRequestIntentService() {
        super("WebRequestIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(GET_WEATHER_API_ACTION.equals(intent.getAction())) {
            String response = WebUtil.getResponseHttp(intent.getStringExtra
                    (GET_WEATHER_API_URL_KEY_INTENT_EXTRA));
            buildAndSendReplyBroadcast(GET_WEATHER_API_ACTION, RESPONSE_GET_WEATHER_KEY_INTENT_EXTRA, response);
        }
    }

    private void buildAndSendReplyBroadcast(String actionType, String keyExtra, String value) {
        Log.d("@@@@@", "2");
        Intent replyBroadcast = new Intent(TAG_BROADCAST);
        replyBroadcast.putExtra(RESPONSE_ACTION_KEY_INTENT_EXTRA, actionType);
        replyBroadcast.putExtra(keyExtra, value);
        sendBroadcast(replyBroadcast);
    }
}
