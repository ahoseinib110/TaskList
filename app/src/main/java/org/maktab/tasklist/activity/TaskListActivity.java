package org.maktab.tasklist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.maktab.tasklist.R;
import org.maktab.tasklist.fragment.TaskListFragment;

public class TaskListActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "org.maktab.tasklist.activity.taskName";
    public static final String EXTRA_NUMBER = "org.maktab.tasklist.activity.taskNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.task_list_fragment_container
                        , TaskListFragment.newInstance(getIntent().getStringExtra(EXTRA_NAME), getIntent().getIntExtra(EXTRA_NUMBER, 0)))
                .commit();
    }

    public static Intent newIntent(Context context, String name, int tasksNumber) {
        Intent intent = new Intent(context, TaskListActivity.class);
        Log.d("TLA_BASHIR",name +" "+tasksNumber);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_NUMBER, tasksNumber);
        return intent;
    }
}