package pi2.econommarket;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Button btn = findViewById(R.id.btnAlarm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent it = new Intent(AlarmActivity.this, AlarmReceiver.class);
                PendingIntent pt = PendingIntent.getBroadcast(AlarmActivity.this, 0, it, 0);

                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.set(Calendar.HOUR_OF_DAY, 21);
                c.set(Calendar.MINUTE, 10);

                am.set( AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pt);

            }
        });


        
        Button btn2 = findViewById(R.id.btnsair);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent it = new Intent(AlarmActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
        
    }

}
