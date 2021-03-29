package entity;

public class WorkOut implements SuperEntity {
    private String wid;
    private String name;
    private String description;

    public WorkOut() {
    }

    public WorkOut(String wid, String name, String description) {
        this.wid = wid;
        this.name = name;
        this.description = description;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WorkOut{" +
                "wid='" + wid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}