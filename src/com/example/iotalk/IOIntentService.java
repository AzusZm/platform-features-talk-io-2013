
package com.example.iotalk;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.DateUtils;

public class IOIntentService extends IntentService {
    public static final String ACTION_SYNC = "sync";

    public IOIntentService() {
        super("IOIntentService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        if (ACTION_SYNC.equals(intent.getAction())) {
            requestSync();
        }
    }

    private void requestSync() {
        // Perform some long-running task
        try {
            Thread.sleep(10 * DateUtils.SECOND_IN_MILLIS);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
