package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Show how to set up fragments using resources and allow communication between them.
 *
 * The controller will receive input from one fragment and send input to the second fragment.  No
 * fragment has knowledge of any other, and
 *
 * The Activity implements a well known interface ({@link Controller}), which the input fragment
 * expects. Since the {@link InputFragment} only produces input, it never registers with the
 * controller. It directs events to the controller, and is stateless.
 *
 * The {@link OutputFragment} implements a well-known interface ({@link NumberOutput}) which
 * can display a number. It registers itself with the {@link Controller}. The Controller directs
 * changes in the number to it.  The output fragment is entirely stateless as well, and receives
 * all its state through the controller.
 */
public class CommunicateDemo extends FragmentActivity implements Controller{
    /** An object that knows how to display a number. */
    private NumberOutput mOutputDelegate;
    private int mGlobalCounter = 0;
    private final static String TAG_GLOBAL_COUNTER = "global-counter";

    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        if (savedState != null) {
            mGlobalCounter = savedState.getInt(TAG_GLOBAL_COUNTER);
        }
        setContentView(R.layout.fragment_communicate);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAG_GLOBAL_COUNTER, mGlobalCounter);
    }

    @Override
    public void acceptOperation(int operation) {
        switch (operation) {
            case Controller.DECREMENT:
                mGlobalCounter--;
                break;
            case Controller.INCREMENT:
                mGlobalCounter++;
                break;
            default:
                Log.wtf("CommunicateDemo", "Incorrect operation!");
                return;
        }
        if (mOutputDelegate != null) {
            mOutputDelegate.setNumber(mGlobalCounter);
        }
    }

    @Override
    public void registerOutput(NumberOutput outputDelegate) {
        mOutputDelegate = outputDelegate;
        // Since it just registered, it needs this global state from us.
        mOutputDelegate.setNumber(mGlobalCounter);
    }

    @Override
    public void unregisterOutput() {
        mOutputDelegate = null;
    }
}