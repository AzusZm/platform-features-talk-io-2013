package com.example.iotalk;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Query a content provider using loaders. This example shows how to query the contacts database
 * and obtain a cursor with the given projection. We use cursor methods to show all the display
 * names in the contacts database. This works only for local contacts. If you are running this on
 * the simulator, you might need to add a few contacts entries.
 */
public class SimpleCursorDemo extends FragmentActivity {
    private final static String CONTACT_URI = "contact-uri";
    /** ID associated with a loader that loads contacts. */
    private static final int CONTACT_LOADER = 42;
    private TextView mOutput;
    /** Start time (in system millis) when the load was initiated. */
    private long mStartTime;

    /**
     * Class to handle loader callbacks. This class has minimal local data.
     */
    final class HandleLoads implements LoaderManager.LoaderCallbacks<Cursor> {
        private final Context mContext;
        private HandleLoads (Context context) {
            mContext = context;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            switch (i) {
                case CONTACT_LOADER:
                    final String[] projection = new String[] {
                            ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            ContactsContract.Contacts.HAS_PHONE_NUMBER
                    };
                    final String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " =  1";
                    final String[] selectionArgs = null;
                    final String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
                            + " COLLATE LOCALIZED ASC";
                    final Uri uri = Uri.parse(bundle.getString(CONTACT_URI));
                    return new CursorLoader(
                            mContext, uri, projection, selection, selectionArgs,
                            sortOrder);

                default:
                    Log.wtf("SimpleCursorDemo", "Creating a loader with an invalid ID");
                    return null;
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
            // Checking how long the load took.
            final long endTime = System.currentTimeMillis();
            final String totalTimeMs;
            if (endTime > mStartTime) {
                totalTimeMs = "" + (System.currentTimeMillis() - mStartTime);
            } else {
                totalTimeMs = "no";
            }
            // Got new data, do something with it.
            if (cursor == null || !cursor.moveToFirst()) {
                return;
            }
            String timeInfo = "Entire load took " + totalTimeMs + " ms.\n";
            mOutput.append(timeInfo);
            Log.d("SimpleCursorDemo", "" + timeInfo);
            int i = 0;
            do {
                // Due to our projection, the name is the 2nd field, (Index 1, O'indexed)
                String line = cursor.getString(1);
                Log.d("SimpleCursorDemo", "" + line);
                if (i <6) {
                    mOutput.append(line + "\n");
                    i++;
                } else if (i == 6) {
                    mOutput.append("...and many others\n");
                    i++;
                }
            } while (cursor.moveToNext());
            Log.d("SimpleCursorDemo", "\nEntire load took " + totalTimeMs + " ms.");
        }

        @Override
        public void onLoaderReset(Loader loader) {
            // Nothing to do here, since we don't have any local state.
        }
    }

    /** Object that handles all our loader callbacks. */
    private final HandleLoads mCallback = new HandleLoads(this);

    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.loader_simple);
        mOutput = (TextView) findViewById(R.id.simple_loader_text);
    }

    /** Start loading data from the contacts database. */
    public void initiateLoad(View v) {
        startDataLoad(ContactsContract.Contacts.CONTENT_URI);
    }

    /**
     * Obtains the contact list for the currently selected account.
     *
     * @return A cursor for for accessing the contact list.
     */
    private void startDataLoad(final Uri uri) {
        mStartTime = System.currentTimeMillis();
        final LoaderManager manager = getSupportLoaderManager();
        final Bundle args = new Bundle();
        args.putString(CONTACT_URI, uri.toString());
        manager.initLoader(CONTACT_LOADER, args, mCallback);
    }
}