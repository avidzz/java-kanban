import java.util.ArrayList;

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
        return "SubTask{" +
                "epicID=" + epicID +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", id=" + getId() +
                '}';
    }
}
