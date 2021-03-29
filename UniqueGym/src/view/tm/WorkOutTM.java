package view.tm;

import javafx.scene.control.Button;

public class WorkOutTM {
    private String wid;
    private String name;
    private String description;
    private Button btn;

    public WorkOutTM() {
    }

    public WorkOutTM(String wid, String name, String description, Button btn) {
        this.wid = wid;
        this.name = name;
        this.description = description;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}