package idea.mahdi.sms;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.FirebaseApp;

import idea.mahdi.sms.Services.SenderService;

public class Application extends android.app.Application {

    boolean serviceBound = false;
    private static Application instance;
    public static final String BROADCAST_SEND = "idea.mahdi.sms.send";

    public static Application getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FirebaseApp.initializeApp(this);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SenderService.LocalBinder binder = (SenderService.LocalBinder) service;
            SenderService player = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    public void send(String phone, String text) {
        if (!serviceBound) {
            Intent playerIntent = new Intent(instance, SenderService.class);
            playerIntent.putExtra("phone", phone);
            playerIntent.putExtra("text", text);
            startService(playerIntent);
            bindService(playerIntent, connection, Context.BIND_AUTO_CREATE);
        } else {
            Intent broadcastIntent = new Intent(Application.BROADCAST_SEND);
            broadcastIntent.putExtra("phone", phone);
            broadcastIntent.putExtra("text", text);
            sendBroadcast(broadcastIntent);
        }
    }
}
