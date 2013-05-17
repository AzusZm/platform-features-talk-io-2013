
package com.example.iotalk;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

public class Notifications {

    public static void createSingleMessageNotification(final Context context) {
        final int notifIcon = R.drawable.stat_notify_email_generic;
        final Bitmap contactPhoto = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher);
        final String contactName = "John Doe";
        final String subject = "Basic single-message notification";
        final String account = "test@example.com";
        final int unreadMessageCount = 2;

        final NotificationCompat.Builder b = new Builder(context);
        b.setSmallIcon(notifIcon);
        b.setLargeIcon(contactPhoto);
        b.setContentTitle(contactName);
        b.setContentText(subject);
        b.setSubText(account);
        b.setNumber(unreadMessageCount);

        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(0,
                b.build());
    }

    public static void createBigTextNotification(final Context context) {
        final int notifIcon = R.drawable.stat_notify_email_generic;
        final Bitmap contactPhoto = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher);
        final String contactName = "John Doe";
        final String subject = "BigTextStyle notification";
        final String account = "test@example.com";
        final int unreadMessageCount = 2;
        final String messageBody = "This is a big message body. " + "This is a big message body. "
                + "This is a big message body. " + "This is a big message body. "
                + "This is a big message body. " + "This is a big message body. "
                + "This is a big message body. " + "This is a big message body. "
                + "This is a big message body. " + "This is a big message body. ";

        final Intent deleteIntent = new Intent();
        final PendingIntent deletePI = PendingIntent.getBroadcast(context, 0, deleteIntent, 0);

        final NotificationCompat.Builder b = new Builder(context);
        b.setSmallIcon(notifIcon);
        b.setLargeIcon(contactPhoto);
        b.setContentTitle(contactName);
        b.setContentText(subject);
        b.setSubText(account);
        b.setNumber(unreadMessageCount);

        final BigTextStyle bts = new BigTextStyle(b);
        bts.bigText(messageBody);
        b.addAction(R.drawable.ic_menu_delete_holo_dark, "Delete", deletePI);

        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(2,
                b.build());
    }

    public static void createMultiMessageNotification(final Context context) {
        final int notifIcon = R.drawable.stat_notify_email_generic;
        final Bitmap multiMailIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_notification_multiple_mail_holo_dark);
        final String notifTitle = "Basic multi-message notification";
        final String account = "test@example.com";
        final int unreadMessageCount = 3;

        final NotificationCompat.Builder b = new Builder(context);
        b.setSmallIcon(notifIcon);
        b.setLargeIcon(multiMailIcon);
        b.setContentTitle(notifTitle);
        b.setSubText(account);
        b.setNumber(unreadMessageCount);

        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1,
                b.build());
    }

    public static void createInboxNotification(final Context context) {
        final int notifIcon = R.drawable.stat_notify_email_generic;
        final Bitmap multiMailIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_notification_multiple_mail_holo_dark);
        final String notifTitle = "InboxStyle notification";
        final String account = "test@example.com";
        final int unreadMessageCount = 3;

        final SpannableStringBuilder msg1 = new SpannableStringBuilder("John Doe - Subject 1");
        msg1.setSpan(new StyleSpan(Typeface.BOLD), 0, "John Doe".length(), 0);

        final SpannableStringBuilder msg2 = new SpannableStringBuilder("Jane Doe - Subject 2");
        msg2.setSpan(new StyleSpan(Typeface.BOLD), 0, "Jane Doe".length(), 0);

        final NotificationCompat.Builder b = new Builder(context);
        b.setSmallIcon(notifIcon);
        b.setLargeIcon(multiMailIcon);
        b.setContentTitle(notifTitle);
        b.setSubText(account);
        b.setNumber(unreadMessageCount);

        final InboxStyle is = new InboxStyle(b);
        is.addLine(msg1);
        is.addLine(msg2);

        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(3,
                b.build());
    }

}
