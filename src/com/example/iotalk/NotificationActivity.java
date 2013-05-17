
package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class NotificationActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
    }

    public void createSingleMessageNotification(final View view) {
        Notifications.createSingleMessageNotification(this);
    }

    public void createMultiMessageNotification(final View view) {
        Notifications.createMultiMessageNotification(this);
    }

    public void createBigTextNotification(final View view) {
        Notifications.createBigTextNotification(this);
    }

    public void createInboxNotification(final View view) {
        Notifications.createInboxNotification(this);
    }
}
