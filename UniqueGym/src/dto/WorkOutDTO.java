package dto;

public class WorkOutDTO {
    private String wid;
    private String name;
    private String description;

    public WorkOutDTO() {
    }

    public WorkOutDTO(String wid, String name, String description) {
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
        return "WorkOutDTO{" +
                "wid='" + wid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
