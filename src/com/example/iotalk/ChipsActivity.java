
package com.example.iotalk;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.util.Rfc822Tokenizer;

import com.android.ex.chips.BaseRecipientAdapter;
import com.android.ex.chips.RecipientEditTextView;

public class ChipsActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chips_activity);

        final BaseRecipientAdapter adapter = new BaseRecipientAdapter(this) { };
        final RecipientEditTextView retv = (RecipientEditTextView) findViewById(R.id.chips_retv);
        retv.setTokenizer(new Rfc822Tokenizer());
        retv.setAdapter(adapter);
        retv.append("test1@example.com");
        retv.append("test2@example.com");
    }
}
