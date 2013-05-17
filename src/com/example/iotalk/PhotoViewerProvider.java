
package com.example.iotalk;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PhotoViewerProvider extends ContentProvider {
    private static final int PHOTOS = 1;
    private static final int PHOTO_INDIVIDUAL_0 = 2;
    private static final int PHOTO_INDIVIDUAL_68 = 70;
    private static final int BARCODE_SLIDE = 60 + PHOTO_INDIVIDUAL_0;

    private static final String PROVIDER_URI = "com.example.iotalk.PhotoViewerProvider";

    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(PROVIDER_URI, "photos", PHOTOS);
        for (int i = PHOTO_INDIVIDUAL_0; i < PHOTO_INDIVIDUAL_68; i++) {
            sUriMatcher.addURI(PROVIDER_URI, "photos/" + i, i);
        }
    }

    @Override
    public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(final Uri uri) {
        return null;
    }

    @Override
    public Uri insert(final Uri uri, final ContentValues values) {
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(final Uri uri, final String[] projection, final String selection,
            final String[] selectionArgs, final String sortOrder) {
        final MatrixCursor matrix = new MatrixCursor(projection);

        /*
         * Choose the table to query and a sort order based on the code returned
         * for the incoming URI. Here, too, only the statements for table 3 are shown.
         */
        switch (sUriMatcher.match(uri)) {
        // If the incoming URI was for all of the photos table
            case PHOTOS:
                // The first photo should be the one with the code location and the barcode
                addRow(matrix, BARCODE_SLIDE);
                for (int i = PHOTO_INDIVIDUAL_0; i < PHOTO_INDIVIDUAL_68; i++) {
                    addRow(matrix, i);
                }
                break;
            default:
                addRow(matrix, sUriMatcher.match(uri));
                break;
        }
        // call the code to actually do the query

        return matrix;
    }

    /**
     * Adds a single row to the Cursor. A real implementation should check the
     * projection to properly match the columns.
     */
    private static void addRow(final MatrixCursor matrix, final int matchId) {
        matrix.newRow()
                .add("content://" + PROVIDER_URI + "/photos/" + matchId) // uri
                .add("talk_" + getFileNumber(matchId) + ".png") // displayName
                .add("content://" + PROVIDER_URI + "/photos/" + matchId + "/contentUri") // contentUri
                .add("content://" + PROVIDER_URI + "/photos/" + matchId + "/thumbnailUri") // thumbnailUri
                .add("image/png"); // contentType
    }

    @Override
    public int update(final Uri uri, final ContentValues values, final String selection,
            final String[] selectionArgs) {
        return 0;
    }

    @Override
    public AssetFileDescriptor openAssetFile(final Uri uri, final String mode)
            throws FileNotFoundException {
        final List<String> pathSegments = uri.getPathSegments();
        final int id = Integer.parseInt(pathSegments.get(1));
        final String fileName = "talk_" + getFileNumber(id) + ".png";
        try {
            return getContext().getAssets().openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final int getFileNumber(final int id) {
        return id - PHOTO_INDIVIDUAL_0;
    }
}
