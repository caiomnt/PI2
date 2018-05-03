package pi2.econommarket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        Intent it = new Intent(context, AlarmActivity.class);
        context.startActivity(it);

        Toast.makeText(context, "teste de receiver alarme", Toast.LENGTH_LONG).show();
    }

}
