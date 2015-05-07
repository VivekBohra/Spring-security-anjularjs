package com.web.cms;

import java.util.List;

/**
 * interface DaoInterface
 *
 *
 */
public interface DaoInterface {

    void addTask(Contact task);

    void archiveTask(int taskId);

    void updateTask(Contact task);

    List<Contact> getTaskById(final int taskId);

    List<Contact> getAllTasks();
}
