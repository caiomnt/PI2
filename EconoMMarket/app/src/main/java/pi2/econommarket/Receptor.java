package pi2.econommarket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Receptor extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        String numero = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        String estado = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

        if (estado.equals("IDLE")) Toast.makeText(context, "SOU O JO SOARES SUA PIRANHA!", Toast.LENGTH_LONG).show();
        else Toast.makeText(context, "VOCE EH O PELE? "+numero, Toast.LENGTH_LONG).show();

    }
}
