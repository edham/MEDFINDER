package com.med.finder.cliente.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.med.finder.cliente.utilidades.Utilidades;

public class ReceiverBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Utilidades.scheduleChargingReminder(context);
        /*
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            Intent serviceLauncher = new Intent(context, Servicio.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                context.startForegroundService(serviceLauncher);
            }
            else
            {
                context.startService(serviceLauncher);
            }
        }
*/
    }
}