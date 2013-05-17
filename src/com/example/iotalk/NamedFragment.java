package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment with a single string for state, and a single textview that shows this state. We don't
 * save/restore that state in the constructor. This is meant to show how to set state on a
 * fragment using {@link #newInstance(String)}. The {@link OutputFragment} is an example of
 * receiving state from a {@link Controller}, which is the other option.
 */
public class NamedFragment extends Fragment {
    private final static String NAME = "fragment-name";

    /** The state associated with this fragment. */
    private String mName;

    public NamedFragment() {
        // Do nothing.
    }

    /**
     * Create a new Named fragment with a name. The name is persisted for the life of the
     * fragment. You can verify that the state persists by rotating the device. We rely on the
     * fragment manager to create a fragment, and to assign this state automatically to it. We
     * only create a fragment once.
     * @param name
     * @return
     */
    public static NamedFragment newInstance(String name) {
        final Bundle args = new Bundle();
        args.putString(NAME, name);
        final NamedFragment fragment = new NamedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mName = args.getString(NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        final View layout = inflater.inflate(R.layout.first_fragment, null);
        // Show the second line, which invites the user to rotate the device and verify that the
        // state is preserved.
        final TextView secondary = (TextView) layout.findViewById(R.id.secondary_text);
        secondary.setVisibility(View.VISIBLE);
        // Display our private state in this textview.
        final TextView nameView = (TextView) layout.findViewById(R.id.ff_text);
        nameView.setText(mName);
        return layout;
    }
}
