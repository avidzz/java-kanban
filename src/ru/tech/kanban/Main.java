package ru.tech.kanban;

import ru.tech.kanban.model.Epic;
import ru.tech.kanban.model.SubTask;
import ru.tech.kanban.model.Task;
import ru.tech.kanban.service.TaskManager;
import ru.tech.kanban.service.TaskStatus;


public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        //создаем две задачи
        Task taskIN1 = new Task("Task1", "Посадить дерево", TaskStatus.NEW);
        Task taskIN2 = new Task("Task2", "Сходить в магазин", TaskStatus.NEW);
        //сохраняем задачи в список
        taskManager.addTask(taskIN1);
        taskManager.addTask(taskIN2);

        //создаем эпик1
        Epic epicIN1 = new Epic("Epic1", "Построить дом");
        //сохраняем эпик1 в список
        taskManager.addEpic(epicIN1);
        //создаем две подзадачи к эпику 1
        SubTask subTaskIN1 = new SubTask("Подзадача1", "Спроектировать дом", TaskStatus.DONE, 1003);
        SubTask subTaskIN2 = new SubTask("Подзадача2", "Найти бригаду строителей", TaskStatus.NEW, 1003);
        //сохраняем подзадачи в список
        taskManager.addSubTask(subTaskIN1);
        taskManager.addSubTask(subTaskIN2);

        //создаем эпик2
        Epic epicIN2 = new Epic("Epic2", "Съездить на отдых");
        //добавляем эпик2 в список
        taskManager.addEpic(epicIN2);
        //создаем одну подзадачу к эпику2
        SubTask subTaskIN3 = new SubTask("Подзадача1", "Купить путевку", TaskStatus.NEW, 1006);
        //сохраняем подзадачу в список
        taskManager.addSubTask(subTaskIN3);

        //Распечатайте списки эпиков, задач и подзадач
        System.out.println(taskManager.getListTasks());
        System.out.println(taskManager.getListSubTasks());
        System.out.println(taskManager.getListEpics());

        //удаляем одну из подзадач эпика1
        taskManager.removeSubTask(1005);
        //проверка
        System.out.println(taskManager.getListSubTasks());

    }
}
