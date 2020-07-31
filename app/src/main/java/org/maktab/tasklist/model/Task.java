package org.maktab.tasklist.model;

public class Task {
    private String mTaskTitle;
    private State mTaskState;

    public Task(String taskTitle, State taskState) {
        mTaskTitle = taskTitle;
        mTaskState = taskState;
    }

    public String getTaskTitle() {
        return mTaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        mTaskTitle = taskTitle;
    }

    public State getTaskState() {
        return mTaskState;
    }

    public void setTaskState(State taskState) {
        mTaskState = taskState;
    }
}
