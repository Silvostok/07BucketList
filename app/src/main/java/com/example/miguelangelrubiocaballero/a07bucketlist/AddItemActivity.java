package com.example.miguelangelrubiocaballero.a07bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mEditText;
    private Button mAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView) findViewById(R.id.explain_textView);
        mEditText = (EditText) findViewById(R.id.input_text);
        mAddButton = (Button) findViewById(R.id.add_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mEditText.getText())) {
                    String title = mEditText.getText().toString();

                    Intent data = new Intent();
                    data.putExtra("newItem", title);

                    setResult(Activity.RESULT_OK, data);

                    finish();
                } else {
                    Toast.makeText(AddItemActivity.this, "Please enter some text in the Title field.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
