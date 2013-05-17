package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment that has two buttons that accept input.
 */
public class InputFragment extends Fragment {
    /** The central object that will actually take the actions. */
    private Controller mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        final View input = inflater.inflate(R.layout.input_layout, null);
        final View incrementButton = input.findViewById(R.id.increment_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });
        final View decrementButton = input.findViewById(R.id.decrement_button);
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });
        return input;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mController = (Controller) getActivity();
        // No need to register ourselves with the controller, since it never sends us anything.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mController = null;
    }

    /** Increment the current count. */
    public void increment() {
        // Directs all input to the controller. If we don't have a controller, silently drop the
        // event on the floor.
        if (mController != null) {
            mController.acceptOperation(Controller.INCREMENT);
        }
    }

    /** Decrement the current count. */
    public void decrement() {
        // Directs all input to the controller. If we don't have a controller, silently drop the
        // event on the floor.
        if (mController != null) {
            mController.acceptOperation(Controller.DECREMENT);
        }
    }
}
