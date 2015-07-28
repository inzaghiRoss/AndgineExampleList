package com.example.wenming.andgineexamplelist;

import android.app.ListActivity;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.util.Log;
import android.content.Intent;


public class MainListActivity extends ListActivity {

    private ListAdapter mListAdapter = null;
    private static final String TAG = "examplelist";
    private static final String SUB_TAG = "MainListActivity";
    private String[] mStrings = new String[]{"0", "1", "2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings );
        this.setListAdapter(mListAdapter);
        this.getListView().setOnItemClickListener(mListener);
    }

    void startIntent(String name) {
        if (name != null) {
            Class act = null;
            try {
                act = Class.forName(this.getPackageName() + name);
            } catch(ClassNotFoundException e)  {
                e.printStackTrace();
            }
            if (act != null) {
                Intent intent = new Intent(this, act);
                //intent.setClassName(this, LineExample.class);
                this.startActivity(intent);
            } else {
                Log.d(TAG, "act is null");
            }
        }
    }


    private AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Log.d(TAG, SUB_TAG + " view " + id);
            switch((int) id) {
                case 0:
                    startIntent(".LineExample");
                    break;
                case 1:
                    startIntent(".AnimatedSpritesExample");
                    break;
                case 2:
                    startIntent(".RandomGameExample");
                    break;
                default:
                    break;
            }
        }
    };
}