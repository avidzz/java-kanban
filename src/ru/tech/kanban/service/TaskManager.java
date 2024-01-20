package ru.tech.kanban.service;

import ru.tech.kanban.model.Epic;
import ru.tech.kanban.model.SubTask;
import ru.tech.kanban.model.Task;
import ru.tech.kanban.Main;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    protected static int id = 1000;
    public HashMap<Integer, Task> tasksMap = new HashMap<>();
    public HashMap<Integer, SubTask> subTasksMap = new HashMap<>();
    public HashMap<Integer, Epic> epicsMap = new HashMap<>();

    public Task addTask(Task taskIN) {
        Task task = new Task(taskIN.getName(), taskIN.getDescription(), taskIN.getStatus(), ++id);
        tasksMap.put(task.getId(), task);
        return task;
    }

    public SubTask addSubTask(SubTask subTaskIN) {
        SubTask subTask = new SubTask(subTaskIN, ++id);
        if (epicsMap.containsKey(subTaskIN.getEpicID())) {
            subTasksMap.put(subTask.getId(), subTask);
            epicsMap.get(subTaskIN.getEpicID()).getIdSubTask().add(subTask.getId());
            updateEpicStatus(subTaskIN);
        }
        return subTask;
    }

    public Epic addEpic(Epic epicIN) {
        Epic epic = new Epic(epicIN, ++id);
        epicsMap.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpicStatus(SubTask subTaskIN) {
        //метод для обновления статуса эпика
        int countProgress = 0;
        int countDone = 0;
        int countNew = 0;
        for (Integer id: epicsMap.get(subTaskIN.getEpicID()).getIdSubTask()) {
            if (subTasksMap.get(id).getStatus() == TaskStatus.IN_PROGRESS) {
                countProgress++;
            } else if (subTasksMap.get(id).getStatus() == TaskStatus.DONE) {
                countDone++;
            } else if (subTasksMap.get(id).getStatus() == TaskStatus.NEW)
                countNew++;
        }

        if((countProgress == 0) && (countDone == 0)) {
            epicsMap.get(subTaskIN.getEpicID()).setStatus(TaskStatus.NEW);
        } else if ((countProgress == 0) && (countNew == 0) && (countDone > 0)) {
            epicsMap.get(subTaskIN.getEpicID()).setStatus(TaskStatus.DONE);
        } else {
            epicsMap.get(subTaskIN.getEpicID()).setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public ArrayList<Task> getListTasks() {
        //метод для получения всех задач
        ArrayList<Task> tasksList = new ArrayList<>();
        for (Task task: tasksMap.values()) {
            tasksList.add(task);
        }
        return tasksList;
    }

    public ArrayList<SubTask> getListSubTasks() {
        //метод для получения всех подзадач
        ArrayList<SubTask> subTasksList = new ArrayList<>();
        for (SubTask subTask: subTasksMap.values()) {
            subTasksList.add(subTask);
        }
        return subTasksList;
    }

    public ArrayList<Epic> getListEpics() {
        //метод для получения всех зэпиков
        ArrayList<Epic> epicsList = new ArrayList<>();
        for (Epic epic: epicsMap.values()) {
            epicsList.add(epic);
        }
        return epicsList;
    }


    public void updateTask(Task task) {
        //метод для обновления задачи
        if (tasksMap.containsKey(task.getId())) {
            tasksMap.put(task.getId(), task);
        }
    }

    public void updateSubTask(SubTask subTask) {
        //метод для обновления подзадачи
        if (subTasksMap.containsKey(subTask.getId()) && (epicsMap.containsKey(subTask.getEpicID()))) {
            subTasksMap.put(subTask.getId(), subTask);
            updateEpicStatus(subTask);
        }
    }

    public void updateEpic(Epic epic) {
        //метод для обновления эпика
        if (epicsMap.containsKey(epic.getId())) {
            epicsMap.put(epic.getId(), epic);
        }
    }

    public void clearAllTasks() {
        //метод для удаления всех задач
        tasksMap.clear();
    }

    public void clearAllSubTasks() {
        //метод для удаления всех подзадач
        for (SubTask subTask: subTasksMap.values()) {
            epicsMap.get(subTask.getEpicID()).getIdSubTask().clear();
        }
        subTasksMap.clear();
    }

    public void clearAllEpics() {
        //метод для удаления всех эпиков
        for(Epic epic: epicsMap.values()) {
           for(Integer subTaskId: epic.getIdSubTask()) {
               subTasksMap.remove(subTaskId);
                }
            }
        epicsMap.clear();
    }

    public Task getTask(Integer id) {
        //метод для получения задачи по id
        return tasksMap.get(id);
    }
    public SubTask getSubTask(Integer id) {
        //метод для получения подзадачи по id
        return subTasksMap.get(id);
    }

    public Epic getEpic(Integer id) {
        //метод для получения эпика по id
        return epicsMap.get(id);
    }

    public void removeTask(Integer id) {
        //метод для удаления задачи по id
        tasksMap.remove(id);
    }

    public void removeSubTask(Integer id) {
        //метод для удаления подзадачи по id
        epicsMap.get(subTasksMap.get(id).getEpicID()).getIdSubTask().remove(id);
        subTasksMap.remove(id);
    }
    public void removeEpic(Integer id) {
        //метод для удаления эпика по id
        for (Integer subTaskId: epicsMap.get(id).getIdSubTask()) {
            subTasksMap.remove(subTasksMap);
        }
        epicsMap.remove(id);
    }

    public ArrayList<Integer> SubTasksEpic(Integer id) {
        //метод для получение всех подзадач определенного эпика
        return epicsMap.get(id).getIdSubTask();
    }


    @Override
    public String toString() {
        return "ru.tech.kanban.service.TaskManager{" +
                "taskMap=" + tasksMap +
                ", subTaskMap=" + subTasksMap +
                ", epicMap=" + epicsMap +
                '}';
    }
 }
