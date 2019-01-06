package com.example.miguelangelrubiocaballero.a07bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.listView);
        mItems = new ArrayList<>();

        updateUI();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddItemActivity.class);
                startActivityForResult(intent, 1234);
            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                // Actual deletion of the item, updateUI gets called to update the adapter.
                mItems.remove(i);
                updateUI();
                // Show a toast containing a message to show to the user what happened.
                Toast.makeText(MainActivity.this, "Deleted: " + item, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.title_list_item);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box);

                //Check the checkbox and strike trough the text. or remove this.
                if (!checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    title.setPaintFlags((title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG));
                } else {
                    checkBox.setChecked(false);
                    title.setPaintFlags(0);
                }
                updateUI();
            }
        });

    }



    private void updateUI() {

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this, R.layout.row_item, R.id.title_list_item, mItems);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }


    // Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1234) {
                String item = data.getStringExtra("newItem");

                mItems.add(item);
                updateUI();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
