package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Do nothing except demonstrate how to set the layout using fragments differently on phone
 * versus tablet.
 */
public class ResourceDemo extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root);
    }
}