package idea.mahdi.sms;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import idea.mahdi.sms.Utils.Util;
import idea.mahdi.sms.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Activity activity;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.btnToken.setOnClickListener(v -> {
            try {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("token", Util.getShare("fcm_token"));
                Objects.requireNonNull(manager).setPrimaryClip(clip);
                Toast.makeText(activity, "Token copied to clipboard", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().subscribeToTopic("message");
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/message");
    }
}
