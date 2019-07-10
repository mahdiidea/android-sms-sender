package idea.mahdi.sms.Services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import idea.mahdi.sms.Application;
import idea.mahdi.sms.Utils.Util;


public class FCMService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Util.setShare("fcm_token", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("FCM_MESSAGE", remoteMessage.getData().toString());

        if (remoteMessage.getData() != null) {
            Map<String, String> data = remoteMessage.getData();
            Application.getInstance().send(data.get("phone"), data.get("text"));
        }
    }
}