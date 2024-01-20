package ru.tech.kanban.model;

import ru.tech.kanban.service.TaskManager;
import ru.tech.kanban.service.TaskStatus;
import ru.tech.kanban.Main;

public class SubTask extends Task {
    private int epicID;

    public SubTask(SubTask subTaskIN, int id) {
        super(subTaskIN.getName(), subTaskIN.getDescription(), subTaskIN.getStatus(), id);
        this.epicID = subTaskIN.epicID;
    }

    public SubTask(String name, String description, TaskStatus status, int epicID) {
        super(name, description, status);
        this.epicID = epicID;
    }

    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString() {
        return "ru.tech.kanban.model.SubTask{" +
                "epicID=" + epicID +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", id=" + getId() +
                '}';
    }
}
