
package com.example.iotalk;

import android.content.Context;
import android.content.Intent;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

public class GCMIntentService extends GCMBaseIntentService {
    private static final String SENDER_ID = "TODO: Put your real sender ID here.";

    public static void register(final Context context) {
        GCMRegistrar.checkDevice(context);
        GCMRegistrar.checkManifest(context);
        final String regId = GCMRegistrar.getRegistrationId(context);
        if ("".equals(regId)) {
            GCMRegistrar.register(context, SENDER_ID);
        }
    }

    @Override
    protected void onRegistered(final Context context, final String regId) {
        // TODO Send this to your server
    }

    @Override
    protected void onMessage(final Context context, final Intent intent) {
        // TODO Do your sync now, since you know there's new data
    }

    @Override
    protected void onUnregistered(final Context context, final String regId) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onError(final Context context, final String errorId) {
        // TODO Auto-generated method stub
    }
}
