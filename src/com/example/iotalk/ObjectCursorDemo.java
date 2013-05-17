package com.example.iotalk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Query a content provider using loaders, <b>and</b> create objects off the UI thread.
 * The basic idea is quite straightforward. {@link ObjectCursor} is a cursor object, but it has
 * an extra method that we are aware of: {@link com.example.iotalk.ObjectCursor#getModel()}. This
 * method returns a pointer to an object that was created earlier. Saves time on object
 * creation, especially for heavy objects. In this demo, the object is trivial: three fields.
 * For larger objects, this method has considerable advantages.
 */
public class ObjectCursorDemo extends FragmentActivity {
    private final static String CONTACT_URI = "contact-uri";
    private static final int CONTACT_LOADER = 42;
    /** We display the results in this field. */
    private TextView mOutput;
    /** Start time (in system millis) when the load was initiated. */
    private long mStartTime;

    /**
     * Class to handle loader callbacks. This class has minimal local data.
     */
    final class HandleLoads implements LoaderManager.LoaderCallbacks<ObjectCursor<Contact>> {
        private final Context mContext;

        private HandleLoads (Context context) {
            mContext = context;
        }

        @Override
        public Loader<ObjectCursor<Contact>> onCreateLoader(int i, Bundle bundle) {
            switch (i) {
                case CONTACT_LOADER:
                    final String[] projection = new String[] {
                            ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            ContactsContract.Contacts.HAS_PHONE_NUMBER
                    };

                    final Uri uri = Uri.parse(bundle.getString(CONTACT_URI));
                    mStartTime = System.currentTimeMillis();
                    return new ObjectCursorLoader<Contact>(mContext, uri, projection, Contact.FACTORY);

                default:
                    Log.wtf("ObjectCursorDemo", "Creating a loader with an invalid ID");
                    return null;
            }
        }

        @Override
        public void onLoadFinished(Loader<ObjectCursor<Contact>> objectCursorLoader,
                ObjectCursor<Contact> cursor) {
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
            int i = 0;
            Log.d("ObjectCursorDemo", timeInfo);
            do {
                // The magic happens here. The object is already created! We can get a reference
                // directly.
                final Contact contact = cursor.getModel();
                final String line = contact.displayName;
                Log.d("ObjectCursorDemo", "" + line);
                if (i <6) {
                    mOutput.append(line + "\n");
                    i++;
                } else if (i == 6) {
                    mOutput.append("...and many others\n");
                    i++;
                }
            } while (cursor.moveToNext());
        }

        @Override
        public void onLoaderReset(Loader loader) {
            // Nothing to do here, since we don't have any local state.
        }
    }

    /**
     * Object that handles all our loader callbacks. It is private an final,
     * to allow it to be constructed with the class, and cleaned up automatically. It is a good
     * idea to keep no state in these callback objects, which reduces complexity if you were to
     * relocate the callback to another activity or fragment. */
    private final HandleLoads mCallback = new HandleLoads(this);

    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.loader_simple);
        // Same layout as before, except that we now create objects off the UI thread.
        mOutput = (TextView) findViewById(R.id.simple_loader_text);
    }

    /** Start loading data from the contacts database. */
    public void initiateLoad(View v) {
        startDataLoad(ContactsContract.Contacts.CONTENT_URI);
    }

    /**
     * Obtains the contact list for the currently selected account. This method just needs a
     * URI of data to access.  It starts off a background query, and background object construction
     * and returns data asynchronously.
     * @param uri The URI where contacts are stored.
     */
    private void startDataLoad(final Uri uri) {
        mStartTime = System.currentTimeMillis();
        final LoaderManager manager = getSupportLoaderManager();
        final Bundle args = new Bundle();
        args.putString(CONTACT_URI, uri.toString());
        manager.initLoader(CONTACT_LOADER, args, mCallback);
    }
}