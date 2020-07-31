package org.maktab.tasklist.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.maktab.tasklist.R;
import org.maktab.tasklist.model.State;
import org.maktab.tasklist.model.Task;
import org.maktab.tasklist.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";
    private static final String ARG_TASKS_NUMBER = "tasksNumber";

    // TODO: Rename and change types of parameters
    private String mName;
    private int mTasksNumber;
    private RecyclerView mRecyclerViewTasks;
    private TaskRepository mTaskRepository;
    TaskAdapter mTaskAdapter;
    public TaskListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListFragment newInstance(String name, int tasksNumber) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_TASKS_NUMBER, tasksNumber);
        Log.d("TLF_BASHIR", name + " " + tasksNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
            mTasksNumber = getArguments().getInt(ARG_TASKS_NUMBER);
        }
        mTaskRepository = TaskRepository.getInstance();
        creatData();
    }

    private void creatData() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i <mTasksNumber ; i++) {
            State state = getRandomState();
            Task task = new Task(mName,state);
            tasks.add(task);
        }
        mTaskRepository.setList(tasks);
    }

    private State getRandomState() {
        State state=State.TODO;
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);
        switch (randomNumber){
            case 0: state =State.TODO;break;
            case 1: state =State.DOING;break;
            case 2: state =State.DONE;break;
            default:break;
        }
        return state;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);
        mRecyclerViewTasks.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        if (mTaskAdapter == null) {
            mTaskAdapter = new TaskAdapter(mTaskRepository.getList());
            mRecyclerViewTasks.setAdapter(mTaskAdapter);
        } else {
            mTaskAdapter.notifyDataSetChanged();
        }
    }
    private void findViews(View view) {
        mRecyclerViewTasks = view.findViewById(R.id.recycler_view_tasks);
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
        List<Task> mTaskList;

        public TaskAdapter(List<Task> taskList) {
            mTaskList = taskList;
        }

        public List<Task> getTaskList() {
            return mTaskList;
        }

        public void setTaskList(List<Task> taskList) {
            mTaskList = taskList;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_row, parent, false);
            TaskViewHolder taskViewHolder = new TaskViewHolder(view);
            return taskViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task task = mTaskList.get(position);
            boolean isOdd = (position%2!=0);
            holder.onBind(task,isOdd);
        }

        @Override
        public int getItemCount() {
            return mTaskList.size();
        }
    }

    private class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private TextView mTextViewState;
        private LinearLayout mRowContainer;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textViewName);
            mTextViewState = itemView.findViewById(R.id.textViewState);
            mRowContainer = itemView.findViewById(R.id.rowContainer);
        }

        public void onBind(Task task, boolean isOdd) {
            if(isOdd){
                mRowContainer.setBackgroundResource(R.color.light_blue);
            }else{
                mRowContainer.setBackgroundResource(R.color.whight);
            }
            mTextViewName.setText(task.getTaskTitle());
            mTextViewState.setText(String.valueOf(task.getTaskState()));
        }
    }
}