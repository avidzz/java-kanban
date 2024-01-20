package ru.tech.kanban.model;

import java.util.ArrayList;
import ru.tech.kanban.service.TaskManager;
import ru.tech.kanban.service.TaskStatus;
import ru.tech.kanban.Main;

public class Epic extends Task {
    private ArrayList<Integer> idSubTask = new ArrayList<>();

    public Epic(Epic epicIN, int id) {
        super(epicIN.getName(), epicIN.getDescription(), epicIN.getStatus(), id);
    }

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }

    public ArrayList<Integer> getIdSubTask() {
        return idSubTask;
    }

    public void setIdSubTask(ArrayList<Integer> idSubTask) {
        this.idSubTask = idSubTask;
    }

    @Override
    public String toString() {
        return "ru.tech.kanban.model.Epic{" +
                "idSubTask=" + idSubTask +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", id=" + getId() +
                '}';
    }
}
