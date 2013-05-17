
package com.example.iotalk;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.panorama.PanoramaClient;
import com.google.android.gms.panorama.PanoramaClient.OnPanoramaInfoLoadedListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PhotoSphereActivity extends FragmentActivity {
    private static final int REQUEST_CODE = 1;

    private PanoramaClient mPanoramaClient;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.chips_activity);

        if (verifyGooglePlayServicesAvailable()) {
            preparePhotoSphere();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mPanoramaClient != null) {
            mPanoramaClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mPanoramaClient != null) {
            mPanoramaClient.disconnect();
        }
    }

    private boolean verifyGooglePlayServicesAvailable() {
        final int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(result, this, REQUEST_CODE).show();
            return false;
        }

        return true;
    }

    private void preparePhotoSphere() {
        mPanoramaClient = new PanoramaClient(this, new ConnectionCallbacks() {
            @Override
            public void onDisconnected() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onConnected(final Bundle connectionHint) {
                showPhotoSphere();
            }
        }, new OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(final ConnectionResult result) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void showPhotoSphere() {
        final Uri uri = copyPhotoSphereToExternal();

        mPanoramaClient.loadPanoramaInfo(new OnPanoramaInfoLoadedListener() {
            @Override
            public void onPanoramaInfoLoaded(final ConnectionResult result,
                    final Intent viewerIntent) {
                if (result.isSuccess() && viewerIntent != null) {
                    startActivity(viewerIntent);
                }
            }
        }, uri);
    }

    private Uri copyPhotoSphereToExternal() {
        final AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            final File outputFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "photosphere.jpg");
            in = assetManager.open("photosphere.jpg");
            out = new FileOutputStream(outputFile);
            final byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();

            return Uri.fromFile(outputFile);
        } catch (final IOException e) {
            return null;
        }
    }
}
