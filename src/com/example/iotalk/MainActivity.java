
package com.example.iotalk;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.android.ex.photo.Intents;
import com.android.ex.photo.Intents.PhotoViewIntentBuilder;

/**
 * Give an plain list of buttons and allow the user to launch one of many
 * activities. A test activity like this is great for initial development.
 * Remember to remove it from the manifest before a release!
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    /**
     * Launch a class by name.
     *
     * @param clasz
     */
    private void launchClass(Class<?> clasz) {
        final Intent intent = new Intent(this, clasz);
        startActivity(intent);
    }

    /**
     * Launch the notifications demo. Called from onclick=""
     *
     * @param unused
     */
    public void launchNotifications(final View unused) {
        launchClass(NotificationActivity.class);
    }

    /**
     * Launch the XML fragment Resource demo. Called from onclick=""
     *
     * @param unused
     */
    public void resourceDemo(final View unused) {
        launchClass(ResourceDemo.class);
    }

    /**
     * Launch the download manager demo. Called from onclick=""
     *
     * @param unused
     */
    public void downloadFile(final View unused) {
        final DownloadManager downloadManager =
                (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        final Uri destUri = Uri.parse(getExternalCacheDir().toURI().toString());

        Request request = new Request(Uri.parse("http://www.google.com/"));
        request.setDestinationUri(destUri);
        request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            request.setAllowedOverMetered(false);
        }
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading large file...");
        request.setDescription("Mail I/O Example App will be ready soon!");
        request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        downloadManager.enqueue(request);
    }

    /**
     * Launch the chips demo. Called from onclick=""
     *
     * @param unused
     */
    public void launchChips(final View unused) {
        launchClass(ChipsActivity.class);
    }

    /**
     * Launch the fragment communication demo. Called from onclick=""
     *
     * @param unused
     */
    public void communicateDemo(final View unused) {
        launchClass(CommunicateDemo.class);
    }

    public void newInstanceDemo(final View unused) {
        launchClass(NewInstanceDemo.class);
    }

    /**
     * Launch the simple loader. Called from onclick=""
     * @param unused
     */
    public void simpleLoaderDemo(final View unused) {
        launchClass(SimpleCursorDemo.class);
    }

    /**
     * Launch the Object cursor loader demo. Called from onclick=""
     * @param unused
     */
    public void objectLoaderDemo(final View unused) {
        launchClass(ObjectCursorDemo.class);
    }

    /**
     * Launch the Google Play services / Photo Sphere demo. Called from
     * onclick=""
     *
     * @param unused
     */
    public void launchPhotoSphere(final View unused) {
        launchClass(PhotoSphereActivity.class);
    }

    public void launchPhotoViewer(final View unused) {
        final PhotoViewIntentBuilder builder = Intents.newPhotoViewActivityIntentBuilder(this);
        builder.setPhotosUri("content://com.example.iotalk.PhotoViewerProvider/photos");
        startActivity(builder.build());
    }
}
