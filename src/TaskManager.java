import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    protected static int id = 0;
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

    public HashMap<Integer, Task> getListTasks() {
        //метод для получения всех задач
        return tasksMap;
    }

    public HashMap<Integer, SubTask> getListSubTasks() {
        //метод для получения всех подзадач
        return subTasksMap;
    }

    public HashMap<Integer, Epic> getListEpics() {
        //метод для получения всех зэпиков
        return epicsMap;
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
        subTasksMap.clear();
    }

    public void clearAllEpics() {
        //метод для удаления всех эпиков
        epicsMap.clear();
    }

    public Task getTasks(int id) {
        //метод для получения задачи по id
        return tasksMap.get(id);
    }
    public SubTask getSubTasks(int id) {
        //метод для получения подзадачи по id
        return subTasksMap.get(id);
    }

    public Epic getEpic(int id) {
        //метод для получения эпика по id
        return epicsMap.get(id);
    }

    public void removeTask(int id) {
        //метод для удаления задачи по id
        tasksMap.remove(id);
    }

    public void removeSubTask(int id) {
        //метод для удаления подзадачи по id
        subTasksMap.remove(id);
    }
    public void removeEpic(int id) {
        //метод для удаления эпика по id
        epicsMap.remove(id);
    }

    public ArrayList<Integer> SubTasksEpic(int id) {
        //метод для получение всех подзадач определенного эпика
        return epicsMap.get(id).getIdSubTask();
    }


    @Override
    public String toString() {
        return "TaskManager{" +
                "taskMap=" + tasksMap +
                ", subTaskMap=" + subTasksMap +
                ", epicMap=" + epicsMap +
                '}';
    }
 }
