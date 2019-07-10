package idea.mahdi.sms.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

import idea.mahdi.sms.Application;

public class SenderService extends Service {
    private final IBinder iBinder = new LocalBinder();

    public SenderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(MessageSenderListener);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Application.BROADCAST_SEND);
        registerReceiver(MessageSenderListener, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void send(String phone, String text) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, text, null, null);
            Log.e("Application", "send " + phone + " : " + text);
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    private BroadcastReceiver MessageSenderListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String phone = intent.getStringExtra("phone");
            String text = intent.getStringExtra("text");
            send(phone, text);
        }
    };

    public class LocalBinder extends Binder {
        public SenderService getService() {
            return SenderService.this;
        }
    }
}
