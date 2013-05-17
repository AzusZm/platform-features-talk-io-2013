package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Trivial first fragment, which shows a simple list. Clicking on the list allows for
 * creating a second fragment.
 */
public class FirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // We ignore the saved state here, and return a trivial view for our fragment.
        final View v = inflater.inflate(R.layout.first_fragment, null);
        Log.d("FirstFragment", "The view we get is " + v);
        final View s = v.findViewById(R.id.ff_text);
        if (s != null) {
            Log.d("FirstFragment", "Found the view ");
        }
        return v;
    }
}
