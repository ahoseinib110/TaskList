package org.maktab.tasklist.repository;

import org.maktab.tasklist.model.Task;

import java.util.List;

public class TaskRepository implements IRepository<Task> {
    private List<Task> mTasks;
    private static TaskRepository mTaskRepository;

    private TaskRepository() {
    }

    public static TaskRepository getInstance(){
        if(mTaskRepository==null){
            mTaskRepository = new TaskRepository();
        }
        return mTaskRepository;
    }

    @Override
    public void setList(List<Task> list) {
        mTasks=list;
    }

    @Override
    public List<Task> getList() {
        return mTasks;
    }

    @Override
    public void insert(Task element) {
        mTasks.add(element);
    }

    @Override
    public void remove(Task element) {
        mTasks.remove(element);
    }

    @Override
    public void remove(int index) {
        mTasks.remove(index);
    }
}
