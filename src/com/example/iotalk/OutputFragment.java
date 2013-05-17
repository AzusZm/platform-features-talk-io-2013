package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment that has a single textview to display an integer.
 */
public class OutputFragment extends Fragment implements NumberOutput {
    private TextView mOutputButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // We ignore the saved state here, and return a trivial view for our fragment.
        final View output = inflater.inflate(R.layout.output_layout, null);
        mOutputButton = (TextView) output.findViewById(R.id.output_i);
        return output;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Register on start.
        final Controller controller = (Controller) getActivity();
        controller.registerOutput(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister ourselves on exit
        final Controller controller = (Controller) getActivity();
        controller.unregisterOutput();
    }

    @Override
    public void setNumber(int newNumber) {
        Log.d("OutputFragment", "setNumber(" + newNumber + ") called.");
        mOutputButton.setText("" + newNumber);
    }
}
