package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Trivial second fragment.
 */
public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // We ignore the saved state here, and return a trivial view for our fragment.
        final View v = inflater.inflate(R.layout.second_fragment, null);
        Log.d("SecondFragment", "The view we get is " + v);
        final View s = v.findViewById(R.id.sf_text);
        if (s != null) {
            Log.d("SecondFragment", "Found the view ");
        }
        return v;
    }
}
