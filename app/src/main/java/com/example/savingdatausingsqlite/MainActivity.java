package com.example.savingdatausingsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.savingdatausingsqlite.FeedReaderContract.*;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FeedReaderDbHelper mDbHelper;
    List itemIds;

    TextView tv_view;

    CRUD crud;
    Context context;

    Button btn_insert;

    EditText et_title, et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        crud = new CRUD();

        crud.tv_view = (TextView) findViewById(R.id.tv_view);

        et_title = (EditText) findViewById(R.id.et_title);
        et_name = (EditText) findViewById(R.id.et_name);

        mDbHelper = new FeedReaderDbHelper(getApplicationContext());

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String name = et_name.getText().toString();
                crud.insert(context, title, name);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        crud.read(context);
    }

    @Override
    protected void onDestroy() {
        crud.closeConnection(context);
        super.onDestroy();
    }
}
