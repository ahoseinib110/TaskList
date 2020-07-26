package org.maktab.tasklist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.maktab.tasklist.R;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }
}