package org.maktab.tasklist.repository;

import org.maktab.tasklist.model.Task;

import java.util.List;

public interface IRepository <E> {
    public void setList(List<E> list);
    public List<E> getList();
    public void insert(E element);
    public void remove(E element);
    public void remove(int index);
}
