package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Creates fragments using newInstance, and assigns state to them. The state
 * in this case is a single String, which is displayed in the fragment.
 */
public class NewInstanceDemo extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.new_fragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void newFragmentWithName(final String name) {
        // The magic happens here. We get a fragment with this state assigned
        // to it. The state is persisted past orientation change and pause/restart.
        final Fragment fragment = NamedFragment.newInstance(name);
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_anchor, fragment, "NamedFragment");
        transaction.commit();
    }

    public void newFragmentViki(final View unused) {
        newFragmentWithName("Vikram Aggarwal");
    }

    public void newFragmentScott(final View unused) {
        newFragmentWithName("Scott Kennedy");
    }
}