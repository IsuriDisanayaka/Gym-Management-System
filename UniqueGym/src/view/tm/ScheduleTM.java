package view.tm;

import javafx.scene.control.Button;

public class ScheduleTM {
    private String schId;
    private String mId;
    private String wId;
    private String wName;
    private String date;
    private String  setRound;
    private Button btn;

    public ScheduleTM() {
    }

    public ScheduleTM(String schId, String mId, String wId, String wName, String date, String setRound, Button btn) {
        this.schId = schId;
        this.mId = mId;
        this.wId = wId;
        this.wName = wName;
        this.date = date;
        this.setRound = setRound;
        this.btn = btn;
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getwId() {
        return wId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSetRound() {
        return setRound;
    }

    public void setSetRound(String setRound) {
        this.setRound = setRound;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}